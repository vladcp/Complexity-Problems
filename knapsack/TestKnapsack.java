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
    public static void main(String[] args) throws Exception {
        try {
            File file = new File("file.txt");
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
        int[] val = {92,57,49,68,60,43,67,84,87,72};
        int[] weights = {23,31,29,44,53,38,63,85,89,82};
        int C = 165;
        //TestEnumSimple(C, val, weights);
        TestEnum_UB(C, val, weights);
    }
}
