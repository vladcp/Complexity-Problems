/**
 * Class that tests different solutons to the Knapsack problem
 * Created by: Vlad-Cristian Prisacariu 
 * Date modified: 10-03-2022
 */
import java.util.*;
class Knapsack{
    class Item{
        int value;
        int weight;
        double relative_value;
        public Item(int v, int w){
            value = v;
            weight = w;
            relative_value = (double)v/w;
        }
    }
    //Maximum capacity
    int C;
    //items vector
    Item[] items;
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
        n = v.length;

        items = new Item[n];
        for(int i = 0; i < n; i++){
            items[i] = new Item(v[i],w[i]);
        }
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
                Enum(z, v_curr + items[z].value, w_curr + items[z].weight, x);
                x[z] = false;
                Enum(z, v_curr, w_curr,x);
            }
        }
    }
    /**
     * Slightly improved solution using only an upper bound to
     * eliminate some branches of the search tree
     * Note: first call Enum(-1,0,0,x)
     * @param z current item, needs to be initialised with -1
     * @param v_curr current total value at a given step
     * @param w_curr current total weight at a given step
     * @param x current solution vector
     */
    public void Enum_UB(int z, int v_curr, int w_curr, boolean[] x){
        step_counter++;
        if(w_curr <= C){
            if(v_curr > v_max){
                v_max = v_curr;
                x_best = Arrays.copyOf(x, x.length);
            }
            if(z < n - 1){
                z++;
                double upper_bound = v_curr + (C - w_curr) * ((double)items[z].value/items[z].weight);
                if(upper_bound > v_max){
                    x[z] = true;
                    Enum_UB(z, v_curr + items[z].value, w_curr + items[z].weight, x);
                    x[z] = false;
                    Enum_UB(z, v_curr, w_curr,x);
                }
            }
        }
    }
    /**
     * Display current state of knapsack object
     */
    public void displaySolution(){
        int total_weight = 0;
        System.out.println("The chosen items are: ");
        for(int i = 0; i < n; i++){
            if(x_best[i]) {
                System.out.print(i+1 + " ");
            }
        }
        System.out.println();
        System.out.print("Weights: ");
        for(int i = 0; i < n; i++){
            if(x_best[i]) {
                System.out.print(items[i].weight + " ");
                total_weight += items[i].weight;
            }
        }
        System.out.println();
        System.out.print("Values: ");
        for(int i = 0; i < n; i++){
            if(x_best[i]) System.out.print(items[i].value + " ");
        }
        System.out.println();
        System.out.println("Total best value: " + v_max);
        System.out.println("Total weight: " + total_weight + "/ " + C);
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
    public static void TestEnum_UB(int capacity, int[] values, int[] weights){
        Knapsack p = new Knapsack(capacity, values, weights);
    }
    public static void main(String[] args) {
        int[] val = {80,20,63};
        int[] weights = {32,16,21};
        int C = 50;
        TestEnumSimple(C, val, weights);
    }
}