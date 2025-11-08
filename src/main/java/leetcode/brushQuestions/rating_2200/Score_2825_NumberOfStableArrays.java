package leetcode.brushQuestions.rating_2200;

/**
 * @author gusixue
 * @description 3130. 找出所有稳定的二进制数组 II
 * @date 2025/11/2 6:02 下午
 */
public class Score_2825_NumberOfStableArrays {

    public static void main(String[] args) {
        (new Score_2825_NumberOfStableArrays()).numberOfStableArrays(3, 3, 2);
    }

    /**
     * 动态规划：定义 dp[i][j][0/1] 代表 i 个 0，j 个 1，最后一个为 0/1，（前 i 个这种方法求前缀和比较困难）
     * dp[i][j][0] = dp[i-1][j][1] + ... + dp[i-l][j][1]（1<=l<=min(limit,i-1,j)） 代表最后一位是 0 时，前一个 1 可在某个范围内放置、同时他们之间都是 0，dp[i][j][1] 同理
     * dp 求得和可以使用前缀和优化，
     * 注意：只能使用一维前缀和，即 prevDp[i][1][0] = prevDp[i][0][0] + dp[i][0][0]，否则二维前缀和是行列而非区间
     * 注意初始化：连续 [0,limit] 个 0/1
     */
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
