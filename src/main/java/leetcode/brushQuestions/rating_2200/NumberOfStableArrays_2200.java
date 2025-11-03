package leetcode.brushQuestions.rating_2200;

/**
 * @author gusixue
 * @description 3129. 找出所有稳定的二进制数组 I
 * @date 2025/11/2 3:10 下午
 */
public class NumberOfStableArrays_2200 {

    public static void main(String[] args) {
        (new NumberOfStableArrays_2200()).numberOfStableArrays(3, 3, 2);
    }

    /**
     * 动态规划：定义 dp[i][j][0/1] 代表前 i 位有 j 个 0，最后一位是 0/1 符合要求的总个数，
     * dp[i][j][0] = dp[i-1][j-1][1] + ... + dp[i-l][j-l][1]（1<=l<=min(limit,i-1,j)） 代表最后一位是 0 时，前一个 1 可在某个范围内放置、同时他们之间都是 0，dp[i][j][1] 同理
     * 注意初始化：连续 [0,limit] 个 0/1
     */
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
