package leetcode.contest.contest_397;

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
    public int maxScore(List<List<Integer>> grid) {
        int res = Integer.MIN_VALUE;

        int m = grid.size();
        int n = grid.get(0).size();
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i + j == 0) {
                    dp[0][0] = Integer.MAX_VALUE;
                    continue;
                }

                if (i == 0) {
                    dp[i][j] = Math.min(grid.get(i).get(j - 1), dp[i][j - 1]);
                    res = Math.max(res, grid.get(i).get(j) - dp[i][j]);

                } else if (j == 0) {
                    dp[i][j] = Math.min(grid.get(i - 1).get(j), dp[i - 1][j]);
                    res = Math.max(res, grid.get(i).get(j) - dp[i][j]);

                } else {
                    dp[i][j] = Math.min(Math.min(grid.get(i - 1).get(j), grid.get(i).get(j - 1)), Math.min(dp[i - 1][j], dp[i][j - 1]));
                    res = Math.max(res, grid.get(i).get(j) - dp[i][j]);
                }
            }
        }

        return res;
    }


}
