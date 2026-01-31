package leetcode.contest.double_175;

import java.util.*;

/**
 * @author gusixue
 * @description TODO:GSX:斜率优化DP
 * @date 2026/1/31 10:29 下午
 */
public class Double_175_4 {
    public long minPartitionScore(int[] nums, int k) {
        int n = nums.length;
        long res = 0;
        for (int num : nums) {
            res += num;
        }

        long[][] dp = new long[n + 1][k + 1];
        long[] prefix = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
            dp[i][1] = prefix[i] * prefix[i];
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 2; j <= Math.min(i, k); j++) {

            }
        }

        return (res + dp[n][k]) / 2;
    }
}
