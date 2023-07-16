package leetcode.contest.contest_354;

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
            int k = AlgorithmUtils.systemInNumberInt();

            int res = contest.solution(nums, k);
            System.out.println(res);
        }

    }

    public int solution(int[] nums, int k) {
        int res = 0;

        Arrays.sort(nums);
        int len = nums.length;
        int min = nums[0];
        int max = nums[len - 1];

        int[] minNums = new int[len];
        int[] maxNums = new int[len];
        for (int i = 0; i < len; i++) {
            minNums[i] = Math.max(min, nums[i] - k);
            maxNums[i] = Math.min(max, nums[i] + k);
            System.out.println(minNums[i] + " : " + maxNums[i]);
        }

        int left = 0;
        int right = 0;
        for (int num = min; num <= max; num++) {
            while (right < len && (minNums[right] <= num && maxNums[right] >= num)) {
                right++;
            }


            while (left < right && !(minNums[left] <= num && maxNums[left] >= num)) {
                left++;
            }

            System.out.println(num + " : " + left + " : " + right);
            res = Math.max(res, right - left);
        }


        return res;
    }


}
