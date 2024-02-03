package leetcode.brushQuestions.medium;

/**
 * @author gusixue
 * @description 1690. 石子游戏 VII
 * @date 2024/2/3
 */
public class StoneGameVII {

    /**
     * Java + dp + 前缀和
     */
    public int stoneGameVII(int[] stones) {
        int n = stones.length;

        // 石头堆在 i 到 j 时，自己减去对方的最大得分差（可能是爱丽丝、也可能是鲍勃）
        int[][] dp = new int[n][n];
        // 初始化 dp[i][i]=0 即删完后没有分差，其他为 -1 代表记忆化求出
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    dp[i][j] = -1;
                }
            }
        }

        // 前缀和求区间和（方便求解，向右移动一位）
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + stones[i - 1];
        }

        // 使用记忆化搜索解决
        memorySearch(dp, 0, n - 1, prefixSum);

        return dp[0][n - 1];
    }

    private int memorySearch(int[][] dp, int left, int right, int[] prefixSum) {
        if (right - left <= 0) {
            return 0;
        }
        if (dp[left][right] != -1) {
            return dp[left][right];
        }

        // 选左侧或者右侧，保证分差最大
        dp[left][right] = Math.max(prefixSum[right + 1] - prefixSum[left + 1] - memorySearch(dp, left + 1, right, prefixSum),
                prefixSum[right] - prefixSum[left] - memorySearch(dp, left, right - 1, prefixSum));

        return dp[left][right];
    }
}
