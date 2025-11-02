package leetcode.brushQuestions.rating_2200;

/**
 * @author gusixue
 * @description 3130. 找出所有稳定的二进制数组 II
 * @date 2025/11/2 6:02 下午
 */
public class NumberOfStableArrays_2825_1 {

    public static void main(String[] args) {
        (new NumberOfStableArrays_2825_1()).numberOfStableArrays(3, 3, 2);
    }

    public int numberOfStableArrays(int zero, int one, int limit) {
        int mod = (int) (1e9 + 7);

        // i 个 0，j 个 1，最后一个为 0/1
        long[][][] dp = new long[zero + 1][one + 1][2];

        // 前缀和优化，注意前缀和不能是二维、只能是一维
        long[][][] prevDp = new long[zero + 2][one + 2][2];

        // 连续 [1,limit] 个 0/1，注意 zero 和 one
        for (int i = 1; i <= Math.min(zero, limit); i++) {
            dp[i][0][0] = 1;
            prevDp[i][1][0] = prevDp[i][0][0] + dp[i][0][0];
        }
        for (int i = 1; i <= Math.min(one, limit); i++) {
            dp[0][i][1] = 1;
            prevDp[1][i][1] = prevDp[0][i][1] + dp[0][i][1];
        }

        for (int s = 2; s <= zero + one; s++) {
            for (int i = Math.max(s - one, 0); i <= Math.min(zero, s); i++) {
                int j = s - i;

//                for (int l = 1; l <= Math.min(limit, i); l++) {
//                    dp[i][j][0] += dp[i - l][j][1];
//                    dp[i][j][0] %= mod;
//                }
//
//                for (int l = 1; l <= Math.min(limit, j); l++) {
//                    dp[i][j][1] += dp[i][j - l][0];
//                    dp[i][j][1] %= mod;
//                }

                dp[i][j][0] += prevDp[i][j][1] - prevDp[i - Math.min(limit, i)][j][1];
                dp[i][j][0] = (dp[i][j][0] + mod) % mod;
                prevDp[i][j + 1][0] = (prevDp[i][j][0] + dp[i][j][0]) % mod;

                dp[i][j][1] += prevDp[i][j][0] - prevDp[i][j - Math.min(limit, j)][0];
                dp[i][j][1] = (dp[i][j][1] + mod) % mod;
                prevDp[i + 1][j][1] = (prevDp[i][j][1] + dp[i][j][1]) % mod;
            }
        }

        return (int) ((dp[zero][one][0] + dp[zero][one][1]) % mod);
    }
}
