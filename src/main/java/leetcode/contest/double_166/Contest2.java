package leetcode.contest.double_166;

/**
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

//            int res = contest.solution(n, nums, list);
//            System.out.println(res);
        }

    }

    /**
     * @return
     */
    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[i] = 1 + costs[i];

            } else if (i == 1) {
                dp[i] = Math.min(dp[i - 1] + 1, 4) + costs[i];

            } else if (i == 2) {
                dp[i] = Math.min(Math.min(dp[i - 1] + 1, dp[i - 2] + 4), 9) + costs[i];

            } else {
                dp[i] = Math.min(Math.min(dp[i - 1] + 1, dp[i - 2] + 4), dp[i - 3] + 9) + costs[i];
            }

        }

        return dp[n-1];
    }


}
