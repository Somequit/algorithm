package leetcode.brushQuestions.easy;


/**
 * @author gusixue
 * @description 2529. 正整数和负整数的最大计数
 * @date 2024/4/9
 */
public class MaximumCount {

    /**
     * 由于非递减，因此可用二分找到小于 0 的最大下标就找到 neg，大于 0 的最小下标就找到 pos
     */
    public int maximumCount(int[] nums) {
        int n = nums.length;

        // 特判全负数/全正数的情况
        if (nums[0] > 0 || nums[n - 1] < 0) {
            return n;
        }

        int negMaxIndex = binarySearchLess(nums, 0);
        int posMinIndex = binarySearchGreater(nums, 0);
//        System.out.println(negMaxIndex + " : " + posMinIndex);

        return Math.max(negMaxIndex + 1, n - posMinIndex);

    }

    /**
     * 二分找到大于 target 的最小下标
     */
    private int binarySearchGreater(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int res = -1;
        while (left <= right) {
            int mid = ((right - left) >> 1) + left;

            if (nums[mid] > target) {
                right = mid - 1;
                res = mid;

            } else if (nums[mid] < target) {
                left = mid + 1;

            } else {
                left = mid + 1;
                res = mid + 1;
            }
        }

        return res;
    }

    /**
     * 二分找到小于 target 的最大下标
     */
    private int binarySearchLess(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int res = -1;
        while (left <= right) {
            int mid = ((right - left) >> 1) + left;

            if (nums[mid] > target) {
                right = mid - 1;

            } else if (nums[mid] < target) {
                left = mid + 1;
                res = mid;

            } else {
                right = mid - 1;
                res = mid - 1;
            }
        }

        return res;
    }
}
