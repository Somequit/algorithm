package leetcode.contest_vp.contest_348_vp;


import utils.AlgorithmUtils;

import java.math.BigInteger;
import java.util.Arrays;

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


    private int solutionDigitDP(String num1, String num2, int min_sum, int max_sum) {
        this.mod = 1_000_000_007;

        int res1 = countSpecialNumbers((new BigInteger(num1)).subtract(BigInteger.ONE).toString(), min_sum, max_sum);
        int res2 = countSpecialNumbers(num2, min_sum, max_sum);

        return (res2 - res1 + this.mod) % this.mod;
    }

    private char s[];
    private int memo[][];
    private int mod;

    public int countSpecialNumbers(String str, int min_sum, int max_sum) {
        s = str.toCharArray();
        int m = s.length;

        // 记忆化搜索：前 i 位，和为多少
        memo = new int[m][max_sum + 1];
        // 初始化，-1 表示没有计算过
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        return recursionDigitDP(0, 0, min_sum, max_sum, true, false);
    }

    /**
     * 数位 DP 模板：记忆化搜索获取合法数字的个数
     * @param curIndex 从 i 位开始填数字，i 前面填的数字和是 mask
     * @param mask 数字和
     * @param minSum 数字和最小值
     * @param maxSum 数字和最大值
     * @param isLimit 前面填写的数字是否与 s 对应上，如果为 true 当前最大为 int(s[i])，否则当前最大为 9
     * @param isNum 前面是否填写过数字（即处理前导零用），如果为 true 当前可以从 0 开始，否则可以 跳过 或 从 1 开始
     * @return 返回合法数字的个数
     */
    private int recursionDigitDP(int curIndex, int mask, int minSum, int maxSum, boolean isLimit, boolean isNum) {
        // isNum 为 true 表示得到了一个合法数字
        if (curIndex == s.length) {
            return (mask >= minSum && isNum) ? 1 : 0;
        }

        // isLimit 为 true（前面与 s 一样）以及 isNum 为 false（前面全没有填）仅有一种情况，不需要记忆化
        if (!isLimit && isNum && memo[curIndex][mask] != -1) {
            return memo[curIndex][mask];
        }

        int res = 0;
        // isNum：前面是否填写过数字（即处理前导零用），可以跳过当前数位
        if (!isNum) {
            res = recursionDigitDP(curIndex + 1, mask, minSum, maxSum, false, false);
        }

        // isLimit：前面填写的数字是否与 s 对应上，如果为 true 当前最大为 int(s[i])，否则当前最大为 9（否则就超过 n 啦）
        int up = isLimit ? s[curIndex] - '0' : 9;
        // 枚举要填入的数字 d，isNum：如果为 true 当前可以从 0 开始，否则可以 跳过 或 从 1 开始
        for (int d = isNum ? 0 : 1; d <= up; d++) {
            // 填 d 后不超过 max_sum
            if (mask + d <= maxSum) {
                res += recursionDigitDP(curIndex + 1, mask + d, minSum, maxSum, isLimit && d == up, true);
                res %= this.mod;
            }
        }

        // 记忆化入数组
        if (!isLimit && isNum) {
            memo[curIndex][mask] = res;
        }
        return res;
    }

}
