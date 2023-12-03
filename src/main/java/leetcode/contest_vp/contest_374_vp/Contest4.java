package leetcode.contest_vp.contest_374_vp;


import utils.AlgorithmUtils;

/**
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
            int n = AlgorithmUtils.systemInNumberInt();
            int[] sick = AlgorithmUtils.systemInArray();

            int res = contest.numberOfSequence(n, sick);
            System.out.println(res);
        }

    }

    /**
     * 方案数 * 排列数
     * 方案数 = 2 ^（per1-1 + ... + per(k-1)-1）
     * 排序数：per0 ... perk，总和为 sum，每个 per 只有 1 种排序方式（从前到后顺序放入），两种思考方式（公式化简后可以互相转化）
     *     1. 先不管 per 顺序、仅看 sum 的总排序数为 sum!，每种 per 的总排序数 per! 变成 1 种排序方式，结果就是 sum！/(per0!*per1!*...*perk!)
     *     2. 从 s 个空位中找到 per0 个位置顺序放入、结果为 C(s,per0)，接着从 s-per0 个空位找 per1 个位置顺序放入、结果为 C(s-per0,per1) ... ,
     *     总结果就是 C(s,per0)*C(s-per0,per1)*...*C(s-per0-...-per(k-1),perk)（可以化简为 sum！/(per0!*per1!*...*perk!)）
     * @return
     */
    public int numberOfSequence(int n, int[] sick) {
        long res = 1;

        // sum!
        long perTotal = FAC[n - sick.length];

        // 排序数：per0，代表第一个感冒左边多少个人
        int per = sick[0];
        perTotal *= INV_FAC[per];
        perTotal %= MOD;

        // 左右边的未感冒者仅有 1 种方案数（从右到左+从左到右）
        int ansPow = 0;

        for (int i = 1; i < sick.length; i++) {
            // 排序数：per1 ... per(k-1)
            per = sick[i] - sick[i - 1] - 1;
            perTotal *= INV_FAC[per];
            perTotal %= MOD;

            // 中间的未感冒者有 2^(per-1) 种方案数
            if (per > 0) {
                ansPow += per - 1;
            }
        }

        // 排序数：perk，代表最后一个感冒右边多少个人
        per = n - sick[sick.length - 1] - 1;
        perTotal *= INV_FAC[per];
        perTotal %= MOD;

        res = perTotal * qPow(2, ansPow, MOD) % MOD;

        return (int)(res);
    }

    /**
     * 方案数 * 排列数：直接使用排列
     * @return
     */
    public int numberOfSequence2(int n, int[] sick) {
        long res = 1;

        // sum
        int sum = n - sick.length;

        // 排序数：per0，代表第一个感冒左边多少个人
        int per = sick[0];

        long perTotal = comb(sum, per);
        perTotal %= MOD;
        sum -= per;

        // 左右边的未感冒者仅有 1 种方案数（从右到左+从左到右）
        int ansPow = 0;

        for (int i = 1; i < sick.length; i++) {
            // 排序数：per1 ... per(k-1)
            per = sick[i] - sick[i - 1] - 1;

            perTotal *= comb(sum, per);
            perTotal %= MOD;
            sum -= per;

            // 中间的未感冒者有 2^(per-1) 种方案数
            if (per > 0) {
                ansPow += per - 1;
            }
        }

        // 排序数：perk，代表最后一个感冒右边多少个人
        per = n - sick[sick.length - 1] - 1;

        perTotal *= comb(sum, per);
        perTotal %= MOD;

        res = perTotal * qPow(2, ansPow, MOD) % MOD;

        return (int)(res);
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

    private static long comb(int n, int k) {
        return FAC[n] * INV_FAC[k] % MOD * INV_FAC[n - k] % MOD;
    }

}
