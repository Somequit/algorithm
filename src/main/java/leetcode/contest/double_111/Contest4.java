package leetcode.contest.double_111;


import utils.AlgorithmUtils;

/**
 * @author gusixue
 * @date 2023/7/22
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
            int low = AlgorithmUtils.systemInNumberInt();
            int high = AlgorithmUtils.systemInNumberInt();
            int k = AlgorithmUtils.systemInNumberInt();

            int res = contest.solution(low, high, k);
            System.out.println(res);
        }

    }

    private int solution(int low, int high, int k) {
        int res = doNumberOfBeautifulIntegers(high, k) - doNumberOfBeautifulIntegers(low - 1, k);
        return res;
    }

    private int doNumberOfBeautifulIntegers(int num, int k) {
        String numStr = num + "";
        int numStrLen = numStr.length();
//        System.out.println(num + " : " + numStr + " : " + numStrLen);


        int res = 0;
        for (int i = 2; i <= numStrLen; i+=2) {
            if (i < numStrLen) {
                res += doBeautifulByDigMin(i, k);

            } else {
                res += doBeautifulByDigEquals(numStrLen, numStr, k);

            }
        }
//        System.out.println(res);
        return res;
    }

    private int doBeautifulByDigEquals(int len, String numStr, int k) {
        // 最高位到最低位 - 偶数个数 - mod k - 对应前面位置等于(0)/小于(1)num
        int[][][][] dp = new int[len + 1][len + 1][k][2];

        dp[0][0][0][0] = 1;

        // 第一位只能为 1-numStr[0]，后面可以为 0-9
        for (int j = 0; j < len + 1; j++) {
            for (int k1 = 0; k1 < k; k1++) {

                for (int m = 1; m < numStr.charAt(0) - '0'; m++) {
                    if (m % 2 == 0) {
                        if (j > 0) {
                            dp[1][j][(k1 * 10 + m) % k][1] += dp[0][j - 1][k1][0];
                        }
                    } else {
                        dp[1][j][(k1 * 10 + m) % k][1] += dp[0][j][k1][0];
                    }
                }

                int m = numStr.charAt(0) - '0';
                if (m % 2 == 0) {
                    if (j > 0) {
                        dp[1][j][(k1 * 10 + m) % k][0] += dp[0][j - 1][k1][0];
                    }
                } else {
                    dp[1][j][(k1 * 10 + m) % k][0] += dp[0][j][k1][0];
                }
            }
        }

        for (int i = 2; i < len + 1; i++) {
            for (int j = 0; j < len + 1; j++) {
                for (int k1 = 0; k1 < k; k1++) {

                    for (int m = 0; m < (numStr.charAt(i - 1) - '0'); m++) {
                        if (m % 2 == 0) {
                            if (j > 0) {
                                dp[i][j][(k1 * 10 + m) % k][1] += dp[i - 1][j - 1][k1][0] + dp[i - 1][j - 1][k1][1];
                            }
                        } else {
                            dp[i][j][(k1 * 10 + m) % k][1] += dp[i - 1][j][k1][0] + dp[i - 1][j][k1][1];
                        }
                    }

                    int m = numStr.charAt(i - 1) - '0';
                    if (m % 2 == 0) {
                        if (j > 0) {
                            dp[i][j][(k1 * 10 + m) % k][0] += dp[i - 1][j - 1][k1][0];
                            dp[i][j][(k1 * 10 + m) % k][1] += dp[i - 1][j - 1][k1][1];
                        }
                    } else {
                        dp[i][j][(k1 * 10 + m) % k][0] += dp[i - 1][j][k1][0];
                        dp[i][j][(k1 * 10 + m) % k][1] += dp[i - 1][j][k1][1];
                    }


                    for (m = (numStr.charAt(i - 1) - '0') + 1; m < 10; m++) {
                        if (m % 2 == 0) {
                            if (j > 0) {
                                dp[i][j][(k1 * 10 + m) % k][1] += dp[i - 1][j - 1][k1][1];
                            }
                        } else {
                            dp[i][j][(k1 * 10 + m) % k][1] += dp[i - 1][j][k1][1];
                        }
                    }

                }
            }
        }

//        System.out.println(len + " : " + k + " : " + numStr + " : " + dp[len][len/2][0][0] + " : " + dp[len][len/2][0][1]);
        return dp[len][len/2][0][0] + dp[len][len/2][0][1];
    }

    private int doBeautifulByDigMin(int len, int k) {
        // 最高位到最低位 - 偶数个数 - mod k
        int[][][] dp = new int[len + 1][len + 1][k];

        dp[0][0][0] = 1;

        // 第一位只能为 1-9，后面可以为 0-9
        for (int j = 0; j < len + 1; j++) {
            for (int k1 = 0; k1 < k; k1++) {
                for (int m = 1; m < 10; m++) {
                    if (m % 2 == 0) {
                        if (j > 0) {
                            dp[1][j][(k1 * 10 + m) % k] += dp[0][j - 1][k1];
                        }
                    } else {
                        dp[1][j][(k1 * 10 + m) % k] += dp[0][j][k1];
                    }
                }
            }
        }

        for (int i = 2; i < len + 1; i++) {
            for (int j = 0; j < len + 1; j++) {
                for (int k1 = 0; k1 < k; k1++) {
                    for (int m = 0; m < 10; m++) {
                        if (m % 2 == 0) {
                            if (j > 0) {
                                dp[i][j][(k1 * 10 + m) % k] += dp[i - 1][j - 1][k1];
                            }
                        } else {
                            dp[i][j][(k1 * 10 + m) % k] += dp[i - 1][j][k1];
                        }
                    }
                }
            }
        }

//        System.out.println(len + " : " + k + " : " + dp[len][len / 2][0]);
        return dp[len][len / 2][0];
    }

}
