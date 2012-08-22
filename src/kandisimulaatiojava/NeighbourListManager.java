package kandisimulaatiojava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class NeighbourListManager {
    private HashMap<Integer, List<Atom>> neighbourLists;
    private final Simulation simulation;
    private final int numberOfNeighbourCells; //in one dimension
    private final double simulationCellSize;

    public NeighbourListManager(Simulation simulation) {
        this.simulation = simulation;
        numberOfNeighbourCells = simulation.getNumberOfNeighbourCells();
        simulationCellSize = simulation.getSimulationCellSize();

        neighbourLists = new HashMap<Integer, List<Atom>>();
        for (int i = 0; i < Phys.cube(numberOfNeighbourCells); i++) {
            neighbourLists.put(i, new ArrayList<Atom>());
        }
    }

    public void updateNeighbourList(){
        simulation.resetMaxDisplacement1();
        simulation.resetMaxDisplacement2();

        for (int i = 0; i < Phys.cube(numberOfNeighbourCells); i++) {
            neighbourLists.get(i).clear();
        }

        for(Atom atom : simulation.getAtoms()){
            //atom.resetDisplacement();
            atom.updatePrevNeighbourUpdatePosition();
            int cell = getCellNumber(atom);
            neighbourLists.get(cell).add(atom);
        }
    }

    List<Atom> getAtomsInCell(int cellNumber){
        return neighbourLists.get(cellNumber);
    }

    public List<Atom> getAtomsInCell(int x, int y, int z){
        int cellNumber = getCellNumber(x, y, z);
        return getAtomsInCell(cellNumber);
    }

    int getCellNumber(Atom atom){
        int cellx = getCellNumber(atom, 0);
        int celly = getCellNumber(atom, 1);
        int cellz = getCellNumber(atom, 2);
        return getCellNumber(cellx, celly, cellz);
    }

    private int getCellNumber(int x, int y, int z){
        return x*Phys.square(numberOfNeighbourCells) + y*numberOfNeighbourCells + z;
    }

    private int getCellNumber(Atom atom, int dim){
        return (int)(numberOfNeighbourCells * atom.getPosition()[dim] / simulationCellSize);
    }

}