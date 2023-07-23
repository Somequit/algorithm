package leetcode.contest.contest_355;

import utils.AlgorithmUtils;

import java.util.Arrays;


/**
 *
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();

            long res = contest.solution(nums);
            System.out.println(res);
        }

    }

    public long solution(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        long res = nums[nums.length - 1];

        long[] numsLong = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsLong[i] = nums[i];
        }

        for (int i = numsLong.length - 2; i >= 0; i--) {
            if (numsLong[i] <= numsLong[i + 1]) {
                numsLong[i] += numsLong[i + 1];
            }
            res = Math.max(numsLong[i], res);
        }
        return res;

    }


}
