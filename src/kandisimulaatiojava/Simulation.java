package kandisimulaatiojava;

import java.util.*;

public class Simulation extends Observable {

    //Set constants
    private int latticeType = LatticeInitializer.FCC;
    private int numberOfLatticeCells = 6;
    private double mass = 40* Phys.AU; //mass of an atom
    private double density = 1410;
    private double latticeCellSize = Math.pow(4*mass/density, 1.0/3);
    private double timeStepFactor = 0.1;
    private double initTemperature = 85;
    private double epsilon = 120* Phys.KB;
    private double sigma = 3.5e-10;
    private int seed = -1;

    //Calculated constants
    private int numberOfAtoms;
    private double simulationCellSize;
    private double timeStep;
    private double neighbourRadius;
    private int numberOfNeighbourCells; //number of subcells in ONE dimension!

    //Variables
    private double temperature;
    private double maxDisplacement1, maxDisplacement2; //largest displacements since last neighbo updateur
    private double time;

    //Objects
    private RandomGen randomGen;
    private List<Atom> atoms;
    private ForceSolver forceSolver;
    private NeighbourListManager neighbourManager;

    public void initialize(){
        initVars();
        LatticeInitializer.initPosition(latticeType, atoms, latticeCellSize, numberOfLatticeCells);
        initNonPeriodicPositions();
        temperature = VelocityInitializer.initVelocitiesMaxBoltz(atoms, initTemperature, mass, randomGen);
        neighbourManager.updateNeighbourList();
    }

    private void initVars(){
        time = 0;

        numberOfAtoms = LatticeInitializer.getNumberOfAtoms(latticeType, numberOfLatticeCells);
        simulationCellSize = numberOfLatticeCells * latticeCellSize;
        calculateTimeStep();
        calculateNeighbourRadius();
        numberOfNeighbourCells = (int)Math.ceil(simulationCellSize / neighbourRadius);
        
        maxDisplacement1 = 0;
        maxDisplacement2 = 0;

        atoms = new ArrayList<Atom>(numberOfAtoms);
        for(int i=0; i< numberOfAtoms; i++){
            atoms.add(new Atom());
        }

        neighbourManager = new NeighbourListManager(this);
        forceSolver = new ForceSolver(this, neighbourManager);

        if(seed <0)
            randomizeSeed();
        randomGen = new RandomGen(seed);
    }
    
    public void step(){
        predictor(atoms);
        forceSolver.solveForces();
        corrector(atoms);
        time += timeStep;
        if(neighbourListNeedsUpdate()) neighbourManager.updateNeighbourList();
        runStatistics();
    }

    public void initNonPeriodicPositions(){
        for(Atom a : atoms){
            for (int i = 0; i < 3; i++) {
                a.setNonPeriodicPosition(i, a.getPosition()[i]);
            }
        }
    }

    public void perturbatePosition(double max){
        for(Atom a : atoms){
            double perturbation = randomGen.random()*max;
            double theta = Math.acos(1-2* randomGen.random());
            double fii = 2*Math.PI* randomGen.random();
            a.addToPosition(0, perturbation*Math.cos(fii)*Math.sin(theta));
            a.addToPosition(1, perturbation*Math.sin(fii)*Math.sin(theta));
            a.addToPosition(2, perturbation*Math.cos(theta));
        }
    }
    

    private void predictor(List<Atom> atoms){
        for(Atom atom : atoms){
            for (int dim = 0; dim < 3; dim++) {
                atom.setPrevPosition(dim, atom.getPosition()[dim]);
                atom.addToPosition(dim, atom.getVelocity()[dim]* timeStep +((4*atom.getForce()[dim]-atom.getPrevForce()[dim])/(6*mass))* timeStep * timeStep);
                if(atom.getPosition()[dim] < 0) {
                    atom.addToPosition(dim, simulationCellSize);
                }
                if(atom.getPosition()[dim] >= simulationCellSize) {
                    atom.addToPosition(dim, -simulationCellSize);
                }
            }
        }
    }
    

    private void corrector(List<Atom> atoms){
        for(Atom atom : atoms){
            for(int dim=0; dim<3; dim++){
                double dr = atom.getVelocity()[dim]* timeStep +((atom.getForce()[dim]+2*atom.getPrevForce()[dim])/(6*mass))* timeStep * timeStep;
                
                atom.setPosition(dim, atom.getPrevPosition()[dim]+dr);
                atom.addToNonPeriodicPosition(dim, dr);
                        
                while(atom.getPosition()[dim] < 0) {
                    atom.addToPosition(dim, simulationCellSize);
                }
                while(atom.getPosition()[dim] >= simulationCellSize) {
                    atom.addToPosition(dim, -simulationCellSize);
                }
                
                atom.setVelocity(dim, dr/ timeStep +((2*atom.getForce()[dim]+atom.getPrevForce()[dim])/(6*mass))* timeStep);
            }
        }
    }

