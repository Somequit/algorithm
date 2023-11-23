package leetcode.brushQuestions.medium;

import utils.AlgorithmUtils;

import java.util.Arrays;

/**
 * @author gusixue
 * @description
 * 2523. 范围内最接近的两个质数
 * 给你两个正整数 left 和 right ，请你找到两个整数 num1 和 num2 ，它们满足：
 * left <= nums1 < nums2 <= right  。
 * nums1 和 nums2 都是 质数 。
 * nums2 - nums1 是满足上述条件的质数对中的 最小值 。
 * 请你返回正整数数组 ans = [nums1, nums2] 。如果有多个整数对满足上述条件，请你返回 nums1 最小的质数对。如果不存在符合题意的质数对，请你返回 [-1, -1] 。
 * 如果一个整数大于 1 ，且只能被 1 和它自己整除，那么它是一个质数。
 * @date 2023/11/19
 */
public class ClosestPrimes {

    public static void main(String[] args) {
        ClosestPrimes closestPrimes = new ClosestPrimes();
        while (true) {
            int left = AlgorithmUtils.systemInNumberInt();
            int right = AlgorithmUtils.systemInNumberInt();

            int[] res = closestPrimes.solution(left, right);
            System.out.println(Arrays.toString(res));
        }
    }

    /**
     * 线性筛预处理所有素数，二分获得大于等于 left 的最小素数，遍历到小于等于 right 的素数，找每个相邻素数的差的最小值
     */
    private int[] solution(int left, int right) {
        int[] res = new int[]{-1, -1};

        // 二分获得非递减数组大于等于 left 的最小素数
        int minPrimes = binarySearchLow(PRIMES, left);
        if (minPrimes == -1) {
            return res;
        }

        for (int i = minPrimes; i + 1 < PRIMES_COUNT && PRIMES[i + 1] <= right; i++) {
            if (res[0] == -1 || res[1] - res[0] > PRIMES[i + 1] - PRIMES[i]) {
                res[0] = PRIMES[i];
                res[1] = PRIMES[i + 1];
            }
        }

        return res;
    }

    /**
     * 二分搜索：返回非递减数组中大于等于 key 的最小下标
     * 如果均小于 key 返回 -1
     */
    public int binarySearchLow(int[] nums, int key) {
        int left = 0;
        int right = nums.length - 1;
        int res = -1;

        while (left <= right) {
            // 避免 left+right 越界
            int mid = ((right - left) >> 1) + left;

            if (nums[mid] > key) {
                res = mid;
                right = mid - 1;

            } else if (nums[mid] < key) {
                left = mid + 1;

            } else {
                res = mid;
                right = mid - 1;

            }
        }

        return res;
    }

    private static final int TOTAL = (int) 1e6;
    private static final int[] PRIMES = new int[78500];
    private static int PRIMES_COUNT;

    /**
     * 每个数 x，乘以 <= lpf[x] 的质数，lpf[x] 指的是 x 的最小质因子
     * 时间复杂度：O（TOTAL），空间复杂度：O（TOTAL）
     */
    static {
        PRIMES_COUNT = 0;

        boolean[] is_primes = new boolean[TOTAL + 1];
        for (int i = 2; i <= TOTAL; i++) {
            if (!is_primes[i]) {
                PRIMES[PRIMES_COUNT] = i;
                PRIMES_COUNT++;
            }

            // 每个数 x，乘以 <= lpf[x] 的质数
            for (int j = 0; j < PRIMES_COUNT; ++j) {
                int p = PRIMES[j];
                if (i * p > TOTAL) {
                    break;
                }

                is_primes[i * p] = true;

                // p 是 lpf[i]
                if (i % p == 0) {
                    break;
                }
            }
        }
    }
}
