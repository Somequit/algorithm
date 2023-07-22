package leetcode.contest.double_109;

import utils.AlgorithmUtils;


/**
 * @author gusixue
 * @date 2023/7/22
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
            int x = AlgorithmUtils.systemInNumberInt();

            long res = contest.solution(nums, x);
            System.out.println(res);
        }

    }

    private long solution(int[] nums, int x) {
        // 0-偶数 1-奇数
        long[][] dp = new long[nums.length][2];

        long negativeInf = -1_000_000_000L;

        if ((nums[0] & 1) == 1) {
            dp[0][1] = nums[0];
            dp[0][0] = negativeInf;
        } else {
            dp[0][0] = nums[0];
            dp[0][1] = negativeInf;
        }

        for (int i = 1; i < nums.length; i++) {
            if ((nums[i] & 1) == 1) {
                dp[i][1] = Math.max(dp[i - 1][1] + nums[i], dp[i - 1][0] + nums[i] - x);
                dp[i][0] = dp[i - 1][0];
            } else {
                dp[i][0] = Math.max(dp[i - 1][0] + nums[i], dp[i - 1][1] + nums[i] - x);
                dp[i][1] = dp[i - 1][1];
            }
        }

        return Math.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
    }



}
