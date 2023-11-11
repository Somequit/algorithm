package leetcode.contest.double_117;


import utils.AlgorithmUtils;

import java.util.Arrays;

/**
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
            int n = AlgorithmUtils.systemInNumberInt();
            int limit = AlgorithmUtils.systemInNumberInt();

            long res = contest.solution(n, limit);
            System.out.println(res);
        }

    }

    /**
     * @return
     */
    private long solution(int n, int limit) {
        long[][] dp = new long[n + 1][3];
        for (int i = 0; i <= Math.min(n, limit); i++) {
            dp[i][0] = 1;
        }

        long[] prefix = new long[n + 1];
        prefix[0] = 1;
        dp[0][1] = 1;
        for (int i = 1; i <= Math.min(n, 2 * limit); i++) {
            dp[i][1] = prefix[i - 1] + dp[i][0];

            if (i > limit) {
                dp[i][1] -= prefix[i - limit - 1];
            }
            prefix[i] = prefix[i - 1] + dp[i][0];
        }

        Arrays.fill(prefix, 0);
        prefix[0] = 1;
        dp[0][2] = 1;
        for (int i = 1; i <= Math.min(n, 3 * limit); i++) {
            dp[i][2] = prefix[i - 1] + dp[i][1];

            if (i > limit) {
                dp[i][2] -= prefix[i - limit - 1];
            }
            prefix[i] = prefix[i - 1] + dp[i][1];
        }

        return dp[n][2];
    }


}
