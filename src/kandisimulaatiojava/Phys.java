package kandisimulaatiojava;

public class Phys {
    public static final double KB = 1.380649e-23; /*Boltzmann constant*/
    public static final double AU = 1.66053892e-27; /*atomic unit mass*/
    public static final double E_CHARGE = 1.602176565e-19;

    public static double square(double x){
        return x*x;
    }

    public static double cube(double x){
        return x*x*x;
    }

    public static int square(int x){
        return x*x;
    }

    public static int cube(int x){
        return x*x*x;
    }
    
    public static double pow6(double value){
        value = value*value*value;
        return value*value;
    }
    
}