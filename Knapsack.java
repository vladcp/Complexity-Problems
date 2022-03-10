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
    int[] x;
    // best found solution vector
    int[] x_best;
    //associated best total value
    int v_max = 0;
    int step_counter = 0;

    public Knapsack(int C, int[] v, int[] w){
        this.C = C;
        this.v = v;
        this.w = w;
        n = v.length;
        x_best = new int[n];
        x = new int[n];
        Arrays.fill(x_best, 0);
        Arrays.fill(x, 0);
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
    public void Enum(int z, int v_curr, int w_curr, int[] x){
        step_counter++;
        if(w_curr <= C){
            if(v_curr > v_max){
                v_max = v_curr;
                x_best = Arrays.copyOf(x, x.length);
            }
            if(z < n - 1){
                z++;
                x[z] = 1;
                Enum(z, v_curr + v[z], w_curr + w[z], x);
                x[z] = 0;
                Enum(z, v_curr, w_curr,x);
            }
        }
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
        System.out.print("The items chosen are: ");
        for (int i : p.x_best) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("Number of recursive calls: " + p.step_counter);
    }


    public static void main(String[] args) {
        int[] val = {80,20,63};
        int[] weights = {32,16,21};
        int C = 50;
        TestEnumSimple(C, val, weights);
    }
}