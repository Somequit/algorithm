package leetcode.contest.contest_350;

import utils.AlgorithmUtils;

import java.util.Arrays;


/**
 * @author gusixue
 * @date 2023/5/14
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();

            int res = contest.solution(nums);
            System.out.println(res);
        }

    }

    public int solution(int[] nums) {
        int res = Integer.MAX_VALUE;

        Arrays.sort(nums);

        for (int i = 1; i < nums.length; i++) {
            res = Math.min(res, nums[i] - nums[i-1]);
        }

        return res;
    }


}
