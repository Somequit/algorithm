package leetcode.contest.double_111;


import utils.AlgorithmUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author gusixue
 * @date 2023/7/22
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
            List<Integer> nums = AlgorithmUtils.systemInList();

            int res = contest.solution(nums);
            System.out.println(res);
        }

    }

    private int solution(List<Integer> nums) {
        int n = nums.size();

        int[][] dp = new int[n][4];
        Arrays.fill(dp[0], 1);
        dp[0][nums.get(0)] = 0;

        int res = 0;
        for (int i = 1; i < nums.size(); i++) {
            dp[i][1] = dp[i - 1][1] + (nums.get(i) == 1 ? 0 : 1);
            res = dp[i][1];

            dp[i][2] = Math.min(dp[i - 1][2], dp[i - 1][1]) + (nums.get(i) == 2 ? 0 : 1);
            res = Math.min(dp[i][2], res);

            dp[i][3] = Math.min(dp[i - 1][3], Math.min(dp[i - 1][2], dp[i - 1][1])) + (nums.get(i) == 3 ? 0 : 1);
            res = Math.min(dp[i][3], res);

//            System.out.println(Arrays.toString(dp[i]) + " : " + res);
        }
        return res;
    }



}
