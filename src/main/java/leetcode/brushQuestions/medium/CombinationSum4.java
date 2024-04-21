package leetcode.brushQuestions.medium;

import java.util.Arrays;

/**
 * @author gusixue
 * @description 377. 组合总和 Ⅳ
 * @date 2024/4/22
 */
public class CombinationSum4 {

    /**
     * 完全背包 + 排列组合
     * 顺序不同的序列被视作不同的组合，改为 dp[i][j][k][l]：前 i 个元素，总共选择 j 个元素总和为 k 的总个数
     * dp[i][j][k] += dp[i-1][j-l][k-nums[i]*l] * C(j, l)，i 可空间压缩掉
     * C(j, l) 代表 nums[i] 选择 l 个元素的可能性：总共 j 个位置，无序选择 l 个 nums[i] 放入然后剩余位置放置原有的元素
     *
     */
    public int combinationSum4Temp(int[] nums, int target) {
        int n = nums.length;

        // dp[i][j][k][l]：前 i 个元素，总共选择 j 个元素总和为 k 的总个数，i 可空间压缩掉
//        int[][][] dp = new int[n + 1][target + 1][target + 1];
        int[][] dp = new int[target + 1][target + 1];
        // 初始化，不选择总和为 0
        dp[0][0] = 1;

        for (int i = 1; i < n + 1; i++) {
            int[][] dpTemp = new int[target + 1][target + 1];
            for (int j = 0; j < target + 1; j++) {
                for (int k = 0; k < target + 1; k++) {
                    dpTemp[j][k] = dp[j][k];
                }
            }

            // 顺序不同的序列被视作不同的组合，dp[i][j][k] += dp[i-1][j-l][k-nums[i]*l] * C(j, l)
            for (int k = 0; k < target + 1; k++) {
//                for (int j = 0; j < target + 1; j++) {
//                    dp[i][j][k] = dp[i - 1][j][k];
//                }

                for (int j = 1; j < k + 1; j++) {
                    for (int l = 1; l < j + 1 && l * nums[i - 1] < k + 1; l++) {
//                        System.out.println(j + " : " + k + " : " + l + " : " + COMBINATION[j][l]);
//                        System.out.println(nums[i - 1] + " : " + dp[i - 1][j - l][k - nums[i - 1] * l]);
//                        dp[j][k] += dp[j - l][k - nums[i - 1] * l] * COMBINATION[j][l];
                        dpTemp[j][k] += dp[j - l][k - nums[i - 1] * l] * COMBINATION[j][l];
                    }
                }
            }

            for (int j = 0; j < target + 1; j++) {
                for (int k = 0; k < target + 1; k++) {
                    dp[j][k] = dpTemp[j][k];
                }
            }
        }
//        Arrays.stream(dp[n]).forEach(arr -> System.out.println(Arrays.toString(arr)));


        int res = 0;
        for (int i = 0; i < target + 1; i++) {
            res += dp[i][target];
        }
        return res;
    }

    private static final long[][] COMBINATION;

    static {
        COMBINATION = new long[1001][1001];

        for (int i = 1; i < COMBINATION.length; i++) {
            COMBINATION[i][0] = 1;
            COMBINATION[i][i] = 1;

            for (int j = 1; j < i; j++) {
                COMBINATION[i][j] = COMBINATION[i - 1][j] + COMBINATION[i - 1][j - 1];
            }
        }
    }


    /**
     * 不使用完全背包，直接考虑动态规划
     * dp[i] 代表和为 i 总共有多少种情况，dp[i] += dp[i-nums[j]]
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int i = 1; i < target + 1; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }

        return dp[target];
    }
}
