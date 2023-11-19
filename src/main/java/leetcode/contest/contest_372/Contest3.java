package leetcode.contest.contest_372;


import utils.AlgorithmUtils;

/**
 * 100119. 最大异或乘积
 * 给你三个整数 a ，b 和 n ，请你返回 (a XOR x) * (b XOR x) 的 最大值 且 x 需要满足 0 <= x < 2n。
 * 由于答案可能会很大，返回它对 109 + 7 取余 后的结果。
 * 注意，XOR 是按位异或操作。
 * 0 <= a, b < 2 ^ 50
 * 0 <= n <= 50
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
            long a = AlgorithmUtils.systemInNumber();
            long b = AlgorithmUtils.systemInNumber();
            int n = AlgorithmUtils.systemInNumberInt();

            int res = contest.solution(a, b, n);
            System.out.println(res);

            int res2 = contest.solutionOptimization(a, b, n);
            System.out.println(res2);
        }

    }

    /**
     * 位运算：[n, 49] 位中 a、b 保持不变，[0, n-1] 位可能会变，可以将 a、b 每一位拆分来看，从高位到低位中、如果相同位值相同则可以均被异或成 1，
     * 如果不同则一定是一位 1 一位 0，因此 a+b 值不会改变，则 a、b 相差越小乘积越大，
     * 直接模拟即可
     * 注意：移位成 long 时必须使用 1L << x
     * 时间复杂度：O（n），空间复杂度：O（1）
     */
    private int solution(long a, long b, int n) {
        int mod = 1_000_000_007;

        // 获取 不变的 [n, 49] 位中 a、b
        long ax = a >> n << n;
        long bx = b >> n << n;
//        System.out.println(ax + ":" + bx);
        // 将 a、b 每一位拆分，从高位到低位直接模拟
        for (int i = n - 1; i >= 0; i--) {
            long bitNum = 1L << i;
            long aBit = a & bitNum;
            long bBit = b & bitNum;

            // 如果相同位值相同则可以均被异或成 1
            if ((aBit > 0 && bBit > 0) || (aBit == 0 && bBit == 0)) {
                ax |= bitNum;
                bx |= bitNum;

            } else {
                // 如果不同则一定是一位 1（a、b 小的） 一位 0（大的）
                if (ax > bx) {
                    bx |= bitNum;

                } else {
                    ax |= bitNum;
                }
            }
        }
//        System.out.println(ax + ":" + bx);
        return (int) ((ax % mod) * (bx % mod) % mod);
    }

    /**
     * 位运算：[n, 49] 位中 a、b 保持不变，[0, n-1] 位可能会变，可以将 a、b 每一位拆分来看，从高位到低位中、如果相同位值相同则可以均被异或成 1，
     * 如果不同则一定是一位 1 一位 0，因此 a+b 值不会改变，则 a、b 相差越小乘积越大，
     * 先获取
     * [0, n-1] 相同位 不同的元素全设置为 1 定为 nDifferent，
     * [0, n-1] 相同位 相同的元素全设置为 1 定为 nSame，
     * 再处理
     * a、b [0, n-1] 位相同位 相同则均转为 1，不同则均转为 0，
     * 最后
     * a、b 中小的数加上 nDifferent，
     * a、b 相等则、一个加上 nDifferentHigh = highestOneBit(nDifferent)（nDifferent 最高位 1 代表的值），一个加上 nDifferent-nDifferentHigh
     * 注意：移位成 long 时必须使用 1L << x
     * 时间复杂度：O（1），空间复杂度：O（1）
     */
    private int solutionOptimization(long a, long b, int n) {
        int mod = 1_000_000_007;

        // [0, n-1] 位均为 1，[n, 49] 位均为 0
        long nBitNum = (1L << n) - 1;
        // 获取 a、b 中 [0, n-1] 位的值，即 [n, 49] 全清空
        long aLow = a & nBitNum;
        long bLow = b & nBitNum;
        // 获取 [0, n-1] 相同位 不同的元素全设置为 1
        long nDifferent = aLow ^ bLow;
        // 获取 [0, n-1] 相同位 相同的元素全设置为 1
        long nSame = (~nDifferent) & nBitNum;

        // a、b [0, n-1] 位相同位 相同则均转为 1，不同则均转为 0
        a = (a & (~nBitNum)) + nSame;
        b = (b & (~nBitNum)) + nSame;

        // a、b 中小的数加上 nDifferent
        if (a > b) {
            b += nDifferent;

        } else if (a < b) {
            a += nDifferent;

        // a、b 相等则、一个加上 nDifferentHigh = highestOneBit(nDifferent)（nDifferent 最高位 1 代表的值），一个加上 nDifferent-nDifferentHigh
        } else {
            long nDifferentHigh = Long.highestOneBit(nDifferent);
            a += nDifferentHigh;
            b += nDifferent - nDifferentHigh;
        }
        return (int) ((a % mod) * (b % mod) % mod);
    }


}
