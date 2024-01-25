package leetcode.hot_100.first.medium;

import utils.AlgorithmUtils;

/**
 * @author gusixue
 * @description
 * 416. 分割等和子集
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 * @date 2023/6/9
 */
public class CanPartition {

    public static void main(String[] args) {
        CanPartition canPartition = new CanPartition();
        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
            boolean res = canPartition.solution(nums);
            System.out.println(res);
        }
    }

    /**
     * 动态规划（01 背包）：首先思考特殊情况：由于所有元素均为正整数、因此 单个元素 或 所有元素和为奇数 则无法划分，
     * 然后是一般情况：题目可以转化为选择部分元素、使其和为所有元素和的一半，因此可以想到 01 背包
     * 定义状态（子问题）：dp[i][j] 为下标（含）前 i 个元素、和为 j 是否可以出现，初始化：j 为 0 是 true、否则为 false，最后判断 dp[n-1][m/2]
     * 转移方程式：如果加当前元素 dp[i-1][j-num[i]] 或不加 dp[i-1][j] 为 true、则 dp[i][j] 为 true（可用 dp[i-1][j-num[i]] | dp[i-1][j]）
     * 空间压缩：由于 i 仅与 i-1 相关，因此可以使用滚动数组 dp[2][m/2]，同时如果遍历 j 时倒序、遍历时可以只用使用之前的元素、同时赋值不会影响之前的元素，因此使用滚动数组 dp[m/2]
     * 元素个数为 n，所有元素之和为 m，时间复杂度：O（n*m），空间复杂度：O（m）
     */
    private boolean solution(int[] nums) {
        // 判空 + 特殊情况（小于俩元素则 false）
        if (nums == null || nums.length <= 1) {
            return false;
        }

        // 获取所有元素之和 + 特殊情况（和为奇数则 false）
        int len = nums.length;
        int total = getTotal(nums, len);
        if ((total & 1) == 1) {
            return false;
        }
        int half = (total >> 1);

        // 定义状态 + 初始化
        boolean[] dp = new boolean[half + 1];
        dp[0] = true;

        // 通过转移方程式定义数据 + 空间压缩
        for (int i = 0; i < len; i++) {
            for (int j = half; j >= nums[i]; j--) {
                dp[j] = (dp[j] | dp[j - nums[i]]);
            }
        }

        return dp[half];
    }

    /**
     * 获取所有元素之和
     */
    private int getTotal(int[] nums, int len) {
        int total = 0;
        for (int i = 0; i < len; i++) {
            total += nums[i];
        }
        return total;
    }
}
