package template;

/**
 * @author gusixue
 * @description 组合数模板：阶乘、逆元模板与排列 C(n,k)
 * TODO:GSX: 求逆元：
 * https://leetcode.cn/discuss/post/3584387/fen-xiang-gun-mo-yun-suan-de-shi-jie-dan-7xgu/
 * https://leetcode.cn/problems/find-triangular-sum-of-an-array/solutions/1390137/o-by-endlesscheng-952i/?envType=daily-question&envId=2025-09-30
 * @date 2023/12/3
 */
public class Factorial {

    // 组合数模板
    private static final int MOD = (int)1e9+7;
    private static final int MX = 100_000;
    // 阶乘
    private static final long[] FAC = new long[MX];
    // 阶乘的逆元
    private static final long[] INV_FAC = new long[MX];

    static {
        FAC[0] = 1;
        for (int i = 1; i < MX; i++) {
            FAC[i] = FAC[i - 1] * i % MOD;
        }

        INV_FAC[MX - 1] = qPow(FAC[MX - 1], MOD - 2, MOD);
        for (int i = MX - 1; i > 0; i--) {
            INV_FAC[i - 1] = INV_FAC[i] * i % MOD;
        }
    }

    private static long qPow(long value, long pow, long mod) {
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

    private static long comb(int n, int k) {
        return FAC[n] * INV_FAC[k] % MOD * INV_FAC[n - k] % MOD;
    }

    // 组合数
    private static final int COMB_MX = 5001;
    private static final long[][] COMBINATION;

    static {
        COMBINATION = new long[COMB_MX][COMB_MX];

        for (int i = 0; i < COMBINATION.length; i++) {
            COMBINATION[i][0] = 1;
            COMBINATION[i][i] = 1;

            for (int j = 1; j < i; j++) {
                COMBINATION[i][j] = (COMBINATION[i - 1][j] + COMBINATION[i - 1][j - 1]) % MOD;
            }
        }
    }
}
