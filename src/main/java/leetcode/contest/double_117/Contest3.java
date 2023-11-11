package leetcode.contest.double_117;


import utils.AlgorithmUtils;

import java.util.HashSet;
import java.util.Set;

/**
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();
        while (true) {
            int n = AlgorithmUtils.systemInNumberInt();

            int res = contest.solution(n);
            System.out.println(res);
        }

    }

    /**
     * @return
     */
    private int solution(int n) {
        int mod = 1_000_000_007;
        if (n < 4) {
            return 0;
        }

        // 总
        long res = qPow(26, n, mod);

        // - l0
        res = (res - qPow(25, n, mod) + mod) % mod;
        // - e0
        res = (res - qPow(25, n, mod) + mod) % mod;
        // - e1
        res = (res - (qPow(25, n - 1, mod) * n % mod) + mod) % mod;
        // - t0
        res = (res - qPow(25, n, mod) + mod) % mod;

        // + l0e0
        res = (res + qPow(24, n, mod)) % mod;
        // + l0e1
        res = (res + qPow(24, n - 1, mod) * n) % mod;
        // + l0t0
        res = (res + qPow(24, n, mod)) % mod;
        // + e0t0
        res = (res + qPow(24, n, mod)) % mod;
        // + e1t0
        res = (res + qPow(24, n - 1, mod) * n) % mod;

        // - l0e0t0
        res = (res - qPow(23, n, mod) + mod) % mod;
        // - l0e1t0
        res = (res - (qPow(23, n - 1, mod) * n % mod) + mod) % mod;

        return (int)res;
    }

    private long qPow(long value, long pow, long mod) {
        long res = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) {
                res *= value;
                res %= mod;
            }

            value *= value;
            value %= mod;
            pow >>= 1;
        }
        return res;
    }


}
