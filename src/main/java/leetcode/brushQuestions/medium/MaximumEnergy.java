package leetcode.brushQuestions.medium;

/**
 * @author gusixue
 * @description 3147. 从魔法师身上吸取的最大能量
 * @date 2025/10/10 2:26 下午
 */
public class MaximumEnergy {

    /**
     * 倒序处理+dp 存储maximumTotalDamage
     */
    public int maximumEnergy(int[] energy, int k) {
        int n = energy.length;
        int[] dp = new int[n];

        dp[n - 1] = energy[n - 1];
        int res = dp[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            dp[i] = energy[i] + ((i + k) >= n ? 0 : dp[i + k]);
            res = Math.max(res, dp[i]);
        }

        return res;
    }
}
