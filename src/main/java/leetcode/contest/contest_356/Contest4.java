package leetcode.contest.contest_356;

import utils.AlgorithmUtils;

import java.math.BigInteger;
import java.util.Arrays;


/**
 *
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
            String low = AlgorithmUtils.systemInString();
            String high = AlgorithmUtils.systemInString();

            int res = contest.solution(low, high);
            System.out.println(res);
        }

    }

    public int solution(String low, String high) {
        int mod = 1_000_000_007;

        int countHigh = doCountSteppingNumbers(high, mod);

        String lowReduce = (new BigInteger(low)).subtract(BigInteger.ONE).toString();
        int countLowReduce = doCountSteppingNumbers(lowReduce, mod);

//        System.out.println(countHigh + " : " + countLowReduce);

        return ((countHigh - countLowReduce) % mod + mod) % mod;
    }

    private int doCountSteppingNumbers(String numStr, int mod) {
        if (numStr.equals("0")) {
            return 0;
        }

        int len = numStr.length();
        if (len == 1) {
            return numStr.charAt(0) - '0';
        }

        int count = doCountCompleteNumbers(len - 1, mod);
//        System.out.println("count:" + count);

        count += doCountLastCompleteNumbers(len, numStr, mod);
//        System.out.println("count:" + count);

        return count % mod;
    }

    private int doCountLastCompleteNumbers(int len, String numStr, int mod) {
        int[][][] dp = new int[len + 1][10][2];

        for (int j = 1; j < 10; j++) {
            // 到当前比 numStr 小就初始化
            dp[1][j][0] = (j >= numStr.charAt(0) - '0') ? 0 : 1;
            // 到当前与 numStr 相等就初始化
            dp[1][j][1] = (j != numStr.charAt(0) - '0') ? 0 : 1;
        }

        int res = 0;
        for (int i = 2; i < len + 1; i++) {
            for (int j = 0; j < 10; j++) {
                dp[i][j][1] = ((j == 0 || j != numStr.charAt(i - 1) - '0') ? 0 : dp[i - 1][j - 1][1])
                        + ((j == 9 || j != numStr.charAt(i - 1) - '0') ? 0 : dp[i - 1][j + 1][1]);
                dp[i][j][1] %= mod;


                dp[i][j][0] = ((j == 0) ? 0 : dp[i - 1][j - 1][0]) + ((j == 9) ? 0 : dp[i - 1][j + 1][0])
                        + ((j == 0 || j >= numStr.charAt(i - 1) - '0') ? 0 : dp[i - 1][j - 1][1])
                        + ((j == 9 || j >= numStr.charAt(i - 1) - '0') ? 0 : dp[i - 1][j + 1][1]);

                dp[i][j][0] %= mod;
            }
//            System.out.println(Arrays.toString(dp[i]));
        }

        for (int j = 0; j < 10; j++) {
            res += dp[len][j][0];
            res %= mod;

            res += dp[len][j][1];
            res %= mod;
        }

        return res;
    }

    private int doCountCompleteNumbers(int len, int mod) {
        int[][] dp = new int[len + 1][10];

        Arrays.fill(dp[1], 1);
        dp[1][0] = 0;

        int res = 9;
        for (int i = 2; i < len + 1; i++) {
            for (int j = 0; j < 10; j++) {
                dp[i][j] = (j == 0 ? 0 : dp[i - 1][j - 1]) + (j == 9 ? 0 : dp[i - 1][j + 1]);
                dp[i][j] %= mod;

                res += dp[i][j];
                res %= mod;
            }
        }

        return res;
    }
}
