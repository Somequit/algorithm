package leetcode.medium;

import utils.AlgorithmUtils;

/**
 * @author gusixue
 * @description
 * 55. 跳跃游戏
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 * 1 <= nums.length <= 3 * 10^4
 * 0 <= nums[i] <= 10^5
 * @date 2023/5/11
 */
public class CanJump {

    public static void main(String[] args) {
        while(true) {
            int[] nums = AlgorithmUtils.systemInArray();
            boolean res = solution(nums);
            System.out.println(res);
        }
    }

    /**
     * 贪心：首先不能往后跳的原因是跳到了 0 这个点，因此只要跳过每一个 0 就一定可以往后跳到最后一个下标
     * 其次由于非 0 的 i 点可以跳到 [i+1, i+nums[i]] 闭区间任意位置，因此我们记下过程中最远可以跳到的点
     * 最后循环数组
     *     非 0 就更新最远的点
     *     0 就看是否最远只能跳到此位置，如果是则代表无法往后跳了
     * 直到最远的点不小于最后一个点（不需要所有元素求和或者乘法所以不会超过 int）
     * PS：注意仅有一个数，即使是 0 也符合要求
     * 时间复杂度：O（n），空间复杂度：O（n）
     */
    private static boolean solution(int[] nums) {
        // 判空
        if (nums == null || nums.length <= 0) {
            return false;
        }

        // 仅有一个数，即使是 0 也符合要求
        if (nums.length == 1) {
            return true;
        }

        boolean res = true;
        // 循环数组计算是否可以调到最后
        int farthestIndex = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                if (farthestIndex <= i) {
                    res = false;
                    break;
                }

            } else {
                farthestIndex = Math.max(farthestIndex, i + nums[i]);
                if (farthestIndex >= len - 1) {
                    break;
                }

            }
        }

        return res;
    }
}
