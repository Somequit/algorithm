package leetcode.brushQuestions.rating_2200;

import java.util.Arrays;

/**
 * @author gusixue
 * @description 3129. 找出所有稳定的二进制数组 I
 * @date 2025/11/2 3:10 下午
 */
public class NumberOfStableArrays_2200_1 {

    public static void main(String[] args) {
        (new NumberOfStableArrays_2200_1()).numberOfStableArrays(3, 3, 2);
    }

    public int numberOfStableArrays(int zero, int one, int limit) {
        int n = zero + one;
        int mod = (int) (1e9 + 7);

        // 前 i 个位置，拥有 j 个 0，当前位为 0/1
        int[][][] dp = new int[n + 1][zero + 1][2];

        // 连续 [0,limit] 个 0/1，注意 zero 和 one
        for (int i = 1; i <= Math.min(limit, n); i++) {
            if (i <= one) {
                dp[i][0][1] = 1;
            }
            if (i <= zero) {
                dp[i][i][0] = 1;
            }
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= Math.min(zero, i); j++) {

                for (int l = 1; l <= Math.min(limit, i - 1); l++) {
                    if (j >= l) {
                        dp[i][j][0] += dp[i - l][j - l][1];
                        dp[i][j][0] %= mod;
                    }

                    dp[i][j][1] += dp[i - l][j][0];
                    dp[i][j][1] %= mod;
                }

                System.out.print(i + ":" + j + ": " + dp[i][j][0] + " : " + dp[i][j][1] + "     ");
            }
            System.out.println();
        }

        return (dp[n][zero][0] + dp[n][zero][1]) % mod;
    }
}
