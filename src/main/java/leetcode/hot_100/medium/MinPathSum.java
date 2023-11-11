package leetcode.hot_100.medium;

import utils.AlgorithmUtils;

import java.util.Arrays;

/**
 * @author gusixue
 * @description
 * 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 200
 * @date 2023/9/13
 */
public class MinPathSum {

    public static void main(String[] args) {
        MinPathSum minPathSum = new MinPathSum();
        while (true) {
            int[][] grid = AlgorithmUtils.systemInTwoArray();

            int res = minPathSum.solution(grid);
            System.out.println(res);
        }
    }

    public int solution(int[][] grid) {
        // 判空
        if (grid == null || grid.length <= 0 || grid[0] == null || grid[0].length <= 0) {
            return 0;
        }

        // 初始化，为方便计算 dp 整体向右移动一格
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            dp[i + 1] = dp[i] + grid[0][i];
        }

        /**
         * 转移方程：dp[i+1][j+1] = grid[i][j] + Math.min(dp[i][j+1], dp[i+1][j])
         * 空间压缩：使用滚动数组
         */
        dp[0] = Integer.MAX_VALUE;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[j + 1] = grid[i][j] + Math.min(dp[j + 1], dp[j]);
            }
//            System.out.println(Arrays.toString(dp));
        }

        // 结果为 dp[m][n]
        return dp[n];
    }
}
