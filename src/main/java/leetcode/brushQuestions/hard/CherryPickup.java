package leetcode.brushQuestions.hard;

import java.util.Arrays;

/**
 * @author gusixue
 * @description 741. 摘樱桃
 * @date 2024/5/6
 */
public class CherryPickup {

    public static void main(String[] args) {
        new CherryPickup().cherryPickup(new int[][]{});
    }

    /**
     * 从 s=[0,0] 到 e=[n-1,n-1] 来回走一遍可以看做从 s 到 e 两个人分开走，
     * 设计两个人同时走 i 步后分别到达 [i1, j] 与 [i2, k] 获得最多的樱桃数，由于仅能向下/右走，因此 i1=i-j，i2=i-k 是确定的，
     * 设计状态 dp[i][j][k]，两个人分别从 上上、上左、左左、左上 四个方向来的最大值，同时注意当前可以走(栅栏、越界)，注意如果位置重复且有樱桃则仅能加一次，
     * dp[i][j][k] = max(dp[i-1][j][k], dp[i-1][j][k-1], dp[i-1][j-1][k-1], dp[i-1][j-1][k]) + grid[i-j][j] + j==k?0:grid[i-k][k]，0<=i-j<n、0<=i-k<n
     * 由于 i 仅与 i-1 相关，所以可以空间优化掉 i，同时注意 j、k 循环需要倒序（类似 01 背包）
     */
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int inf = Integer.MAX_VALUE >> 2;

        // j、k 均向后移动一格方便计算
        int[][] dp = new int[n + 1][n + 1];
        // 初始化所有地方均不能走
        Arrays.stream(dp).forEach(arr -> Arrays.fill(arr, -inf));
        // 初始化开始的位置
        dp[1][1] = grid[0][0];

        for (int i = 1; i < n * 2 - 1; i++) {
            for (int j = n; j >= 1; j--) {
                for (int k = n; k >= 1; k--) {
                    // 当前步可以走(栅栏、越界)
                    int i1 = i - j + 1;
                    int i2 = i - k + 1;
                    if (i1 < 0 || i1 >= n || i2 < 0 || i2 >= n || grid[i1][j - 1] == -1 || grid[i2][k - 1] == -1) {
                        dp[j][k] = -inf;
                        continue;
                    }

                    dp[j][k] = Math.max(Math.max(dp[j][k], dp[j][k - 1]),
                            Math.max(dp[j - 1][k - 1], dp[j - 1][k]))
                            + grid[i1][j - 1] + (j == k ? 0 : grid[i2][k - 1]);
                }
            }
        }

        return dp[n][n] < 0 ? 0 : dp[n][n];
    }
}
