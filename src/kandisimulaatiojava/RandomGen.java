package kandisimulaatiojava;

public class RandomGen {

    private int seed;

    public RandomGen(int seed) {
        this.seed = seed;
    }

    //Implementation of Park-Miller generator from the lecture notes of
    //Basics of Monte Carlos simulations, Helsinki University 2012
    double random(){
        int k;
        double ans;

        seed ^= 123459876;
        k = seed / 127773;
        seed = 16807*(seed - k*127773) - 2836*k;
        if(seed < 0) seed += 2147483647;
        ans = seed / 2147483647.0;
        seed ^= 123459876;
        return ans;
    }

}