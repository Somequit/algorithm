package leetcode;

import utils.AlgorithmUtils;

/**
 * 4. 寻找两个正序数组的中位数
 * TODO:开发
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 */
public class MedianOfTwoSortedArray {

    public static void main(String[] args) {
        int[] nums1 = AlgorithmUtils.systemInArray();
        int[] nums2 = AlgorithmUtils.systemInArray();
        System.out.println(solution(nums1, nums2));
    }

    /**
     * nums1数组的中位数取出记为 medianIndexTemp，将其对nums2二分搜索查到位置
     * 比较比 medianIndexTemp 小的个数与中位数的位置，可以得到 medianIndexTemp 在中位数的左侧还是右侧
     * 然后缩小两个数组的范围重新执行第一步，直到某个数组只有一个数，再操作一次即可确认结果
     * 最坏时间约 = log(m) * log(n)
     * @param nums1
     * @param nums2
     */
    private static double solution(int[] nums1, int[] nums2) {
        int l1 = 0;
        int r1 = nums1.length;
        int l2 = 0;
        int r2 = nums2.length;

        if (r2 == 0) {
            return MedianOfSortedArray(nums1);
        } else if(r1 == 0) {
            return MedianOfSortedArray(nums2);
        }

        int medianIndex = r1 + r2 - 1 >> 1;
        while (r1 - l1 > 0) {
            int medianIndexTemp = l1 + r1 - 1 >> 1;
            int medianIndexTemp2 = binarySearchLeftIndex(nums1[medianIndexTemp], nums2, l2, r2);

            if (medianIndexTemp + medianIndexTemp2 == medianIndex) {
                l1 = medianIndexTemp;
                r1 = medianIndexTemp;

            } else if (medianIndexTemp + medianIndexTemp2 < medianIndex) {
                l1 = medianIndexTemp + 1;
                l2 = medianIndexTemp2;

            } else {
                r1 = medianIndexTemp;
                r2 = medianIndexTemp2;
            }
        }

        if (r2 - l2 > 0) {
            if (((nums1.length + nums2.length) & 1) == 1) {
                return nums1[l1];
            } else {
                return secondMedian(nums1, l1, nums2);
            }

        } else {
            if (((nums1.length + nums2.length) & 1) == 1) {
                return nums2[l2];
            } else {
                return secondMedian(nums2, l2, nums1);
            }

        }
    }

    private static int MedianOfSortedArray(int[] nums) {
        if ((nums.length & 1) == 1) {
            return nums[nums.length - 1 >> 1];

        } else {
            int leftMedian = nums[nums.length - 1 >> 1];
            int rightMedian = nums[nums.length >> 1];
            // 特别注意：避免越界（俩数不同部分的一半 加上 俩数相同的部分）,但是负奇数与直接除以2不同
            return (leftMedian + rightMedian) / 2;
        }
    }

    /**
     * 大于target的最小值、小于target的最大值、是否包括重复
     */
    private static int binarySearchLeftIndex(int target, int[] nums, int l, int r) {
        while (l < r) {
            int medianIndex = l + r - 1 >> 1;
            if (nums[medianIndex] >= target) {
                r = medianIndex;
            } else {
                l = medianIndex + 1;
            }
        }
        return l;
    }

    private static int secondMedian(int[] nums, int medianIndex, int[] numsTemp) {
        int leftMedian = nums[medianIndex];

        int rightMedian = Integer.MAX_VALUE;
        if (medianIndex + 1 < nums.length) {
            rightMedian = nums[medianIndex + 1];
        }
        int rightMedianIndex = binarySearchLeftIndex(leftMedian, numsTemp, 0, numsTemp.length);
        if (rightMedianIndex < numsTemp.length) {
            rightMedian = Math.min(rightMedian, numsTemp[rightMedianIndex]);
        }

        return (leftMedian + rightMedian) / 2;
    }
}