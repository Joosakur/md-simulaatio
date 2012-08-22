package kandisimulaatiojava;

import java.util.List;

class VelocityInitializer {
    static double initVelocitiesMaxBoltz(List<Atom> atoms, double initTemperature, double mass, RandomGen randomGen){
        int i;
        double u, v, speedSquareSum, c, c2;
        double[] vsum = new double[atoms.size()];

        speedSquareSum=0;

        c2 = Math.sqrt(mass /(2*Math.PI* Phys.KB*2* initTemperature));
        c = 0.5* mass /(Phys.KB*2* initTemperature); //g(x) = 1/(cx^2+1)

        for(i = 0; i< atoms.size(); i++){
            Atom atom = atoms.get(i);
            for(int dim=0; dim<3; dim++){
                while(true){
                    v = Math.tan(Math.PI*(randomGen.random()-0.5))/(Math.sqrt(c)); //v=G^-1x)

                    u = randomGen.random()*c2/(c*v*v + 1); //u = 0..A*g(x), A=pi, g(x) = 1/(A(cx^2+))

                    if(u < c2*Math.exp(-0.5* mass *v*v/(Phys.KB*2* initTemperature))){
                        atom.setVelocity(dim, v);
                        vsum[dim] += v;
                        break;
                    }
                }
            }
            v = Math.sqrt(atom.getVelocity()[0]*atom.getVelocity()[0]+atom.getVelocity()[1]*atom.getVelocity()[1]+atom.getVelocity()[2]*atom.getVelocity()[2]);
            speedSquareSum += v*v;

        }

        for(Atom a : atoms){
            for (int dim = 0; dim < 3; dim++) {
                a.addToVelocity(dim, -vsum[dim] / atoms.size());
            }
        }

        // T = 2/(3kB) E_rms
        return (mass /(3* Phys.KB))*speedSquareSum / atoms.size();
    }
}
