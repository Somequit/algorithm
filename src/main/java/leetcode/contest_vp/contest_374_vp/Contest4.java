package leetcode.contest_vp.contest_374_vp;


import utils.AlgorithmUtils;

/**
 * 100146. 统计感冒序列的数目
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
     * 组合数学+阶乘+逆元：
     *
     * 第 1 步：
     * 分析题目易得每秒都 **有且仅仅有一位未感冒者** 被传染，且他的左或右一定有感冒的人，
     * 然后观察示例可以想到：未感冒者会组成 k 个连续区间，除了第一段以及看最后一段区间，中间区间全是左右均有感冒
     *
     * 第 2 步：
     * 因为已感冒者无法被影响，因此先在区间内部考虑，再思考区间与区间的关系，
     *
     * 第 3 步：
     * 区间内部的方案数：
     *     * 第一段或者最后一段仅有 1 种方案数，从右到左与从左到右
     *     * 中间区间 pre[i] 个人、方案数为：2 ^ (pre[i]-1)
     *         * 暴力枚举也可
     *         * DP 也行：dp[i] 代表 i 个人方案数由 i-1 个人方案数选 首/尾，且最后一个人无法选择：dp[i] = dp[i-1] * 2
     *
     * **第 4 步**：
     * 每段区间方案数相乘即 **总方案数**：1 * 2 ^（per[1]-1 + ... + per[k-1]-1）* 1
     * 但这仅是以整个区间来考虑的，如果形如：pre[0] 中选第一个、pre[2] 中选最后一个、pre[1] 中选第一个...
     * 我们将其称为排列数，
     *
     * **第 5 步**：
     * 排序数：sum！/(per0!*per1!*...*perk!)
     * 设 pre[i] 总和为 sum，每个区间 per[i] 只有 1 种排序方式（从前到后顺序放入），而区间内部如何选由方案数决定，
     * 接着我们又两种思考方式（公式化简后可以互相转化）
     *     1. 先不管 per 顺序、仅看 sum 的总排序数为 sum!，每种 per 的总排序数 per! 变成 1 种排序方式，结果就是：sum！/(per0!*per1!*...*perk!)
     *     2. 从 s 个空位中找到 per[0] 个位置顺序放入、结果为 C(s,per[0])，接着从 s-per[0] 个空位找 per[1] 个位置顺序放入、结果为 C(s-per[0],per[1]) ... ,
     *     总结果就是 C(s,per[0])*C(s-per[0],per[1])*...*C(s-per[0]-...-per[k-1],per[k])（可以化简为 sum！/(per[0]!*per[1]!*...*per[k]!)）
     *
     * 第 6 步：
     * 结果就是：每种区间内部方案数 * 区间之间的排列数
     *
     */
    public int numberOfSequence(int n, int[] sick) {
        long res = 1;

        // 排序数：sum！
        long perTotal = FAC[n - sick.length];

        // 排序数：per[0]，代表第一个感冒左边多少个人
        int per = sick[0];
        perTotal *= INV_FAC[per];
        perTotal %= MOD;

        // 每段区间方案数相乘即 **总方案数**：1 * 2 ^（per[1]-1 + ... + per[k-1]-1）* 1
        int ansPow = 0;

        for (int i = 1; i < sick.length; i++) {
            // 排序数：sum！/(per0!*per1!*...*perk!)
            per = sick[i] - sick[i - 1] - 1;
            perTotal *= INV_FAC[per];
            perTotal %= MOD;

            // 中间的未感冒者有 2 ^ (per-1) 种方案数
            if (per > 0) {
                ansPow += per - 1;
            }
        }

        // 排序数：per[k]，代表最后一个感冒右边多少个人
        per = n - sick[sick.length - 1] - 1;
        perTotal *= INV_FAC[per];
        perTotal %= MOD;

        // 每种区间内部方案数 * 区间之间的排列数
        res = qPow(2, ansPow, MOD) * perTotal % MOD;

        return (int)(res);
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
     * 方法一样，不过排列数使用：C(s,per[0])*C(s-per[0],per[1])*...*C(s-per[0]-...-per[k-1],per[k])（可以化简为 sum！/(per[0]!*per[1]!*...*per[k]!)）
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


    private static long comb(int n, int k) {
        return FAC[n] * INV_FAC[k] % MOD * INV_FAC[n - k] % MOD;
    }

}
