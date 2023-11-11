package leetcode.hot_100.medium;

import utils.AlgorithmUtils;

import java.util.Arrays;

/**
 * @author gusixue
 * @description
 * 322. 零钱兑换
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 2^31 - 1
 * 0 <= amount <= 10^4
 * @date 2023/5/12
 */
public class CoinChange {

    public static void main(String[] args) {
        while (true) {
            int[] coins = AlgorithmUtils.systemInArray();
            int amount = AlgorithmUtils.systemInNumberInt();
            int res = solution(coins, amount);
            System.out.println(res);
        }
    }

    /**
     * 动态规划之完全背包：
     * 定义一维数组 dp，其中 dp[i] 表示组成总金额 i 所需的最少硬币数
     * 初始化 dp 数组，dp[0] 为 0，表示组成金额 0 需要 0 个硬币，dp[1...amount] 初始化为极大值，表示当前无法组成该总金额
     * 遍历硬币数组 coins，对于每种面额的硬币，遍历总金额范围内可以添加该硬币的金额下标。即 dp[j] 不为极大值，说明可以组成 j + coins[i] 金额，此时转移方程为：dp[j + coins[i]] = Math.min(dp[j + coins[i]], dp[j] + 1)
     * 遍历结束后，dp[amount] 如果仍为极大值，则无法组成，返回 -1；否则返回 dp[amount] 表示最少需要的硬币数
     * PS：由于 amount 最多由 amount 个硬币组成，因此初始化极大值只要大于 amount 就可以
     */
    private static int solution(int[] coins, int amount) {
        // 判空
        if (amount == 0) {
            return 0;
        }
        if (coins == null || coins.length <= 0) {
            return -1;
        }

        // 定义且初始化 dp 数组
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, 1, dp.length, Integer.MAX_VALUE);

        // 循环添加每一种硬币
        int coinsLen = coins.length;
        int dpLen = dp.length;
        for (int i = 0; i < coinsLen; i++) {
            for (int j = 0; j < dpLen - coins[i]; j++) {
                if (dp[j] < Integer.MAX_VALUE) {
                    dp[j + coins[i]] = Math.min(dp[j + coins[i]], dp[j] + 1);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
