package leetcode.hot_100.first.medium;

import utils.AlgorithmUtils;

/**
 * @author gusixue
 * @description
 * 309. 买卖股票的最佳时机含冷冻期
 * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 * @date 2023/9/20
 */
public class MaxProfit {

    public static void main(String[] args) {
        MaxProfit maxProfit = new MaxProfit();
        while (true) {
            int[] prices = AlgorithmUtils.systemInArray();

            int res = maxProfit.solution(prices);
            System.out.println(res);
        }
    }

    /**
     * 动态规划dp：dp[i] 代表第 i 天卖出可以获得最大的收益，初始化 dp[0]=0
     * 转移方程：dp[i]=max(dp[i], dp[i-1], prices[i]-price[i-j]+dp[i-j-2]) 1<=j<=i，注意当 i-j-2 < 0 时直接不要
     * 时间优化：为方便计算，现将 dp[i] 变成 dp[i+2] 整体右移，初始化 dp[0]=dp[1]=dp[2]=0，因为 prices[i] 不变，因此每次仅需要求 dp[i-j-2]-price[i-j] 的最大值即可
     * 空间优化：由于 dp[i+2] 仅仅与 dp[i+1]、dp[i-1] 有关，因此可以只使用 dp[4] 的滚动数组，每次对 4 取模即可
     * n 为 prices.length，时间复杂度：O（n），空间复杂度：O（1）
     */
    private int solution(int[] prices) {
        // 特殊判断
        if (prices.length <= 1) {
            return 0;
        }

        // 动态规划
        int[] dp = new int[4];
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 0;

        // 转移方程：dp[i+2]=max(dp[i+1], prices[i]-price[i-j]+dp[i-j]) 1<=j<=i，注意整体右移了 2
        int maxDpPrice = Integer.MIN_VALUE;
        for (int i = 1; i < prices.length; i++) {
            maxDpPrice = Math.max(maxDpPrice, dp[(i - 1 + 4) % 4] - prices[i - 1]);
            dp[(i + 2) % 4] = Math.max(dp[(i + 1) % 4], prices[i] + maxDpPrice);
        }
//        System.out.println(Arrays.toString(dp));

        return dp[(prices.length + 1) % 4];
    }
}
