package leetcode.brushQuestions.medium;

import java.util.stream.IntStream;

/**
 * @author gusixue
 * @description 2012. 数组美丽值求和
 * @date 2025/3/11
 */
public class SumOfBeauties {

    /**
     * 先逆序求出后缀最小值放入数组，接着顺序求美丽值的同时求出前缀最大值、仅保存当前前缀最大值
     * @param nums
     * @return
     */
    public int sumOfBeauties(int[] nums) {
        int n = nums.length;
        int[] suffixMin = new int[n];

        suffixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
        }

        int res = 0;
        int prefixMax = nums[0];
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] < nums[i + 1]) {
                res++;
                if (nums[i] > prefixMax && nums[i] < suffixMin[i + 1]) {
                    res++;
                }
            }

            prefixMax = Math.max(prefixMax, nums[i]);
        }

        return res;
    }
}
