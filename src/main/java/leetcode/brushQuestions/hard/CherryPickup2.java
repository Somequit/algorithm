package leetcode.brushQuestions.hard;

import java.util.Arrays;

/**
 * @author gusixue
 * @description 1463. 摘樱桃 II
 * @date 2024/5/7
 */
public class CherryPickup2 {

    /**
     * 设计两个机器人同时走 i 步后分别到达 [i, j] 与 [i, k] 获得最多的樱桃数，
     * 设计状态 dp[i][j][k]，两个人分别从 上直/上左/上右 * 上直/上左/上右 九个方向来的最大值，同时注意当前越界，注意如果位置重复樱桃仅能加一次，
     * dp[i][j][k] = max(dp[i-1][j][k], dp[i-1][j][k-1], dp[i-1][j][k+1],
     *                  dp[i-1][j-1][k], dp[i-1][j-1][k-1], dp[i-1][j-1][k+1],
     *                  dp[i-1][j+1][k], dp[i-1][j+1][k-1], dp[i-1][j+1][k+1]) + grid[i][j] + j==k?0:grid[i][k]
     * 由于 i 仅与 i-1 相关，所以可以使用滚动数组
     */
    public int cherryPickup(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int infMin = Integer.MIN_VALUE;

        // 滚动数组，j、k 均向后一位同时最后留一位避免越界
        int[][][] dp = new int[2][cols + 2][cols + 2];

        // 初始化均不能走，初始化开始位置
        Arrays.stream(dp[0]).forEach(arr -> Arrays.fill(arr, infMin));
        dp[0][1][cols] = grid[0][0] + grid[0][cols - 1];

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols + 1; j++) {
                for (int k = 1; k < cols + 1; k++) {
                    int ci = i % 2;
                    int pi = (i - 1) % 2;

                    dp[ci][j][k] = maxAll(dp[pi][j][k], dp[pi][j][k - 1], dp[pi][j][k + 1],
                            dp[pi][j - 1][k], dp[pi][j - 1][k - 1], dp[pi][j - 1][k + 1],
                            dp[pi][j + 1][k], dp[pi][j + 1][k - 1], dp[pi][j + 1][k + 1])
                            + grid[i][j - 1] + (j == k ? 0 : grid[i][k - 1]);
                }
            }
        }

        int res = 0;
        for (int i = 1; i < cols + 1; i++) {
            for (int j = 1; j < cols + 1; j++) {
                res = Math.max(res, dp[(rows - 1) % 2][i][j]);
            }
        }
        return res;
    }

    private int maxAll(int... nums) {
        int res = nums[0];
        for (int num : nums) {
            res = Math.max(res, num);
        }

        return res;
    }
}
