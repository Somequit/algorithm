package leetcode.hot_100.easy;

import utils.AlgorithmUtils;

/**
 * 121. 买卖股票的最佳时机
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * @author gusixue
 * @date 2022/9/7
 */
public class MaxProfit {

    public static void main(String[] args) {
        while (true) {
            int[] prices = AlgorithmUtils.systemInArray();
            MaxProfit maxProfit = new MaxProfit();

            int res = maxProfit.solution(prices);
            System.out.println(res);
        }
    }

    private int solution(int[] prices) {
        if (prices == null || prices.length <= 0) {
            return 0;
        }

        int prifit = 0;
        int suffixMax = prices[prices.length - 1];

        // 一边取所有后缀的最大值、一边计算与前一个数的差取得差的最大值（均为负数则默认值为0）
        for (int i = prices.length - 2; i >=0 ; i--) {
            prifit = Math.max(prifit, suffixMax - prices[i]);
            suffixMax = Math.max(suffixMax, prices[i]);
        }

        return prifit;
    }
}
