package leetcode.contest.contest_394;

import java.util.*;
/**
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

//            int res = contest.solution(n, nums, list);
//            System.out.println(res);
        }

    }

    /**
     * @return
     */
    public int minimumOperations(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        // j 列改成 0-9 的最小值，与左侧不同即可
        int[][] dp = new int[m][10];

        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {

                for (int k = 0; k < 10; k++) {
                    if (grid[i][j] != k) {
                        dp[j][k]++;
                    }
                }
            }
        }


        for (int j = 1; j < m; j++) {
            for (int k = 0; k < 10; k++) {
                int minVal = Integer.MAX_VALUE;

                for (int kPre = 0; kPre < 10; kPre++) {
                    if (k != kPre) {
                        minVal = Math.min(minVal, dp[j - 1][kPre]);
                    }
                }

                dp[j][k] += minVal;
            }
        }
//        Arrays.stream(dp).forEach(arr->System.out.println(Arrays.toString(arr)));

        int res = Integer.MAX_VALUE;
        for (int k = 0; k < 10; k++) {
            res = Math.min(res, dp[m - 1][k]);
        }
        return res;
    }


}
