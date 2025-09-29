package leetcode.contest.contest_469;

import utils.AlgorithmUtils;

import java.util.Arrays;

/**
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
            int n = AlgorithmUtils.systemInNumberInt();
            int l = AlgorithmUtils.systemInNumberInt();
            int r = AlgorithmUtils.systemInNumberInt();

            int res = contest.zigZagArrays(n, l, r);
            System.out.println(res);
        }

    }

    /**
     * @return
     */
    public int zigZagArrays(int n, int l, int r) {
        int mod = (int) 1e9 + 7;

        long res = 0;
        // 升降升，降升降本质上和这个相同、可看做前面加一个 l-1 就变成了升降升
        long[][] incDp = new long[n][r + 1];
        for (int i = l; i < r + 1; i++) {
            incDp[0][i] = 1;
        }
        res = doZigZagDp(incDp, n, l, r, mod);
//        Arrays.stream(incDp).forEach(t -> System.out.println(Arrays.toString(t)));


//        System.out.println("res:" + res);
//        // 降升降，最前面加一个最小值 l-1，就变成长度为 n+1 的 升降升
//        long[][] decDp = new long[n + 1][r + 1];
//        decDp[0][l - 1] = 1;
//        res += doZigZagDp(decDp, n + 1, l, r, mod);
//        Arrays.stream(decDp).forEach(t -> System.out.println(Arrays.toString(t)));

        return (int) (res * 2 % mod);

    }

    /**
     * 以 升降升 方式计算 dp
     * 返回第 n 位时，所有情况的总和
     */
    private long doZigZagDp(long[][] dp, int n, int l, int r, int mod) {
        // 前 i 位，小于 j 的 dp 总和
        long[][] prefixDp = new long[n][r + 2];

        // 初始化前缀和（前闭后开区间）
        for (int i = l; i < r + 2; i++) {
            prefixDp[0][i] = dp[0][i - 1] + prefixDp[0][i - 1];
            prefixDp[0][i] %= mod;
        }

        for (int i = 1; i < n; i++) {
            for (int j = l; j < r + 1; j++) {
                // 比前一个元素大
                if (i % 2 == 1) {
                    dp[i][j] = prefixDp[i - 1][j];

                // 比前一个元素小
                } else {
                    dp[i][j] = prefixDp[i - 1][r + 1] + mod - prefixDp[i - 1][j + 1];
                    dp[i][j] %= mod;
                }

                prefixDp[i][j] = dp[i][j - 1] + prefixDp[i][j - 1];
                prefixDp[i][j] %= mod;
            }

            prefixDp[i][r + 1] = dp[i][r] + prefixDp[i][r];
            prefixDp[i][r + 1] %= mod;
        }
//        Arrays.stream(prefixDp).forEach(t -> System.out.println(Arrays.toString(t)));
//        System.out.println();

        return prefixDp[n - 1][r + 1];
    }


}
