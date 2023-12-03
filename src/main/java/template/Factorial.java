package template;

/**
 * @author gusixue
 * @description 组合数模板：阶乘、逆元模板与排列 C(n,k)
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
}
