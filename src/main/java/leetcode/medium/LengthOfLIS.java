package leetcode.medium;

import utils.AlgorithmUtils;

import java.util.Arrays;

/**
 * @author gusixue
 * @description
 * 300. 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * 1 <= nums.length <= 2500
 * -10^4 <= nums[i] <= 10^4
 * @date 2023/5/7
 */
public class LengthOfLIS {

    public static void main(String[] args) {
        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
            int res = solution(nums);
            System.out.println(res);
        }
    }

    /**
     * 贪心 + 二分：构造一个严格递增数组，依次将每个元素放入对应位置，数组个数就是结果
     * 第一个元素放在严格递增数组第一个位置，第二个元素开始使用二分查询严格递增数组：
     *     如果严格递增数组所有元素都比该元素大，则该元素替换第一个位置，因为该元素放在第一位最优
     *     如果严格递增数组所有元素都比该元素小，则该元素追加在严格递增数组后，因为前面的元素在原数组中在该元素前面，因此该元素可以增长结果
     *     如果严格递增数组某个元素等于该元素，则该元素替换相等元素（方便编码，本质是应该抛弃的）
     *     否则，该元素替换严格递增数组中大于该元素的最小值，因为该元素放在此位、与前面的元素可形成最长严格递增子序列最优
     * PS：构造的严格递增数组不一定是最长严格递增子序列，因为替换元素时、数组中该元素后面的元素不正确，但不影响结果
     * 时间复杂度：O（nlogn），空间复杂度：O（n）
     */
    private static int solution(int[] nums) {
        int len = nums.length;
        // 判空
        if (nums == null || len <= 0) {
            return 0;
        }

        // 循环原数组构造严格递增数组
        int[] increase = new int[len];

        // 严格递增数组现有元素个数
        int increaseNum = 0;

        increase[0] = nums[0];
        increaseNum++;
        for (int i = 1; i < len; i++) {
            if (nums[i] < increase[0]){
                increase[0] = nums[i];

            } else if (nums[i] > increase[increaseNum - 1]){
                increase[increaseNum] = nums[i];
                increaseNum++;

            } else {
                // 二分严格递增数组，返回大于等于该元素的最小值的下标
                int increaseMinIndex = binaryMin(increase, 0, increaseNum, nums[i]);
                increase[increaseMinIndex] = nums[i];
            }
            System.out.println(increaseNum + ":" + Arrays.toString(increase));
        }

        return increaseNum;
    }

    /**
     * 二分严格递增数组，返回大于等于该元素的最小值的下标
     * @param increase 严格递增数组
     * @param left 数组下标开始（含）
     * @param right 数组下标结尾（不含）
     * @param target 比较的元素
     * @return 返回大于等于该元素的最小值的下标
     */
    private static int binaryMin(int[] increase, int left, int right, int target) {
        int res = left;
        right--;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (increase[mid] > target) {
                right = mid - 1;
                res = mid;

            } else if (increase[mid] < target) {
                left = mid + 1;

            } else {
                res = mid;
                break;
            }
        }

        return res;
    }
}
