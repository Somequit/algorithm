package leetcode.contest.contest_353;

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
            int target = AlgorithmUtils.systemInNumberInt();

            int res = contest.solution(nums, target);
            System.out.println(res);
        }

    }

    public int solution(int[] nums, int target) {
        int[] dp = new int[nums.length];

        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (Math.abs(nums[j] - nums[i]) <= target && dp[i] > -1) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
        }

        return dp[nums.length - 1];
    }
}
