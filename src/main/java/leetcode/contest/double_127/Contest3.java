package leetcode.contest.double_127;

import utils.AlgorithmUtils;

import java.util.*;
/**
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
            int k = AlgorithmUtils.systemInNumberInt();

            int res = contest.minimumSubarrayLength(nums, k);
            System.out.println(res);
        }

    }

    /**
     * @return
     */
    public int minimumSubarrayLength(int[] nums, int k) {
        int res = Integer.MAX_VALUE;

        int[] bitCount = new int[31];
        // [left, right)
        for (int left = 0, right = 1; right < nums.length + 1; right++) {
            doBitCount(nums[right - 1], bitCount, 1);
//            System.out.println(Arrays.toString(bitCount));

            while (right > left && calBitCount(bitCount) >= k) {
                res = Math.min(right - left, res);

                doBitCount(nums[left], bitCount, -1);
                left++;
            }
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int calBitCount(int[] bitCount) {
        int res = 0;
        for (int i = 0; i < bitCount.length; i++) {
            if (bitCount[i] > 0) {
                res |= (1 << i);
            }
        }
//        System.out.println(Arrays.toString(bitCount) + " : " + res);

        return res;
    }

    private void doBitCount(int num, int[] bitCount, int val) {
        for (int i = 0; i < bitCount.length; i++) {
            if (((num >> i) & 1) > 0) {
                bitCount[i] += val;
            }
        }
    }


}
