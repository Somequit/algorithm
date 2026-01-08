package leetcode.brushQuestions.hard;

import java.util.Arrays;
import java.util.Map;

/**
 * @author gusixue
 * @description 1458. 两个子序列的最大点积
 * @date 2026/1/8 11:06 下午
 */
public class MaxDotProduct {

    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[][] dp = new int[n + 1][m + 1];
        // 无论如何只能返回非正数
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < m; j++) {
                res = Math.max(res, nums1[i] * nums2[j]);
            }
        }
        if (res <= 0) {
            return res;
        }

        // 一定返回正数
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                // 注意结果是必须最少选择一对，因此分解成的子问题也必须最少选择一对
                dp[i][j] = Math.max(
                        // nums1[i-1]不选 或 nums2[j-1]不选
                        Math.max(dp[i - 1][j], dp[i][j - 1]),
                        // 仅选nums1[i - 1]*nums2[j - 1] 或 选nums1[i - 1]*nums2[j - 1]后还要选
                        nums1[i - 1] * nums2[j - 1] + Math.max(0, dp[i - 1][j - 1])
                );
            }
        }

        return dp[n][m];
    }

}
