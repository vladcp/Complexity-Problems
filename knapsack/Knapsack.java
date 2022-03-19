package knapsack;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

/**
 * Class that tests different solutons to the Knapsack problem
 * Created by: Vlad-Cristian Prisacariu 
 * Date modified: 10-03-2022
 */
import java.util.*;
public class Knapsack{
    public class Item implements Comparable<Item>{
        int index;
        int value;
        int weight;
        double relative_value;
        boolean must_include;
        public Item(int v, int w, int i){
            index = i+1;
            value = v;
            weight = w;
            relative_value = (double)v/w;
            must_include = false;
        }
        public double getRelative_value() {
            return relative_value;
        }
        public String toString(){
            String string = "";
            string += value + " " + weight + " " + relative_value + "\n";
            return string;
        }
        @Override
        public int compareTo(Item i) {
            if(relative_value > i.relative_value) return -1;
            else if(relative_value > i.relative_value) return 0;
            return 1;
        }
    }
    //Maximum capacity
    int C;
    //items vector
    Item[] items;

    //must include items
    Item[] mustInclude_items;
    //number of items
    int n;
    //current solution vector
    boolean[] x;
    // best found solution vector
    boolean[] x_best;
    //associated best total value
    int v_max = 0;
    int step_counter = 0;

    int LB;
    int UB;

    public Knapsack(int C, int[] v, int[] w){
        this.C = C;
        n = v.length;

        items = new Item[n];
        for(int i = 0; i < n; i++){
            items[i] = new Item(v[i],w[i],i);
        }
        x_best = new boolean[n];
        x = new boolean[n];
        Arrays.fill(x_best, false);
        Arrays.fill(x, false);
    }

    public Knapsack(int C, Item[] i, Item[] must){
        this.C = C;
        n = i.length;
        items = i;
        mustInclude_items = must;
        x_best = new boolean[n];
        x = new boolean[n];
        Arrays.fill(x_best, false);
        Arrays.fill(x, false);
    }
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
    public double upperBound(){
        double upperBound = 0;
        int capCopy = C;
        int i = 0;
        while(i < mustInclude_items.length && mustInclude_items[i].weight < capCopy){
            upperBound += mustInclude_items[i].value;
            capCopy -= mustInclude_items[i].weight;
            i++;
        }
        if(i != mustInclude_items.length){
            upperBound += mustInclude_items[i].value * (double)capCopy/mustInclude_items[i].weight;
        }
        i = 0;
        while(i < items.length && items[i].weight < capCopy){
            upperBound += items[i].value;
            capCopy -= items[i].weight;
            i++;
        }
        if(i != items.length){
            upperBound += items[i].value * (double)capCopy/items[i].weight;
        }
        return upperBound;
    }
    public int lowerBound(){
        int lowerBound = 0;
        int capCopy = C;
        for(int i = 0; i < mustInclude_items.length; i++){
            if(mustInclude_items[i].weight < capCopy){
                lowerBound += mustInclude_items[i].value;
                capCopy -= mustInclude_items[i].weight;
                x_best[mustInclude_items[i].index] = true;
            }
            else return -1;
        }
        for(int i = 0; i < items.length; i++){
            if(items[i].weight < capCopy){
                lowerBound += items[i].value;
                capCopy -= items[i].weight;
                x_best[mustInclude_items[i].index] = true;
            }
        }
        return lowerBound;
    }
    /**
     * Display current state of knapsack object
     */
    
    public static void BranchAndBoundMax(Knapsack k){
        int L = Integer.MIN_VALUE;
        ArrayList<Knapsack> instances = new ArrayList<>();
        instances.add(k);
        while(instances.size() > 0){
            Knapsack curInstance = instances.get(0);
            instances.remove(0);
            double l_upperBound = curInstance.upperBound();
            if(l_upperBound > L){
                int l_lowerBound = curInstance.lowerBound();
                if(l_lowerBound == -1) continue;
                if(l_lowerBound > L) L = l_lowerBound;
                if(l_upperBound > L){
                    //partition in subinstances 
                    // Knapsack i1 = new Knapsack(k.C,)
                    //add them to arraylist
                }
            }
        }
    //return best sol with highest value L
    }

     public void displaySolution(){
        int total_weight = 0;
        System.out.println("The chosen items are: ");
        for(int i = 0; i < n; i++){
            if(x_best[i]) {
                System.out.print(items[i].index + " ");
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
    public static void main(String[] args) {
        try {
            File file = new File("text.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                System.out.println(data);
              }
              scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
            //TODO: handle exception
        }
    }
}