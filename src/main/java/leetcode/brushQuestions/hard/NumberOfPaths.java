package leetcode.brushQuestions.hard;

/**
 * @author gusixue
 * @description 2435. 矩阵中和能被 K 整除的路径
 * @date 2025/11/26 7:14 下午
 */
public class NumberOfPaths {

    public int numberOfPaths(int[][] grid, int k) {
        int mod = (int) (1e9 + 7);
        int m = grid.length;
        int n = grid[0].length;
        int[][][] dp = new int[m][n][k];
        dp[0][0][grid[0][0] % k] = 1;
        for (int i = 1; i < m; i++) {
            for (int l = 0; l < k; l++) {
                dp[i][0][(l + grid[i][0]) % k] = dp[i - 1][0][l];
            }
        }
        for (int i = 1; i < n; i++) {
            for (int l = 0; l < k; l++) {
                dp[0][i][(l + grid[0][i]) % k] = dp[0][i - 1][l];
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                for (int l = 0; l < k; l++) {
                    dp[i][j][(l + grid[i][j]) % k] = (dp[i - 1][j][l] + dp[i][j - 1][l]) % mod;
                }
            }
        }

        return dp[m - 1][n - 1][0];
    }
}
