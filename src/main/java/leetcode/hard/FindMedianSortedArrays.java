package leetcode.hard;

import utils.AlgorithmUtils;

/**
 * @author gusixue
 * @description
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 *
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -10 ^ 6 <= nums1[i], nums2[i] <= 10 ^ 6
 * @date 2023/11/6
 */
public class FindMedianSortedArrays {

    public static void main(String[] args) {
        FindMedianSortedArrays findMedianSortedArrays = new FindMedianSortedArrays();
        while (true) {
            int[] nums1 = AlgorithmUtils.systemInArray();
            int[] nums2 = AlgorithmUtils.systemInArray();

            double res = findMedianSortedArrays.solution(nums1, nums2);
            System.out.println(res);

            double res2 = findMedianSortedArrays.solutionOptimization(nums1, nums2);
            System.out.println(res2);
        }
    }

    /**
     * 首先思考暴力：可以设置两个指针 a、b 从俩数组下标 0 出发，如果 nums1[a] <= nums2[b] 则 a++、否则 b++，直到移动 (m+n-1)/2 次后，最小值就是就是（第一个）中位数，
     * 如果 (m+n) 总个数为奇数就是答案，为偶数就再移动小的元素指针 1 次、然后最小值就是第二个中位数，实现可以取出两个中位数，返回答案时处理，这样更统一
     * 过程中如果某个指针移动到数组最后一个元素之后，则强制设置该元素为最大元素（即不会再移动了）
     * 时间复杂度：O(m+n) ，空间复杂度：O(1)
     */
    private double solution(int[] nums1, int[] nums2) {
        // 特判只有一个元素的结果
        if (nums1.length == 0 && nums2.length == 1) {
            return nums2[0];

        } else if (nums1.length == 1 && nums2.length == 0) {
            return nums1[0];
        }

        // 两个指针 a、b 从俩数组下标 0 出发
        int a = 0;
        int b = 0;

        // 实现可以取出两个中位数
        int res1 = Integer.MIN_VALUE;
        int res2 = Integer.MIN_VALUE;
        int moveCount = (nums1.length + nums2.length - 1) >> 1;
        for (int i = 0; i < moveCount + 2; i++) {
            // 强制设置 a 为最大元素 / 没有强制 b 为最大元素 且 a 比较大
            if (a == nums1.length || (b != nums2.length && nums1[a] > nums2[b])) {
                // 移动 (m+n-1)/2 次以后（即移动 (m+n-1)/2+1 次之前）
                if (i == moveCount) {
                    res1 = nums2[b];

                // 移动 (m+n-1)/2+1 次以后
                } else if (i == moveCount + 1) {
                    res2 = nums2[b];
                }
                b++;

            // 强制设置 b 该元素为最大元素 / 没有强制 a 为最大元素 且 a 不如 b 大
            } else {
                if (i == moveCount) {
                    res1 = nums1[a];

                } else if (i == moveCount + 1) {
                    res2 = nums1[a];
                }
                a++;

            }
        }

        // (m+n) 总个数为奇数就是答案
        if (((nums1.length + nums2.length) & 1) == 1) {
            return res1;

        } else {
            return (res1 + res2) / 2.0;
        }
    }

    /**
     * 思考优化暴力：
     * 时间复杂度：O(log (m+n)) ，空间复杂度：O(1)
     */
    private double solutionOptimization(int[] nums1, int[] nums2) {
        return 0;
    }
}
