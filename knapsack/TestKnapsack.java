package knapsack;
import java.util.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class TestKnapsack {
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
    /**
     * Test method for the enumeration method with upper bound
     * @param capacity
     * @param values
     * @param weights
     */
    public static void TestEnum_UB (int capacity, int[] values, int[] weights) throws Exception {
        if(values.length != weights.length) throw new Exception("Number of values should be equal to the number of weights!");
        if(capacity <= 0) throw new Exception("Capacity should be positive!");
        Knapsack p = new Knapsack(capacity, values, weights);
        Arrays.sort(p.items);
        p.Enum_UB(-1, 0, 0, p.x);
        p.displaySolution();
        //sort items based on relative value
    }

    public static void TestBranchAndBoundMax(int capacity, int[] values, int[] weights){
        Knapsack p = new Knapsack(capacity, values, weights);
        Arrays.sort(p.items);
        p.Enum_UB(-1, 0, 0, p.x);
        p.displaySolution();
        //sort items based on relative value
    }
    public static void main(String[] args) {
        
    }
}
