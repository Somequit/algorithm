package leetcode.contest.contest_354;

import utils.AlgorithmUtils;


/**
 *
 */
public class Contest1 {

    public static void main(String[] args) {
        Contest1 contest = new Contest1();

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();

            int res = contest.solution(nums);
            System.out.println(res);
        }

    }

    public int solution(int[] nums) {
        int res = 0;

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (n % (i + 1) == 0) {
                res += nums[i] * nums[i];
            }
        }
        return res;
    }


}
