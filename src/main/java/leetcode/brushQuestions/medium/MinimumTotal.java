package leetcode.brushQuestions.medium;

import java.util.List;

/**
 * @author gusixue
 * @description 120. 三角形最小路径和
 * @date 2025/9/25 12:03 下午
 */
public class MinimumTotal {

    /**
     * 动态规划：dp[i][j] 代表从头开始到第 i 行第 j 列的最小和，顺序执行到整个三角形即可，求出最后一行最小值即可；可用滚动数组优化空间
     * 优化空间：仅使用 dp[j]，倒序处理即从最后一行运行到第一行、每行需要从 0 到 n-1 正序，通项公式：dp[j] = min(dp[j], dp[j+1]) + triangle[i][j]
     * 时间复杂度：O（n^2）,空间复杂度：O（n）
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        if (len == 1) {
            return triangle.get(0).get(0);
        }

        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            dp[i] = triangle.get(len - 1).get(i);
        }

        for (int i = len - 2; i >= 0; i--) {
            for (int j = 0; j < i + 1; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }

        return dp[0];
    }
}
