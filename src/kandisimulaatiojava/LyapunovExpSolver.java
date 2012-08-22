package kandisimulaatiojava;

import java.util.List;
import java.util.Observable;

public class LyapunovExpSolver extends Observable implements Runnable{

    private boolean paused;
    private Simulation referenceSimulation;
    private Simulation perturbedSimulation;
    private double distanceTreshold;
    private double initPhaseSpaceDiff;
    private double phaseSpaceDiff;
    private double diffRatio;
    private double sumOfLogOfDiffRatio;
    private double lyapunovExp;

    public LyapunovExpSolver(Simulation simulation1, Simulation simulation2) {
        this.referenceSimulation=simulation1;
        this.perturbedSimulation=simulation2;
    }

    @Override
    public void run() {
        initialize();
        while(true){
            if(!paused) {
                step();
            }
        }
    }

    private void initialize() {
        paused = true;
        sumOfLogOfDiffRatio = 0;
        
        while(paused); //wait
        
        referenceSimulation.initialize();
        perturbedSimulation.setSeed(referenceSimulation.getSeed()); //get possibly randomized seed
        perturbedSimulation.initialize();

        perturbedSimulation.setSeed(referenceSimulation.getSeed()); //use same seed again
        double maxPerturbation=referenceSimulation.getSigma()/1e9;
        perturbedSimulation.perturbatePosition(maxPerturbation);

        referenceSimulation.initNonPeriodicPositions();
        perturbedSimulation.initNonPeriodicPositions();
        
        initPhaseSpaceDiff = getTotalPhaseSpaceDistance();
        distanceTreshold = 100 * initPhaseSpaceDiff;
    }

    private void step(){
        referenceSimulation.step();
        perturbedSimulation.step();

        if(getTotalPhaseSpaceDistance() > distanceTreshold)
            updateLyapunov();

        //update screen
        setChanged();
        notifyObservers();
    }

    private void updateLyapunov() {
        phaseSpaceDiff = getTotalPhaseSpaceDistance();
        
        diffRatio = phaseSpaceDiff / initPhaseSpaceDiff;
        sumOfLogOfDiffRatio += Math.log(diffRatio);
        
        lyapunovExp = sumOfLogOfDiffRatio /referenceSimulation.getTime();
        System.out.println(lyapunovExp);
        
        rescaleAllPerturbedPositions(diffRatio);
    }
    
    private double getTotalPhaseSpaceDistance(){
        double L=0;
        List<Atom> atoms1 = referenceSimulation.getAtoms();
        List<Atom> atoms2 = perturbedSimulation.getAtoms();
        
        for (int i = 0; i < referenceSimulation.getAtoms().size(); i++) {
            Atom a1 = atoms1.get(i);
            Atom a2 = atoms2.get(i);
            
            double dx = a2.getNonPeriodicPosition()[0] - a1.getNonPeriodicPosition()[0];
            double dy = a2.getNonPeriodicPosition()[1] - a1.getNonPeriodicPosition()[1];
            double dz = a2.getNonPeriodicPosition()[2] - a1.getNonPeriodicPosition()[2];
            
            L += dx*dx + dy*dy + dz*dz;
        }
        
        return Math.sqrt(L);
    }
    
    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public double getLyapunovExp() {
        return lyapunovExp;
    }

    private void rescaleAllPerturbedPositions(double rescaleParameter) {
        List<Atom> referenceAtoms = referenceSimulation.getAtoms();
        List<Atom> perturbedAtoms = perturbedSimulation.getAtoms();
        
        for (int i=0; i<referenceSimulation.getNumberOfAtoms(); i++){
            Atom referenceAtom = referenceAtoms.get(i);
            Atom perturbedAtom = perturbedAtoms.get(i);
            
            perturbedAtom.setPosition(0, getRescaledPosition(0, referenceAtom, perturbedAtom, rescaleParameter));
            perturbedAtom.setPosition(1, getRescaledPosition(1, referenceAtom, perturbedAtom, rescaleParameter));
            perturbedAtom.setPosition(2, getRescaledPosition(2, referenceAtom, perturbedAtom, rescaleParameter));
            
            perturbedAtom.setNonPeriodicPosition(0, getRescaledLEPosition(0, referenceAtom, perturbedAtom, rescaleParameter));
            perturbedAtom.setNonPeriodicPosition(1, getRescaledLEPosition(1, referenceAtom, perturbedAtom, rescaleParameter));
            perturbedAtom.setNonPeriodicPosition(2, getRescaledLEPosition(2, referenceAtom, perturbedAtom, rescaleParameter));
            
            perturbedAtom.setVelocity(0, getRescaledVelocity(0, referenceAtom, perturbedAtom, rescaleParameter));
            perturbedAtom.setVelocity(1, getRescaledVelocity(1, referenceAtom, perturbedAtom, rescaleParameter));
            perturbedAtom.setVelocity(2, getRescaledVelocity(2, referenceAtom, perturbedAtom, rescaleParameter));
            
        }
    }
    
    private double getRescaledPosition(int dim, Atom referenceAtom, Atom perturbedAtom, double rescaleParameter){
        double referencePosition = referenceAtom.getPosition()[dim];
        double perturbedPosition = perturbedAtom.getPosition()[dim];
        return getRescaledValue(rescaleParameter, referencePosition, perturbedPosition);
    }

    private double getRescaledLEPosition(int dim, Atom referenceAtom, Atom perturbedAtom, double rescaleParameter){
        double referencePosition = referenceAtom.getNonPeriodicPosition()[dim];
        double perturbedPosition = perturbedAtom.getNonPeriodicPosition()[dim];
        return getRescaledValue(rescaleParameter, referencePosition, perturbedPosition);
    }
    
    private double getRescaledVelocity(int dim, Atom referenceAtom, Atom perturbedAtom, double rescaleParameter){
        double referenceVelocity = referenceAtom.getVelocity()[dim];
        double perturbedVelocity = perturbedAtom.getVelocity()[dim];
        return getRescaledValue(rescaleParameter, referenceVelocity, perturbedVelocity);
    }

    private double getRescaledValue(double rescaleParameter, double referenceValue, double perturbedValue) {
        return referenceValue + (perturbedValue-referenceValue)/rescaleParameter;
    }
    
}
