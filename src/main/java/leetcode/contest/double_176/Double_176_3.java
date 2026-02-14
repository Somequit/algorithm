package leetcode.contest.double_176;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2026/2/14
 */
public class Double_176_3 {
    public long rob(int[] nums, int[] colors) {
        int n = nums.length;

        long[][] dp = new long[2][n];
        dp[1][0] = nums[0];

        for (int i = 1; i < n; i++) {
            dp[0][i] = Math.max(dp[0][i - 1], dp[1][i - 1]);

            if (colors[i] == colors[i - 1]) {
                dp[1][i] = dp[0][i - 1] + nums[i];

            } else {
                dp[1][i] = Math.max(dp[0][i - 1], dp[1][i - 1]) + nums[i];
            }
        }

        return Math.max(dp[0][n - 1], dp[1][n - 1]);
    }
}
