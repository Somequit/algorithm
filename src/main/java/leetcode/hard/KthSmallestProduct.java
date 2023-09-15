package leetcode.hard;

import utils.AlgorithmUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author gusixue
 * @description
 * 2040. 两个有序数组的第 K 小乘积
 * 给你两个 从小到大排好序 且下标从 0 开始的整数数组 nums1 和 nums2 以及一个整数 k ，请你返回第 k （从 1 开始编号）小的 nums1[i] * nums2[j] 的乘积，其中 0 <= i <
 * nums1.length 且 0 <= j < nums2.length 。
 *
 * 1 <= nums1.length, nums2.length <= 5 * 10 ^ 4
 * -10 ^ 5 <= nums1[i], nums2[j] <= 10 ^ 5
 * 1 <= k <= nums1.length * nums2.length
 * nums1 和 nums2 都是从小到大排好序的。
 * @date 2023/9/15
 */
public class KthSmallestProduct {

    public static void main(String[] args) {
        KthSmallestProduct kthSmallestProduct = new KthSmallestProduct();
        while (true) {
            int[] nums1 = AlgorithmUtils.systemInArray();
            int[] nums2 = AlgorithmUtils.systemInArray();
            long k = AlgorithmUtils.systemInNumberInt();
            
            long res = kthSmallestProduct.solution(nums1, nums2, k);
            System.out.println(res);
        }
    }

    public long solution(int[] nums1, int[] nums2, long k) {
        // 将递增数组 nums1 与 nums2，0-负数 取绝对值后 按照从小到大提取返回，1-正数按照从小到大提取返回
        List<Integer>[] negPosNums1 = listNegativePositive(nums1);
        List<Integer>[] negPosNums2 = listNegativePositive(nums2);

        long res = 0L;
        // 积为负数总个数
        long multiNegative = (long)negPosNums1[0].size() * negPosNums2[1].size() + (long)negPosNums2[0].size() * negPosNums1[1].size();
        // 第 k 小为负数，则二分 [1, Max(negPosNums1[0][len-1]*negPosNums2[1][len-1], negPosNums2[0][len-1]*negPosNums1[1][len-1])]，
        // 找到 负数1*正数2（取绝对值后）+负数2*正数1（取绝对值后） 区间第 总负数个数-k+1 小
        if (k <= multiNegative) {
            k = multiNegative - k + 1;
//            System.out.println(k + " : multiNegative=" + multiNegative);
            res = 0L - doKthSmallestProduct(k, negPosNums1[0], negPosNums2[1], negPosNums2[0], negPosNums1[1]);

        } else {
            // 积为0总个数
            int zero1 = nums1.length - negPosNums1[0].size() - negPosNums1[1].size();
            int zero2 = nums2.length - negPosNums2[0].size() - negPosNums2[1].size();
            long multiZero = (long)zero1 * nums2.length + (long)zero2 * nums1.length - (long)zero1 * zero2;
            // 如果第 k 小为 0，则返回0
            if (k <= multiNegative + multiZero) {
//                System.out.println(multiNegative + " : multiZero=" + multiZero);
                res = 0L;

            } else {
                // 积为正数总个数
                long multiPositive = (long)negPosNums1[0].size() * negPosNums2[0].size() + (long)negPosNums1[1].size() * negPosNums2[1].size();
                // 第 k 小为正数，则二分 [1, Max(negPosNums1[0][len-1]*negPosNums2[0][len-1], negPosNums1[1][len-1]*negPosNums2[1][len-1])]，
                // 找到 负数1*负数2 + 正数1*正数2 区间第 k-总负数与0个数 小
                k -= (multiNegative + multiZero);
//                System.out.println(k + " : " + multiPositive + " : " + multiNegative + " : " + multiZero);
                res = doKthSmallestProduct(k, negPosNums1[0], negPosNums2[0], negPosNums1[1], negPosNums2[1]);

            }
        }

        return res;
    }

