package knapsack;
public class Knapsack_DP {
    public static class Knapsack{
        int[] values;
        int[] weights;
        int C;
        public Knapsack(int c, int[] v, int[] w){
            values = v;
            weights = w;
            C = c;
        }
        /**
         * Maximal value for subset 1..i 
         * @param i index where subset ends
         * @param c capacity
         */
        public int OPT(int i, int c){
            if(i == 0 || c==0) return 0;
            if(weights[i] > c) return OPT(i-1,c);
            return Math.max(OPT(i-1,c), values[i] + OPT(i-1,c-weights[i]));
        }
    }
    private static void displayMatrix(int[][] m){
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void Knapsack(int C, int[] values, int[] weights){
        Knapsack k = new Knapsack(C, values, weights);
        int[][] dp = new int[values.length][C+1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if(i == 0 || j == 0) dp[i][j] = 0;
                else dp[i][j] = k.OPT(i, j);
            }
        }
        displayMatrix(dp);
    }
    public static void main(String[] args) {
        int C = 11;
        int[] values = {0 ,1,6,18,22,28};
        int[] weights = {0, 1,2,5,6,7};
        Knapsack(C, values, weights);
    }
}