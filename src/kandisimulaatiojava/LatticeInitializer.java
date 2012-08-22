package kandisimulaatiojava;

import java.util.List;

public class LatticeInitializer {
    public static final int BCC = 1;
    public static final int FCC = 2;

    public static void initPosition(int latticeType, List<Atom> atoms, double latticeCellSize, int numberOfLatticeCells){
        if(latticeType == BCC) initPositionBCC(atoms, latticeCellSize, numberOfLatticeCells);
        else if(latticeType == FCC) initPositionFCC(atoms, latticeCellSize, numberOfLatticeCells);
    }

    private static void initPositionFCC(List<Atom> atoms, double latticeCellSize, int numberOfLatticeCells){
        int a;

        for(int i=0; i< numberOfLatticeCells; i++){
            for(int j=0; j< numberOfLatticeCells; j++){
                for(int k=0; k< numberOfLatticeCells; k++){
                    a=4*(numberOfLatticeCells * numberOfLatticeCells *i+ numberOfLatticeCells *j+k);

                    atoms.get(a).setPosition(0, latticeCellSize *i);
                    atoms.get(a).setPosition(1, latticeCellSize *j);
                    atoms.get(a).setPosition(2, latticeCellSize *k);

                    atoms.get(a + 1).setPosition(0, latticeCellSize *i);
                    atoms.get(a + 1).setPosition(1, latticeCellSize *(j+0.5));
                    atoms.get(a + 1).setPosition(2, latticeCellSize *(k+0.5));

                    atoms.get(a + 2).setPosition(0, latticeCellSize *(i+0.5));
                    atoms.get(a + 2).setPosition(1, latticeCellSize *(j+0.5));
                    atoms.get(a + 2).setPosition(2, latticeCellSize *k);

                    atoms.get(a + 3).setPosition(0, latticeCellSize *(i+0.5));
                    atoms.get(a + 3).setPosition(1, latticeCellSize *j);
                    atoms.get(a + 3).setPosition(2, latticeCellSize *(k+0.5));

                }
            }
        }
    }

    private static void initPositionBCC(List<Atom> atoms, double latticeCellSize, int numberOfLatticeCells){
        int a;

        for(int i=0; i< numberOfLatticeCells; i++){
            for(int j=0; j< numberOfLatticeCells; j++){
                for(int k=0; k< numberOfLatticeCells; k++){
                    a=2*(numberOfLatticeCells * numberOfLatticeCells *i+ numberOfLatticeCells *j+k);

                    atoms.get(a).setPosition(0, latticeCellSize *i);
                    atoms.get(a).setPosition(1, latticeCellSize *j);
                    atoms.get(a).setPosition(2, latticeCellSize *k);

                    atoms.get(a + 1).setPosition(0, latticeCellSize *(i+0.5));
                    atoms.get(a + 1).setPosition(1, latticeCellSize *(j+0.5));
                    atoms.get(a + 1).setPosition(2, latticeCellSize *(k+0.5));
                }
            }
        }
    }

    public static int getNumberOfAtoms(int latticeType, int numberOfLatticeCells){
        switch (latticeType){
            case BCC:
                return 2*Phys.cube(numberOfLatticeCells);
            case FCC:
                return 4*Phys.cube(numberOfLatticeCells);
            default:
                return -1;
        }
    }

}