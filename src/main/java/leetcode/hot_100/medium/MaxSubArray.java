package leetcode.hot_100.medium;

import utils.AlgorithmUtils;

/**
 * @author gusixue
 * @description
 * 53. 最大子数组和
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * @date 2023/6/7
 */
public class MaxSubArray {

    public static void main(String[] args) {
        MaxSubArray maxSubArray = new MaxSubArray();
        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
            int res = maxSubArray.solution(nums);
            System.out.println(res);
        }
    }

    /**
     * 动态规划：由于数组需要**连续**、因此可以定义每个元素作为后缀起点，比较 前一个元素所有后缀的最大值 与 0（不加任何元素） 的最大值，此时定义的状态「无后效性」
     * **定义状态（定义子问题）**：dp[n] 代表当前元素为后缀起点的所有后缀最大值
     * **定义转移方程**：dp[i]=max(0, dp[i-1])+nums[i]，结果就是 max(dp[0..n-1])
     * **空间压缩**：由于 dp[i] 仅与 dp[i-1] 相关，因此可以使用一个值滚动即可
     * 时间复杂度：O（n），空间复杂度：O（1）
     *
     * 理解「无后效性」
     * 「无后效性」在李煜东著《算法竞赛进阶指南》，摘录如下：
     * 为了保证计算子问题能够按照顺序、不重复地进行，动态规划要求已经求解的子问题不受后续阶段的影响。这个条件也被叫做「无后效性」。
     * 换言之，动态规划对状态空间的遍历构成一张有向无环图，遍历就是该有向无环图的一个拓扑序。有向无环图中的节点对应问题中的「状态」，
     * 图中的边则对应状态之间的「转移」，转移的选取就是动态规划中的「决策」。
     *
     * 理解：每个子问题仅求解一次，后续元素的增加 或 子问题的求解 **不会影响之前的求解完成的子问题**
     */
    private int solution(int[] nums) {
        // 判空
        if (nums == null || nums.length <= 0) {
            return 0;
        }

        // 定义 dp 与初始化
        int dp = nums[0];


        // 循环获取最大值 与 空间压缩
        int res = dp;
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            dp = Math.max(0, dp) + nums[i];
            res = Math.max(res, dp);
        }

        return res;
    }
}