    private boolean neighbourListNeedsUpdate(){
        for(Atom atom : atoms){
            double displacement = Math.sqrt(
                    Phys.square(atom.getNonPeriodicPosition()[0] - atom.getPrevNeighbourUpdatePosition()[0])
                            + Phys.square(atom.getNonPeriodicPosition()[1] - atom.getPrevNeighbourUpdatePosition()[2])
                            + Phys.square(atom.getNonPeriodicPosition()[1] - atom.getPrevNeighbourUpdatePosition()[2])
            );

            if(displacement > maxDisplacement1) {
                maxDisplacement2 = maxDisplacement1;
                maxDisplacement1 = displacement;
            }
            else if (displacement > maxDisplacement2) maxDisplacement2 = displacement;

            if (maxDisplacement1 + maxDisplacement2 > neighbourRadius - forceSolver.getCutOffRadius())
                return true;
        }

        return false;
    }

    private void runStatistics(){
        double velocitySquareSum = 0;
        for(Atom atom : atoms){
            velocitySquareSum += atom.getVelocitySquare();
        }
        temperature = (mass/(3* Phys.KB))*(velocitySquareSum/numberOfAtoms);
    }

    public double getInitTemperature() {
        return initTemperature;
    }

    public boolean setInitTemperature(double initTemperature) {
        if(initTemperature < 0 || initTemperature > 10000) return false;
        this.initTemperature = initTemperature;
        return true;
    }

    public int getNumberOfAtoms() {
        return numberOfAtoms;
    }

    public int getNumberOfLatticeCells() {
        return numberOfLatticeCells;
    }

    public boolean setNumberOfLatticeCells(int numberOfLatticeCells) {
        if(numberOfLatticeCells<1) return false;
        this.numberOfLatticeCells = numberOfLatticeCells;
        return true;
    }

    public boolean isNumberOfNeighbourCellsValid(){
        simulationCellSize = numberOfLatticeCells * latticeCellSize;
        calculateTimeStep();
        calculateNeighbourRadius();
        numberOfNeighbourCells = (int)Math.ceil(simulationCellSize / neighbourRadius);
        return numberOfNeighbourCells >= 3;
    }

    private void calculateNeighbourRadius() {
        neighbourRadius = 2.5*sigma + 20*Math.sqrt(3* Phys.KB*initTemperature/mass)*timeStep;
    }

    private void calculateTimeStep() {
        timeStep = timeStepFactor *(latticeCellSize /20)/(5*Math.sqrt(3* Phys.KB*initTemperature/mass));
    }

    public double getTemperature() {
        return temperature;
    }

    public double getLatticeCellSize() {
        return latticeCellSize;
    }

    public boolean setLatticeCellSize(double length) {
        if(length<0) return false;
        this.latticeCellSize = length;
        if(latticeType == LatticeInitializer.BCC) density = 2*mass/(latticeCellSize * latticeCellSize * latticeCellSize);
        if(latticeType == LatticeInitializer.FCC) density = 4*mass/(latticeCellSize * latticeCellSize * latticeCellSize);
        return true;
    }

    public List<Atom> getAtoms() {
        return atoms;
    }

    public double getDensity() {
        return density;
    }

    public boolean setDensity(double density) {
        if(density<0) return false;
        this.density = density;
        if(latticeType == LatticeInitializer.BCC) latticeCellSize = Math.pow(2*mass/density, 1.0/3);
        if(latticeType == LatticeInitializer.FCC) latticeCellSize = Math.pow(4*mass/density, 1.0/3);
        return true;
    }

    public double getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(double epsilon) {
        this.epsilon = epsilon;
    }

    public int getLatticeType() {
        return latticeType;
    }

    public boolean setLatticeType(int latticeType) {
        if(latticeType!= LatticeInitializer.FCC && latticeType!= LatticeInitializer.BCC) return false;
        this.latticeType = latticeType;
        return true;
    }

    public double getMass() {
        return mass;
    }

    public boolean setMass(double mass) {
        if(mass<0) return false;
        this.mass = mass;
        return true;
    }

    public double getSigma() {
        return sigma;
    }

    public boolean setSigma(double sigma) {
        if(sigma<0) return false;
        this.sigma = sigma;
        return true;
    }

    public double getTime() {
        return time;
    }

    public double getTimeStep() {
        return timeStep;
    }

    public double getTimeStepFactor() {
        return timeStepFactor;
    }

    public boolean setTimeStepFactor(double timeStepFactor) {
        if(timeStepFactor<0) return false;
        this.timeStepFactor = timeStepFactor;
        return true;
    }

    public double getSimulationCellSize() {
        return simulationCellSize;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
        if(seed <0){
            randomizeSeed();
        }
    }

    private void randomizeSeed(){
        seed = (int)(System.currentTimeMillis()%(int)(Math.pow(2, 31)-1));
    }

    protected void resetMaxDisplacement2() {
        this.maxDisplacement2 = 0;
    }

    protected void resetMaxDisplacement1() {
        this.maxDisplacement1 = 0;
    }

    int getNumberOfNeighbourCells() {
        return numberOfNeighbourCells;
    }
}
