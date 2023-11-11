package leetcode.hot_100.medium;

import utils.AlgorithmUtils;

/**
 * @author gusixue
 * @description
 * 152. 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 测试用例的答案是一个 32-位 整数。
 * 子数组 是数组的连续子序列。
 * 1 <= nums.length <= 2 * 10^4
 * -10 <= nums[i] <= 10
 * nums 的任何前缀或后缀的乘积都 保证 是一个 32-位 整数
 * @date 2023/6/5
 */
public class MaxProduct {

    public static void main(String[] args) {
        MaxProduct maxProduct = new MaxProduct();
        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
            int res = maxProduct.solution(nums);
            System.out.println(res);
        }
    }

    /**
     * 动态规划：定义 dp[n][2] 分别代表后缀的最小连续乘积与最大连续乘积，即 dp[i][0]：以 i 为后缀的最小连续乘积（负数乘最小负数可得最大正数），
     * dp[i][1]：以 i 为后缀的最大连续乘积，初始化：当 i = 0 时 dp[0][0]=dp[0][1]=num[0]，动规转移方程：
     * dp[i][0] = min(num[i], dp[i-1][0]*num[i], dp[i-1][1]*num[i])，dp[i][1] = max(num[i], dp[i-1][0]*num[i], dp[i-1][1]*num[i])
     * 最大值为 max(dp[0..n-1][1])
     * 空间压缩：dp 数组中 i 仅与 i-1 相关，所以可用滚动数组 dp[2][2]
     * 时间复杂度：O（n），空间复杂度：O（1）
     */
    private int solution(int[] nums) {
        // 判空
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        // dp 初始化 + 空间压缩
        int len = nums.length;
        int[][] dp = new int[2][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        int res = nums[0];

        // 循环数组获得最大值
        for (int i = 1; i < len; i++) {
            dp[i & 1][0] = Math.min(nums[i], Math.min(dp[(i + 1) & 1][0] * nums[i], dp[(i + 1) & 1][1] * nums[i]));
            dp[i & 1][1] = Math.max(nums[i], Math.max(dp[(i + 1) & 1][0] * nums[i], dp[(i + 1) & 1][1] * nums[i]));
            res = Math.max(res, dp[i & 1][1]);
        }

        return res;
    }
}
