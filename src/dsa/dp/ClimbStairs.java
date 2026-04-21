package dsa.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Solves the classic "Climbing Stairs" problem.
 *
 * <p>Problem statement: if you can take either 1 step or 2 steps at a time,
 * find the total number of distinct ways to reach step {@code n}.</p>
 *
 * <p>This class demonstrates the progression in this order:</p>
 * <ol>
 *   <li>Pure recursion (simple but exponential time)</li>
 *   <li>Recursion + memoization (top-down dynamic programming)</li>
 *   <li>Tabulation (bottom-up dynamic programming)</li>
 * </ol>
 */
public class ClimbStairs {

    /**
     * Runs all counting approaches for a sample input and prints all valid step paths.
     */
    public static void main(String[] args) {
        int n=5;
        System.out.println("climbRecursive :  " +climbRecursive(n));
        int dp[] = new int[n+1];
        Arrays.fill(dp, -1);
        System.out.println("climbRecursiveMemooization : " + climbRecursiveMemooization(n, dp));
        System.out.println("climbTabulation : " + climbTabulation(n, dp));
        Solution.solve(5, new ArrayList<>());
    }

    /**
     * Bottom-up dynamic programming solution (tabulation).
     *
     * @param n target stair index
     * @param dp table where {@code dp[i]} stores ways to reach stair {@code i}
     * @return number of distinct ways to reach stair {@code n}
     */
    private static int climbTabulation(int n, int[] dp) {
        if(n==0)
            return 0;
        dp[0]=1;
        dp[1] =1;
        for(int i=2;i<=n;i++){
            dp[i]=dp[i-1] + dp[i-2];
        }
        return dp[n];

    }

    /**
     * Top-down dynamic programming solution (memoization).
     *
     * @param n remaining stairs to climb
     * @param dp cache where {@code dp[i]} stores precomputed result for {@code i}
     * @return number of distinct ways to finish climbing from {@code n}
     */
    private static int climbRecursiveMemooization(int n, int[] dp) {

        if (n == 0) return 1;
        if (n < 0) return 0;
         if(dp[n] != -1){
             return dp[n];
         }
         dp[n] = climbRecursiveMemooization(n - 1,dp) + climbRecursiveMemooization(n - 2,dp);
         return dp[n];
    }

    /**
     * Pure recursive solution.
     *
     * <p>This is the first and most intuitive approach but recomputes subproblems,
     * resulting in exponential time complexity.</p>
     *
     * @param n remaining stairs to climb
     * @return number of distinct ways to finish climbing from {@code n}
     */
    static int climbRecursive(int n) {
//        System.out.println(n);
        if (n == 0) return 1;
        if (n < 0) return 0;

        return climbRecursive(n - 1) + climbRecursive(n - 2);
    }


    /**
     * Utility class that prints every valid path of 1-step and 2-step moves.
     *
     * <p>Unlike the counting methods above, this helper enumerates the actual paths.</p>
     */
    static class Solution {

        public static void main(String[] args) {
            int n = 5;
            List<Integer> path = new ArrayList<>();
            solve(n, path);
        }

        /**
         * Backtracking helper that prints all step sequences reaching exactly 0.
         *
         * @param n remaining stairs
         * @param path current sequence of taken steps
         */
        static void solve(int n, List<Integer> path) {

            // ✅ Base case: reached exactly 0
            if (n == 0) {
                System.out.println(path);
                return;
            }

            // ❌ Invalid case: overshoot
            if (n < 0) {
                return;
            }

            // Option 1: take 1 step
            path.add(1);
            solve(n - 1, path);
            path.remove(path.size() - 1); // backtrack

            // Option 2: take 2 steps
            path.add(2);
            solve(n - 2, path);
            path.remove(path.size() - 1); // backtrack
        }
    }
}