    /**
     * 将递增数组 nums 0-负数 取绝对值后 按照从小到大提取返回，1-正数按照从小到大提取返回
     * @param nums
     * @return
     */
    private List<Integer>[] listNegativePositive(int[] nums) {
        List<Integer>[] negPosNums = new ArrayList[2];
        negPosNums[0] = new ArrayList<>();
        negPosNums[1] = new ArrayList<>();

        for (int num : nums) {
            if (num < 0) {
                negPosNums[0].add(-num);

            } else if (num > 0) {
                negPosNums[1].add(num);
            }
        }
        Collections.sort(negPosNums[0]);

//        System.out.println(Arrays.toString(negPosNums));
        return negPosNums;
    }

    /**
     * 二分 [1, Max(firstNums1[len-1]*firstNums2[len-1], secondNums1[len-1]*secondNums2[len-1])]，找到区间第 k 小
     * @param k 第 k 小
     * @param firstNums1 正整数递增数组 firstNums1 与 firstNums2 乘积
     * @param firstNums2 正整数递增数组 firstNums1 与 firstNums2 乘积
     * @param secondNums1 正整数递增数组 secondNums1 与 secondNums1 乘积
     * @param secondNums2 正整数递增数组 secondNums1 与 secondNums1 乘积
     * @return
     */
    private long doKthSmallestProduct(long k, List<Integer> firstNums1, List<Integer> firstNums2,
                                      List<Integer> secondNums1, List<Integer> secondNums2) {
        long left = 1L;
        int maxF1 = firstNums1.size() == 0 ? 1 : firstNums1.get(firstNums1.size() - 1);
        int maxF2 = firstNums2.size() == 0 ? 1 : firstNums2.get(firstNums2.size() - 1);
        int maxS1 = secondNums1.size() == 0 ? 1 : secondNums1.get(secondNums1.size() - 1);
        int maxS2 = secondNums2.size() == 0 ? 1 : secondNums2.get(secondNums2.size() - 1);
        long right = Math.max((long)maxF1 * maxF2, (long)maxS1 * maxS2);
//        System.out.println(maxF1 + " : " + maxF2 + " : " + maxS1 + " : " + maxS2 + " : " + right);
        long res = 1L;

        while (left <= right) {
            long mid = ((right - left) >> 1) + left;

            // 0-等于 mid 的个数，1-小于 mid 的个数
            int[] count = getLessEqualCount(mid, firstNums1, firstNums2);
            int[] countTemp = getLessEqualCount(mid, secondNums1, secondNums2);
            count[0] += countTemp[0];
            count[1] += countTemp[1];

            // mid 是第 [count[0]+1, count[0]+count[1]] 小，包含 k 则结果就是 mid
            if (k > count[1] && k <= count[1] + count[0]) {
                res = mid;
                break;

            // mid 在 count[1] 到 count[1]+1 小中间（不含），k 小于等于 count[1] 代表结果小于 mid
            } else if (k <= count[1]) {
                right = mid - 1;

            // mid 在 count[1] 到 count[1]+1 小中间（不含），k 大于 count[1] 代表结果大于 mid
            } else {
                left = mid + 1;
            }
        }

        return res;
    }

    /**
     * 双指针找到小于与等于 num 的个数
     * @param target 乘积小于等于的目标值
     * @param list1 正整数递增数组
     * @param list2 正整数递增数组
     * @return 0-等于 mid 的个数，1-小于 mid 的个数
     */
    private int[] getLessEqualCount(long target, List<Integer> list1, List<Integer> list2) {
        int[] count = new int[2];
        int[] temp = new int[2];

        // 乘积小于等于 target，list1 降序、list2 就一定非降序，因此使用双指针
        for (int i = list1.size() - 1, j = 0; i >= 0; i--) {

            // list1 变小则之前相乘等于 target 的就一定小于 target，更新 temp
            if (i < list1.size() - 1 && list1.get(i) != list1.get(i + 1)) {
                temp[1] += temp[0];
                temp[0] = 0;
            }

            // list2 不回溯
            while (j < list2.size() && (long)list1.get(i) * list2.get(j) <= target) {
                if ((long)list1.get(i) * list2.get(j) < target) {
                    temp[1]++;

                } else {
                    temp[0]++;
                }
                j++;
            }

            count[0] += temp[0];
            count[1] += temp[1];
        }
//        System.out.println(target + " : " + count[0] + " : " + count[1]);

        return count;
    }
}
