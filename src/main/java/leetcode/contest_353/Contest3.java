package leetcode.contest_353;

import utils.AlgorithmUtils;


/**
 * @author gusixue
 * @date 2023/5/14
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
            int[] nums1 = AlgorithmUtils.systemInArray();
            int[] nums2 = AlgorithmUtils.systemInArray();

            int res = contest.solution(nums1, nums2);
            System.out.println(res);
        }

    }

    public int solution(int[] nums1, int[] nums2) {
        int res = 1;

        int[][] dp = new int[nums1.length][2];
        dp[0][0] = 1;
        dp[0][1] = 1;

        for (int i = 1; i < nums1.length; i++) {
            dp[i][0] = Math.max( (nums1[i] >= nums1[i - 1] ? dp[i - 1][0] + 1 : 1),
                     (nums1[i] >= nums2[i - 1] ? dp[i - 1][1] + 1 : 1));
            dp[i][1] = Math.max( (nums2[i] >= nums1[i - 1] ? dp[i - 1][0] + 1 : 1),
                     (nums2[i] >= nums2[i - 1] ? dp[i - 1][1] + 1 : 1));

            res = Math.max(res, dp[i][0]);
            res = Math.max(res, dp[i][1]);
        }

        return res;
    }


}
