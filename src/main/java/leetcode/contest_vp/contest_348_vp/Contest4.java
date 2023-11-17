package leetcode.contest_vp.contest_348_vp;


import utils.AlgorithmUtils;

import java.math.BigInteger;

/**
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

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
    private int solution(String num1, String num2, int min_sum, int max_sum) {
        int mod = 1_000_000_007;

        int res1 = lessEqualsDp((new BigInteger(num1)).subtract(BigInteger.ONE).toString(), min_sum, max_sum, mod);
        int res2 = lessEqualsDp(num2, min_sum, max_sum, mod);

        return (res2 - res1 + mod) % mod;
    }

    private int lessEqualsDp(String num, int min_sum, int max_sum, int mod) {
//        System.out.println("num : " + num);

        // 前 i 位，总和为 j，k：0-等于 maxNum，1-小于 maxNum
        long[][][] dp = new long[num.length()][max_sum + 1][2];
        for (int i = 0; i < num.charAt(0) - '0' && i <= max_sum; i++) {
            dp[0][i][1] = 1;
        }
        if (num.charAt(0) - '0' <= max_sum) {
            dp[0][num.charAt(0) - '0'][0] = 1;
        }
//        AlgorithmUtils.systemOutArray(dp[0]);

        for (int i = 1; i < num.length(); i++) {
            for (int j = max_sum; j >= 0; j--) {
                for (int k = 0; k < 10 && k <= j; k++) {
                    if (k < num.charAt(i) - '0') {
                        dp[i][j][1] += dp[i - 1][j - k][0] + dp[i - 1][j - k][1];
                        dp[i][j][1] %= mod;

                    } else if (k == num.charAt(i) - '0') {
                        dp[i][j][0] += dp[i - 1][j - k][0];
                        dp[i][j][0] %= mod;
                        dp[i][j][1] += dp[i - 1][j - k][1];
                        dp[i][j][1] %= mod;

                    } else {
                        dp[i][j][1] += dp[i - 1][j - k][1];
                        dp[i][j][1] %= mod;
                    }

                }
            }
        }

        long res = 0;
        for (int i = min_sum; i <= max_sum; i++) {
            res += dp[num.length() - 1][i][0] + dp[num.length() - 1][i][1];
            res %= mod;
        }

        return (int) res;
    }


}
