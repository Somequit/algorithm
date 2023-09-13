package leetcode.medium;

import utils.AlgorithmUtils;

/**
 * @author gusixue
 * @description
 * 33. 搜索旋转排序数组
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0],
 * nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 *
 * 1 <= nums.length <= 5000
 * -10 ^ 4 <= nums[i] <= 10 ^ 4
 * nums 中的每个值都 独一无二
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -10 ^ 4 <= target <= 10 ^ 4
 *
 * @date 2023/9/13
 */
public class Search {

    public static void main(String[] args) {
        Search search = new Search();

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
            int target = AlgorithmUtils.systemInNumberInt();

            int res = search.solution(nums, target);
            System.out.println(res);

            int res2 = search.solution2(nums, target);
            System.out.println(res2);
        }
    }

    private int solution2(int[] nums, int target) {
        // 判空
        if (nums == null || nums.length <= 0) {
            return -1;
        }

        // 特判长度为 1
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }

        // 一次二分解决
        int left = 0;
        int right = nums.length - 1;
        int res = -1;
        while (left <= right) {
            int mid = ((right - left + 1) >> 1) + left;

            if (target == nums[mid]) {
                res = mid;
                break;
            }

            // [left, mid] 递增
            if (nums[mid] >= nums[left]) {
                if (target >= nums[left] && target <= nums[mid]) {
                    right = mid - 1;

                } else {
                    left = mid + 1;
                }

                // [left, mid-k-1] 处递增，[mid-k,mid]（更小） 但再次递增
            } else {
                if (target <= nums[right] && target > nums[mid]) {
                    left = mid + 1;

                } else {
                    right = mid - 1;
                }

            }
        }

        return res;
    }

    public int solution(int[] nums, int target) {
        // 判空
        if (nums == null || nums.length <= 0) {
            return -1;
        }

        int res = -1;
        int len = nums.length;
        // 旋转后整个数组还是严格递增数组，直接二分
        if (nums[0] <= nums[len - 1]) {
            res = binarySearch(nums, 0, len, target);

        } else {
            // 找到旋转前 nums[0] 所在的下标 k
            int k = getMinIndex(nums);
//            System.out.println(k);

            // [0,k-1] 与 [k, len-1] 都是严格递增数组，二分找到 target
            res = binarySearch(nums, 0, k, target);
            if (res == -1) {
                res = binarySearch(nums, k, len, target);
            }

        }

        return res;
    }

    /**
     * 找到旋转前 nums[0] 所在的下标 k
     */
    private int getMinIndex(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        int res = 0;
        while (left < right) {
            int mid = ((right - left + 1) >> 1) + left;

            if (nums[mid] >= nums[left]) {
                left = mid;

            } else {
                res = mid;
                right = mid - 1;
            }
        }
        return res;
    }

    /**
     * @param nums [left, right-1] 中属于严格递增数组
     * @param left 左端点下标（含）
     * @param right 右端点下标（不含）
     * @param target 查询是否在数组中
     * @return target 在数组中则返回数组下标，否则返回 -1
     */
    private int binarySearch(int[] nums, int left, int right, int target) {
        int res = -1;
        while (left < right) {
            int mid = ((right - left) >> 1) + left;

            if (nums[mid] > target) {
                right = mid;

            } else if (nums[mid] < target) {
                left = mid + 1;

            } else {
                res = mid;
                break;
            }
        }
        return res;
    }
}
