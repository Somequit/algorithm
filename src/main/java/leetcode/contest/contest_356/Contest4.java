package leetcode.contest.contest_356;

import utils.AlgorithmUtils;

import java.math.BigInteger;
import java.util.Arrays;


/**
 * Leetcode 第 356 场周赛 Problem D 2801. 统计范围内的步进数字数目（数位 DP，递推写法）
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

    /**
     * 首先将题目转化为 [1,high] - [1,low-1] 有多少个步进数字，数字为 0-9 直接返回数值，否则拆分成 [0, 10^(len-1)) + [10^(len-1), numStr]，不用处理前导零
     * 前者
     *     dp[i][j] 代表前 i 位、最后一位数字为 j 时，步进数字的个数，
     *     转移方程：j=0 时、dp[i][j]=dp[i-1][j+1]，j=9 时、dp[i][j] = dp[i-1][j-1]，
     *     否则 dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1]
     *     dp[i][j] 的总和就是答案
     * 后者
     *     dp[i][j][k] 代表前 i 位、第一位非 0、最后一位为 j 且前面 k=（0-小于 1-等于 numStr 前 i 位）有多少步进数字，
     *     转移方程：dp[i][j][1]=dp[i-1][j-1][1]+dp[i-1][j+1][1]，注意 0<=j<=9、填写的 j 等于 numStr[i-1]
     *     dp[i][j][0]=dp[i-1][j-1][0]+dp[i-1][j+1][0]+dp[i-1][j-1][1]+dp[i-1][j+1][1]，注意 0<=j<=9、dp[i-1][j-1][1]+dp[i-1][j+1][1] 需要填写的 j 小于 numStr[i-1]
     *     dp[len][j][k] 的总和就是答案
     * 最后结果可能为负数，需要使用 (x % mod + mod) % mod
     * len 位 high 的长度，10 为数字 0-9 总和，时间复杂度：O(10*len)，空间复杂度：O(10*len)
     */
    public int solution(String low, String high) {
        int mod = 1_000_000_007;

        // [1,high] 有多少个步进数字
        int countHigh = doCountSteppingNumbers(high, mod);

        // [1,low-1] 有多少个步进数字
        String lowReduce = (new BigInteger(low)).subtract(BigInteger.ONE).toString();
        int countLowReduce = doCountSteppingNumbers(lowReduce, mod);

//        System.out.println(countHigh + " : " + countLowReduce);

        // 结果可能为负数，需要使用 (x % mod + mod) % mod
        return ((countHigh - countLowReduce) % mod + mod) % mod;
    }

    /**
     * 返回 [1,numStr] 有多少个步进数字
     */
    private int doCountSteppingNumbers(String numStr, int mod) {
        // 数字为 0-9 直接返回数值
        int len = numStr.length();
        if (len == 1) {
            return numStr.charAt(0) - '0';
        }

        // 返回 [0, 10^(len-1)) 有多少步进数字
        int count = doCountCompleteNumbers(len - 1, mod);
//        System.out.println("count:" + count);

        // 返回 [10^(len-1), numStr] 有多少步进数字
        count += doCountLastCompleteNumbers(len, numStr, mod);
//        System.out.println("count:" + count);

        return count % mod;
    }

    /**
     * 返回 [0, 10^(len-1)) 有多少步进数字
     */
    private int doCountCompleteNumbers(int len, int mod) {
        // dp[i][j] 代表前 i 位、最后一位数字为 j 时，步进数字的个数
        int[][] dp = new int[len + 1][10];

        Arrays.fill(dp[1], 1);
        dp[1][0] = 0;

        // 转移方程：j=0 时、dp[i][j]=dp[i-1][j+1]，j=9 时、dp[i][j] = dp[i-1][j-1]，否则 dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1]
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

    private int doCountLastCompleteNumbers(int len, String numStr, int mod) {
        // dp[i][j][k] 代表前 i 位、第一位非 0、最后一位为 j 且前面 k=（0-小于 1-等于 numStr 前 i 位）有多少步进数字
        int[][][] dp = new int[len + 1][10][2];

        // 初始化第一位非 0
        for (int j = 1; j < 10; j++) {
            // 到当前比 numStr 小就初始化
            dp[1][j][0] = (j >= numStr.charAt(0) - '0') ? 0 : 1;
            // 到当前与 numStr 相等就初始化
            dp[1][j][1] = (j != numStr.charAt(0) - '0') ? 0 : 1;
        }

        /**
         * 转移方程：
         * dp[i][j][1]=dp[i-1][j-1][1]+dp[i-1][j+1][1]，注意 0<=j<=9、填写的 j 等于 numStr[i-1]
         * dp[i][j][0]=dp[i-1][j-1][0]+dp[i-1][j+1][0]+dp[i-1][j-1][1]+dp[i-1][j+1][1]，注意 0<=j<=9、dp[i-1][j-1][1]+dp[i-1][j+1][1] 需要填写的 j 小于 numStr[i-1]
         */
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

        // 结果为填写 len 位后总和
        for (int j = 0; j < 10; j++) {
            res += dp[len][j][0];
            res %= mod;

            res += dp[len][j][1];
            res %= mod;
        }

        return res;
    }


}
