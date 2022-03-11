/**
 * Class that tests different solutons to the Knapsack problem
 * Created by: Vlad-Cristian Prisacariu 
 * Date modified: 10-03-2022
 */
import java.util.*;
class Knapsack{
    //Maximum capacity
    int C;
    //values vector
    int[] v;
    //weights vector
    int[] w;
    //number of items
    int n;
    //current solution vector
    boolean[] x;
    // best found solution vector
    boolean[] x_best;
    //associated best total value
    int v_max = 0;
    int step_counter = 0;

    public Knapsack(int C, int[] v, int[] w){
        this.C = C;
        this.v = v;
        this.w = w;
        n = v.length;
        x_best = new boolean[n];
        x = new boolean[n];
        Arrays.fill(x_best, false);
        Arrays.fill(x, false);
    }
    // x is the current solution vector 
    //it stores 1 on indices of chosen items, 0 for rest

    /**
     * Inefficient EXP solution for the knapsack problem
     * Note: first call Enum(-1,0,0,x)
     * @param z current item, needs to be initialised with -1
     * @param v_curr current total value at a given step
     * @param w_curr current total weight at a given step
     * @param x current solution vector
     */
    public void Enum(int z, int v_curr, int w_curr, boolean[] x){
        step_counter++;
        if(w_curr <= C){
            if(v_curr > v_max){
                v_max = v_curr;
                x_best = Arrays.copyOf(x, x.length);
            }
            if(z < n - 1){
                z++;
                x[z] = true;
                Enum(z, v_curr + v[z], w_curr + w[z], x);
                x[z] = false;
                Enum(z, v_curr, w_curr,x);
            }
        }
    }
    /**
     * Display current state of knapsack object
     */
    public void displaySolution(){
        System.out.println("The chosen items are: ");
        System.out.print("Weights: ");
        for(int i = 0; i < n; i++){
            if(x_best[i]) System.out.print(w[i] + " ");
        }
        System.out.println();
        System.out.print("Values: ");
        for(int i = 0; i < n; i++){
            if(x_best[i]) System.out.print(v[i] + " ");
        }
        System.out.println();
        System.out.println("Number of recursive calls: " + step_counter);
    }
    /**
     * Test method for the unimproved enumeration method
     * @param capacity
     * @param values
     * @param weights
     */
    public static void TestEnumSimple(int capacity, int[] values, int[] weights){
        Knapsack p = new Knapsack(capacity, values, weights);
        p.Enum(-1, 0, 0, p.x);
        p.displaySolution();
    }

    public static void main(String[] args) {
        int[] val = {80,20,63};
        int[] weights = {32,16,21};
        int C = 50;
        TestEnumSimple(C, val, weights);
    }
}