package leetcode.hot_100.medium;

import utils.AlgorithmUtils;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * 581. 最短无序连续子数组
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 */
public class FindUnsortedSubarray {

    public static void main(String[] args) {
        while(true) {
            int[] nums = AlgorithmUtils.systemInArray();
//            int result = solution(nums);
            int result = solution2(nums);
            System.out.println(result);
        }
    }

    /**
     * 结果本质上是查询左右边界，左边界以左为升序，并且最大的数都不大于右边任意数，右边界以右也是升序，且最小的数都不小于左边任意数，左右边界中间就是结果
     * 先找左边界，从第一个数开始找到第 i 个数，当 第 i+1 个数小于第 i 个数时，[0, i]就升序（当然可能有相同的数字），这时从 i 开始到结尾查询最小值，
     * 然后找到最小值处于[0, i]中的某一位（假设为比最小值小的最大值为 j），那么左边界以左就是[0, j]，当然可能会有整个数组升序或者最小值比第 0 位还要小的结果
     * 右边界查询方法与左边界一致
     * 时间复杂度：O(n) 额外空间复杂度：O(1)
     */
    private static int solution(int[] nums) {
        int sortLeft = 1;
        for (; sortLeft < nums.length; sortLeft++) {
            if (nums[sortLeft] < nums[sortLeft - 1]) {
                break;
            }
        }
        System.out.println("sortLeft:" + sortLeft);
        // 当前位置(含)往右需要排序
        int left = 0;
        if (sortLeft >= nums.length) {
            return 0;
        } else if (sortLeft > 1) {
            int minNum = Integer.MAX_VALUE;
            for (int i = sortLeft; i < nums.length; i++) {
                minNum = min(minNum, nums[i]);
            }
            System.out.println("minNum:" + minNum);
            // 可使用二分进行优化
            for (int i = 0; i < sortLeft; i++) {
                if (nums[i] > minNum) {
                    left = i;
                    break;
                }
            }
        }
        System.out.println("left:" + left);

        int sortRight = nums.length - 2;
        for (; sortRight >= left; sortRight--) {
            if (nums[sortRight] > nums[sortRight + 1]) {
                break;
            }
        }
        System.out.println("sortRight:" + sortRight);
        int right = nums.length - 1;
        if (sortRight == nums.length - 2) {
            return nums.length - left;
        } else {
            int maxNum = Integer.MIN_VALUE;
            for (int i = left; i <= sortRight; i++) {
                maxNum = max(maxNum, nums[i]);
            }
            System.out.println("maxNum:" + maxNum);
            // 当前位置(含)向左需要排序
            // 可使用二分进行优化
            for (int i = nums.length - 1; i > sortRight; i--) {
                if (nums[i] < maxNum) {
                    right = i;
                    break;
                }
            }
        }
        System.out.println("right:" + right);

        return right - left + 1;
    }

    /**
     * LeetCode官方题解、代码极其方便编写
     * 本质上还是查询左右边缘[left， right]，由于 left 左边所有数都不大于右边任意数、且 left 需要尽量靠右
     * 因此我们反过来想、从右往左遍历数组查询 left，如果递减则 left 位置不变，如果数字变大、则 left 至少也要移动到变大那个数字的位置
     * 同理right也可以这么计算，并且两个循环可以合并在一起编写，最后注意如果数组有序结果是0
     * 时间复杂度：O（n） 额外空间复杂度：O（1）
     */
    private static int solution2(int[] nums) {
        int len = nums.length;

        int minn = Integer.MAX_VALUE;
        int maxn = Integer.MIN_VALUE;
        int left = -1;
        int right = -1;

        for(int i = 0; i < len; i++) {
            if(minn >= nums[len - i - 1]) {
                minn = nums[len - i - 1];
            } else {
                left = len - i - 1;
            }

            if(maxn <= nums[i]) {
                maxn = nums[i];
            } else {
                right = i;
            }
        }
        return right == -1 ? 0 : right - left + 1;
    }
}
