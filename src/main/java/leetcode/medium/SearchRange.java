package leetcode.medium;

import utils.AlgorithmUtils;

import java.util.Arrays;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 * @author gusixue
 * @date 2023/2/23
 */
public class SearchRange {

    public static void main(String[] args) {
        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
            int target = AlgorithmUtils.systemInNumberInt();
            SearchRange searchRange = new SearchRange();

            int[] res = searchRange.solution(nums, target);
            System.out.println(Arrays.toString(res));
        }
    }

    /**
     * 标准二分匹配算法求上下界
     */
    private int[] solution(int[] nums, int target) {
        int[] res = {-1, -1};
        // 判空
        if (nums == null || nums.length == 0 || target < nums[0] || target > nums[nums.length - 1]) {
            return res;
        }

        // 二分求非递减数组等于 target 的最小位置
        res[0] = binarySearchLeftOrRightBound(nums, target, "left");

        // 二分求非递减数组等于 target 的最大位置
        res[1] = binarySearchLeftOrRightBound(nums, target, "right");

        return res;
    }


    /**
     * 二分求非递减数组等于 target 的位置
     * @param nums 非递减数组，非空
     * @param target 目标值
     * @param bound "left"代表等于 target 的最小下标，"right"代表最大下标
     * @return 返回下标
     */
    private int binarySearchLeftOrRightBound(int[] nums, int target, String bound) {
        int low = 0;
        int hight = nums.length - 1;
        int res = -1;
        while (low <= hight) {
            int mid = ((low + hight) >> 1);
            if (nums[mid] > target) {
                hight = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                res = mid;
                if ("left".equals(bound)) {
                    hight = mid - 1;
                } else if ("right".equals(bound)) {
                    low = mid + 1;
                } else {
                    // 异常处理
                }
            }
        }
        return res;
    }
}
