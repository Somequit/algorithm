package leetcode.hot_100.first.medium;

import utils.AlgorithmUtils;

import java.util.Arrays;

/**
 * @author gusixue
 * @description
 * 62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 10^9
 * @date 2023/5/23
 */
public class UniquePaths {

    public static void main(String[] args) {
        UniquePaths uniquePaths = new UniquePaths();
        while (true) {
            int m = AlgorithmUtils.systemInNumberInt();
            int n = AlgorithmUtils.systemInNumberInt();
            
            int res = uniquePaths.solution(m, n);
            System.out.println(res);
        }
    }

    /**
     * 动态规划：标准动规设置 dp[i][j] 代表走到 （i, j）路径数，初始化：i=0/j=0 时 dp[i][j]=1
     * 由于当前位置仅能从上或者左方走到，动规转移方程：dp[i][j] = dp[i-1][j] + dp[i][j-1]
     * 空间压缩：根据动规转移方程，可知仅需要上一行与当前行的数据，又因为两行都是加在一起，因此使用循环数组空间压缩仅需要长度为 n 的一位数组
     * PS：由于 m 与 n 可以交换，因此空间复杂度还可以进一步压缩成 O（min（m，n））
     * 时间复杂度：O（m*n），空间复杂度：O（n）
     */
    private int solution(int m, int n) {
        // 直接判断
        if (m == 1 || n == 1) {
            return 1;
        }
        // 初始化与循环数组
        int[] pathsDp = new int[n];
        Arrays.fill(pathsDp, 1);

        // 循环动态规划
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                pathsDp[j] += pathsDp[j-1];
            }
        }

        return pathsDp[n-1];
    }
}
