package leetcode.contest.double_118;

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
    private int solution(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];

        dp[0][0] = Integer.MAX_VALUE;
        dp[0][1] = prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][1] = prices[i] + Math.min(dp[i - 1][0], dp[i - 1][1]);
            dp[i][0] = Integer.MAX_VALUE;

            for (int j = i - 1; i - j <= j + 1 && j >= 0; j--) {
                dp[i][0] = Math.min(dp[i][0], dp[j][1]);
            }
        }

        return Math.min(dp[n - 1][0], dp[n - 1][1]);
    }


}
