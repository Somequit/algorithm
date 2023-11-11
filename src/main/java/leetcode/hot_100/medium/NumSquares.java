package leetcode.hot_100.medium;

import utils.AlgorithmUtils;

import java.util.Arrays;

/**
 * @author gusixue
 * @description
 * 279. 完全平方数
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * 1 <= n <= 10 ^ 4
 * @date 2023/9/22
 */
public class NumSquares {

    public static void main(String[] args) {
        NumSquares numSquares = new NumSquares();
        while (true) {
            int n = AlgorithmUtils.systemInNumberInt();

            int res = numSquares.solution(n);
            System.out.println(res);
        }
    }

    /**
     * 动态规划：dp[i] 代表和为 i 需要最少多少个完全平方数，初始化 dp[0]=0
     * 转移方程：dp[i]=min(dp[i-j*j])+1，1<=j*j<=i
     * 时间复杂度：O（n*(n ^ 1/2)）；空间复杂度：O（n）
     */
    private int solution(int n) {
        // 特殊判断
        if (n <= 0) {
            return 0;
        }

        // 动态规划
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[n];
    }

}
