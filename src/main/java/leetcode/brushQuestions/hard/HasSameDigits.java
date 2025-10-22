package leetcode.brushQuestions.hard;

/**
 * @author gusixue
 * @description 3463. 判断操作后字符串中的数字是否相等 II
 * @date 2025/10/23 12:58 上午
 */
public class HasSameDigits {

    public static void main(String[] args) {

    }

    // 组合数模板
    private static final int MOD = 10;
    private static final int MX = 100_000;
    // 阶乘除去所有 2与5 因子
    private static final long[] FAC = new long[MX];
    // 阶乘除去所有 2与5 因子的逆元
    private static final long[] INV_FAC = new long[MX];
    // 阶乘中 2与5 因子的个数
    private static final long[] FACTOR2 = new long[MX];
    private static final long[] FACTOR5 = new long[MX];
    // 2与5 的次方模10，2的次方模10为[2,4,8,6]循环，5^i % 10 = 5 恒成立
    private static final long[] POW2 = new long[]{2,4,8,6};

    static {
        FAC[0] = 1;
        int[] notFac25 = new int[MX];
        for (int i = 1; i < MX; i++) {
            FACTOR2[i] = FACTOR2[i - 1];
            FACTOR5[i] = FACTOR5[i - 1];

            int temp = i;
            while (temp % 2 == 0) {
                FACTOR2[i]++;
                temp /= 2;
            }
            while (temp % 5 == 0) {
                FACTOR5[i]++;
                temp /= 5;
            }
            notFac25[i] = temp;

            FAC[i] = FAC[i - 1] * temp % MOD;

//            System.out.println(FACTOR2[i] + " : " + FACTOR5[i] + " : " + POW2[i] + " : " + POW5[i] + " : " + temp);
        }

        INV_FAC[MX - 1] = qPow(FAC[MX - 1], MOD - 2, MOD);
        for (int i = MX - 1; i > 0; i--) {
            INV_FAC[i - 1] = INV_FAC[i] * notFac25[i] % MOD;
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

    /**
     * 第一个数为 [0, n-2] 每个数按照杨辉三角乘积来贡献，第二个数类似的为 [1, n-1]，注意最后求俩数，因此为 n-2，
     * 杨辉三角第 n 层为 C(n,0),C(n,1) ... C(n,n-1),C(n,n)，即 1，n!/1!/(n-1)! ... n!/(n-1)!/(n-(n-1))!,1，最后 %10，
     * 加法和乘法取模具备结合律，同时有除法，因此需要求 1! ... n! 对 10 取模的逆元，
     * 由于 10 不是素数也不会与 [1,n] 互质，因此将提取阶乘因子中的所有 2和5，将分子中的 2与5 减去分母中的 2与5，由于分子一定整除分母，因此可以减完，减完后一定互质再 mod=10的逆元即可，分子剩下的因子如果存在 2*5 则结果为 0、否则直接乘回去
     * 优化：可以不用求两次杨辉三角乘积，改成 两数相减是否等于0，即 C(n-2,0)*(s[0]-s[1]) + C(n-2,1)*(s[1]-s[2])* ... *C(n-2,n-3)*(s[n-3]-s[n-2])+C(n-2,n-2)*(s[n-2]-s[n-1])
     * 时间复杂度：O（n)），空间复杂度：O（n）
     */
    public boolean hasSameDigits(String s) {
        int n = s.length();
        int res = 0;
        for (int i = 0; i <= n - 2; i++) {
            int num1 = s.charAt(i) - '0';
            int num2 = s.charAt(i + 1) - '0';

            // 因子 2与5 还剩下多少
            int factor2Count = (int) (FACTOR2[n - 2] - FACTOR2[i] - FACTOR2[n - 2 - i]);
            int factor5Count = (int) (FACTOR5[n - 2] - FACTOR5[i] - FACTOR5[n - 2 - i]);
            res += FAC[n - 2] * INV_FAC[i] * INV_FAC[n - 2 - i]
                    * (factor2Count > 0 ? POW2[(factor2Count - 1) % 4] : 1)
                    * (factor5Count > 0 ? 5 : 1)
                    * (num1 - num2);
//            System.out.println(factor2Count + " : " + factor5Count + " : " + FAC[n - 2] + " : " + INV_FAC[i] + " : " + INV_FAC[n - 2 - i] + " : " + POW2[factor2Count] + " : " + POW5[factor5Count]);
            res %= 10;

        }

        return res == 0;
    }
}
