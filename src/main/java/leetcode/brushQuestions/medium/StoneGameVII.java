package leetcode.brushQuestions.medium;

/**
 * @author gusixue
 * @description 1690. 石子游戏 VII
 * @date 2024/2/3
 */
public class StoneGameVII {

    /**
     * Java + dp + 前缀和
     *
     */
    public int stoneGameVII(int[] stones) {
        int n = stones.length;

        // 第 i 次，最左边为第 j 个石头，0-爱丽丝的得分 1-鲍勃的得分
        int[][][] dp = new int[n][n + 1][2];

        // 前缀和求区间和（方便求解，向右移动一位）
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + stones[i - 1];
        }

        // 初始化第一次爱丽丝扩大得分的差值
        dp[0][0][0] = Math.max(prefixSum[n - 1] - prefixSum[0], prefixSum[n] - prefixSum[1]);
        dp[0][1][0] = dp[0][0][0];

        // 循环每次游戏
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i + 2; j++) {
                // 爱丽丝扩大得分的差值
                if (i % 2 == 0) {
                    // 选左或者选右的最大得分的差值，求出 [j, j+n-i-1)
                    if (j == 0 || (j != i + 1 && dp[i - 1][j - 1][0] - dp[i - 1][j - 1][1] < dp[i - 1][j][0] - dp[i - 1][j][1])) {
                        dp[i][j][1] = dp[i - 1][j][1];
                        dp[i][j][0] = dp[i - 1][j][0] + (prefixSum[j + n - i - 1] - prefixSum[j]);

                    } else {
                        dp[i][j][1] = dp[i - 1][j - 1][1];
                        dp[i][j][0] = dp[i - 1][j - 1][0] + (prefixSum[j + n - i - 1] - prefixSum[j]);
                    }

                    // 鲍勃减小得分的差值
                } else {
                    dp[i][j][0] = dp[i - 1][j][0];
                    // 选左或者选右的最小得分的差值，求出 [j, j+n-i-1)
                    if (j == 0 || (j != i + 1 && dp[i - 1][j - 1][0] - dp[i - 1][j - 1][1] > dp[i - 1][j][0] - dp[i - 1][j][1])) {
                        dp[i][j][0] = dp[i - 1][j][0];
                        dp[i][j][1] = dp[i - 1][j][1] + (prefixSum[j + n - i - 1] - prefixSum[j]);

                    } else {
                        dp[i][j][0] = dp[i - 1][j - 1][0];
                        dp[i][j][1] = dp[i - 1][j - 1][1] + (prefixSum[j + n - i - 1] - prefixSum[j]);
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++) {
                System.out.print(dp[i][j][0] + " : " + dp[i][j][1] + "\t");
            }
            System.out.println();
        }

        int res = 0;
        // 最后一次游戏，爱丽丝扩大得分的差值
        if ((n - 1) % 2 == 0) {
            res = Integer.MIN_VALUE;
            for (int j = 0; j <= n; j++) {
                res = Math.max(res, dp[n - 1][j][0] - dp[n - 1][j][1]);
            }

            // 最后一次游戏，鲍勃减小得分的差值
        } else {
            res = Integer.MAX_VALUE;
            for (int j = 0; j <= n; j++) {
                res = Math.min(res, dp[n - 1][j][0] - dp[n - 1][j][1]);
            }
        }

        return res;
    }
}
