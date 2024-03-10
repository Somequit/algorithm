package leetcode.contest.contest_388;

import java.util.*;
/**
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

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
    public long maximumStrength(int[] nums, int k) {
        int n = nums.length;
        // dp[i][j] 代表前 i 个元素分成 k 段的最大能量值，n、k 均向右移一位方便计算
        long[][] dp = new long[n + 1][k + 1];
        // 结果最小值肯定大于 -10^9*10^4
        long inf = (long) (-1e13);
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(dp[i], inf);
            // 分成 0 段一定为 0
            dp[i][0] = 0;
        }

        long[] prefixSum = new long[n + 1];
        for (int i = 1; i < n + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        // 优化 dp[l][j - 1] +/- (prefixSum[i] - prefixSum[l]) * (k - j + 1)，将每个 j 分别放入数组中
        long[] maxColDp = new long[k + 1];
        Arrays.fill(maxColDp, inf);

        for (int i = 1; i < n + 1; i++) {

//            for (int j = 1; j < Math.min(k + 1, i + 1); j++) {
//                // 第 i 个元素不选
//                dp[i][j] = dp[i - 1][j];
//
//                // 第 i 个元素选择，同时枚举该段开始位置[j-1, i-1]
//                for (int l = j - 1; l < i; l++) {
//                    if (j % 2 == 1) {
//                        dp[i][j] = Math.max(dp[i][j], dp[l][j - 1] + (prefixSum[i] - prefixSum[l]) * (k - j + 1));
//
//                    } else {
//                        dp[i][j] = Math.max(dp[i][j], dp[l][j - 1] - (prefixSum[i] - prefixSum[l]) * (k - j + 1));
//                    }
//                }
//            }

            // 选择第 i 个元素
            for (int j = 1; j < Math.min(k + 1, i + 1); j++) {
                if (j % 2 == 1) {
                    /**
                     * 将 l 改为 i-1，同时注意 (prefixSum[i-1]-prefixSum[i-2]) 需要改为 (prefixSum[i]-prefixSum[i-2])，
                     * 因此 maxColDp[j] 需要 +(prefixSum[i]-prefixSum[i-2])-(prefixSum[i-1]-prefixSum[i-2]) 即 +(prefixSum[i]-prefixSum[i - 1])，
                     * 所以结果为 maxColDp[j], dp[i - 1][j - 1] 先求最大值再 + (prefixSum[i] - prefixSum[i - 1]) * (k - j + 1)，
                     */

                    maxColDp[j] = Math.max(maxColDp[j], dp[i - 1][j - 1]) + (prefixSum[i] - prefixSum[i - 1]) * (k - j + 1);

                } else {
                    maxColDp[j] = Math.max(maxColDp[j], dp[i - 1][j - 1]) - (prefixSum[i] - prefixSum[i - 1]) * (k - j + 1);
                }
            }

            for (int j = 1; j < Math.min(k + 1, i + 1); j++) {
                // 第 i 个元素 不选 或 选时的最大值
                dp[i][j] = Math.max(dp[i - 1][j], maxColDp[j]);
            }


        }

        return dp[n][k];
    }


}
