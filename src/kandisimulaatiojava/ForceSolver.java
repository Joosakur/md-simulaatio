package kandisimulaatiojava;

import java.util.ArrayList;
import java.util.List;

class ForceSolver {
    private final List<Atom> interactingAtoms;
    private final Simulation simulation;
    private final NeighbourListManager neighbourListManager;
    private final int numberOfNeighbourCells;
    private final double simulationCellSize;
    private final double epsilon;
    private final double sigma;
    private final double sigmaPow6;
    private final double forceConstant;
    private final double cutOffRadius;

    public ForceSolver(Simulation simulation, NeighbourListManager neighbourListManager) {
        this.simulation = simulation;
        this.neighbourListManager = neighbourListManager;
        this.numberOfNeighbourCells = simulation.getNumberOfNeighbourCells();
        this.simulationCellSize = simulation.getSimulationCellSize();
        this.epsilon = simulation.getEpsilon();
        this.sigma = simulation.getSigma();
        sigmaPow6 = Phys.pow6(sigma);
        forceConstant = -24*epsilon*sigmaPow6;
        cutOffRadius = 2.5*sigma;
        interactingAtoms = new ArrayList<Atom>();
    }

    public void solveForces(){
        updatePrevForcesAndResetForces();

        for(Atom a1 : simulation.getAtoms()){
            updateInteractingAtoms(a1);
            for(Atom a2 : interactingAtoms){
                addForceFromAtom2ToAtom1(a1, a2);
            }
        }
    }

    private void updatePrevForcesAndResetForces() {
        for(Atom a : simulation.getAtoms()){
            for (int dim = 0; dim < 3; dim++) {
                a.setPrevForce(dim, a.getForce()[dim]);
            }
            a.resetForce();
        }
    }

    private void updateInteractingAtoms(Atom a1) {
        int cellX, cellY, cellZ, x, y, z;
        interactingAtoms.clear();

        cellX = (int)(numberOfNeighbourCells*a1.getPosition()[0]/simulationCellSize);
        cellY = (int)(numberOfNeighbourCells*a1.getPosition()[1]/simulationCellSize);
        cellZ = (int)(numberOfNeighbourCells*a1.getPosition()[2]/simulationCellSize);

        for (int i = cellX-1; i <= cellX+1; i++) {
            x = i;
            if(x < 0) x += numberOfNeighbourCells;
            if(x == numberOfNeighbourCells) x=0;
            for (int j = cellY-1; j <= cellY+1; j++) {
                y = j;
                if(y < 0) y+= numberOfNeighbourCells;
                if(y == numberOfNeighbourCells) y=0;
                for (int k = cellZ-1; k <= cellZ+1; k++) {
                    z = k;
                    if(z < 0) z+= numberOfNeighbourCells;
                    if(z == numberOfNeighbourCells) z=0;

                    interactingAtoms.addAll(neighbourListManager.getAtomsInCell(x, y, z));
                }
            }
        }
    }

    private void addForceFromAtom2ToAtom1(Atom a1, Atom a2) {
        if(a1.equals(a2)) return;

        double dx = calculateDistanceOnAxis(a1, a2, 0);
        if(dx>cutOffRadius) return;
        double dy = calculateDistanceOnAxis(a1, a2, 1);
        if(dy>cutOffRadius) return;
        double dz = calculateDistanceOnAxis(a1, a2, 2);
        if(dz>cutOffRadius) return;
        double dr = Math.sqrt(dx*dx+dy*dy+dz*dz);
        if(dr>cutOffRadius) return;

        double dr6 = Phys.pow6(dr);

        double force = forceConstant*(dr6-2*sigmaPow6)/(dr6*dr6*dr);

        double forcePerDistance = force/dr;
        
        a1.addToForce(0, forcePerDistance*dx);
        a1.addToForce(1, forcePerDistance*dy);
        a1.addToForce(2, forcePerDistance*dz);
    }

    private double calculateDistanceOnAxis(Atom a1, Atom a2, int dim) {
        double dx;
        dx = a1.getPosition()[dim]-a2.getPosition()[dim];
        if(dx > simulationCellSize /2.0) {
            dx -= simulationCellSize;
        }
        else if(dx <= -simulationCellSize /2.0) {
            dx+= simulationCellSize;
        }
        return dx;
    }

    public double getCutOffRadius() {
        return cutOffRadius;
    }

}