package kandisimulaatiojava;

public class Atom {
    private static int curId = 0;
    private double[] position;    
    private double[] prevPosition; //position on last time step
    private double[] prevNeighbourUpdatePosition;
    private double[] nonPeriodicPosition;
    private double[] velocity;
    private double[] force;
    private double[] prevForce; //force on last time step

    private final int id;

    public Atom() {
        this.id = curId++;
        position = new double[3]; 
        prevPosition = new double[3];
        prevNeighbourUpdatePosition = new double[3];
        nonPeriodicPosition = new double[3];
        velocity = new double[3];
        force = new double[3];
        prevForce = new double[3];
    }

    public int getId() {
        return id;
    }

    public double[] getPosition() {
        return position;
    }

    public void setPosition(int dimension, double value) {
        position[dimension] = value;
    }

    public void addToPosition(int dimension, double dx) {
        position[dimension] += dx;
    }

    public double[] getPrevPosition() {
        return prevPosition;
    }

    public void setPrevPosition(int dimension, double value) {
        prevPosition[dimension] = value;
    }

    public double[] getPrevNeighbourUpdatePosition() {
        return prevNeighbourUpdatePosition;
    }

    public void updatePrevNeighbourUpdatePosition() {
        prevNeighbourUpdatePosition[0] = nonPeriodicPosition[0];
        prevNeighbourUpdatePosition[1] = nonPeriodicPosition[2];
        prevNeighbourUpdatePosition[1] = nonPeriodicPosition[2];
    }

    public double[] getNonPeriodicPosition() {
        return nonPeriodicPosition;
    }

    public void setNonPeriodicPosition(int dimension, double value) {
        nonPeriodicPosition[dimension] = value;
    }

    public void addToNonPeriodicPosition(int dimension, double dx) {
        nonPeriodicPosition[dimension] += dx;
    }

    public double[] getVelocity() {
        return velocity;
    }

    public void setVelocity(int dimension, double value) {
        velocity[dimension] = value;
    }

    public void addToVelocity(int dimension, double dv) {
        velocity[dimension] += dv;
    }

    public double[] getForce() {
        return force;
    }

    public void setForce(int dimension, double value) {
        force[dimension] = value;
    }
    
    public void addToForce(int dimension, double value) {
        force[dimension] += value;
    }
    
    public void resetForce(){
        force[0] = 0;
        force[1] = 0;
        force[2] = 0;
    }

    public double[] getPrevForce() {
        return prevForce;
    }

    public void setPrevForce(int dimension, double value) {
        prevForce[dimension] = value;
    }

    @Override
    public boolean equals(Object o){
        if(((Atom)o).getId()==this.id) return true;
        else return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + this.id;
        return hash;
    }

    double getVelocitySquare() {
        return velocity[0]*velocity[0]+velocity[1]*velocity[1]+velocity[2]*velocity[2];
    }

}
