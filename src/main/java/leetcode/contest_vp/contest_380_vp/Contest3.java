package leetcode.contest_vp.contest_380_vp;

import java.util.*;
/**
 * 100160. 价值和小于等于 K 的最大数字
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();
        System.out.println(contest.getCount(6, 1));

        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

//            int res = contest.solution(n, nums, list);
//            System.out.println(res);

        }

    }

    /**
     * Java + 二分答案 + 规律：
     *
     * 第 1 步：
     * 首先 [1, maxNum] 在确定 x 后显然满足"价值和"单调非递减，因此可以二分最大值 maxNum，
     * 其次需要确定 [1, maxNum] 在确定 x 的"价值和"就行
     *
     * 第 2 步：
     * 二分答案确定上下界：
     *     * k 最小为 1，x 最小为 1 代表每一位均统计，此时结果最小、即下界为 1
     *     * k 最大为 1e15，x 最大为 8 代表每 8 位统计一次、即每 2^8 个数最少会记录 1 次，此时结果最大，而开始的 [1, 2^8-1] 在 x=8 时不统计，因此上界就是 (k+1)*2^8-1
     *
     * 第 3 步：
     * [1, maxNum] 在 x 下的"价值和"可以找规律，我们先写出 [1, 8] 的二进制：
     * 0001
     * 0010
     * 0011
     * 0100
     * 0101
     * 0110
     * 0111
     * 1000
     * 按题意最后一位往前看（可以多写几位来看）：
     *     * 第 1 位是先零位 0 然后"一位 1 一位 0"的 10 依次循环
     *     * 第 2 位是先一位 0 然后"两位 1 两位 0"的 10 依次循环
     *     * 第 3 位是先三位 0 然后"四位 1 四位 0"的 10 依次循环
     *     * 第 4 位是先七位 0 然后"八位 1 八位 0"的 10 依次循环
     * 如果我们前面加上 0，可以得到第 i 位是 "2^(i-1) 位 0 与 2^(i-1) 位 1" 的 01 依次循环，
     *
     * 第 4 步：
     * 对于 [1, maxNum] 先转化为 [0, maxNum] 总共 maxNum+1 个数，
     * 然后循环 long 总共的 63 位 i，当满足 (i % x == 0) 时，记录第 i 位"价值和"，
     * 分为前面循环的 1 + 可能有的最后一个不完整的循环 1
     * 前面循环的 1，先除循环再乘完整的 1：(maxNum + 1) / 2^i * 2^(i-1)
     * 可能有的最后一个不完整的循环 1，先减去完整循环再减去开头的 0：max((maxNum + 1) % 2^i - 2^(i-1), 0)
     * 时间复杂度：O（63*log（k<<x）），空间复杂度：O（1）
     *
     */
    public long findMaximumNumber(long k, int x) {
        // 二分答案，确定上下界
        long left = 1;
        long right = (k + 1) << x - 1;

        long res = 1;
        while (left <= right) {
            // 避免加法溢出
            long mid = ((right - left) >> 1) + left;
            // 获取 [1, mid] 在 x 下的"价值和"
            long count = getCount(mid, x);
            if (count <= k) {
                res = mid;
                left = mid + 1;

            } else {
                right = mid - 1;
            }
        }

        return res;
    }

    /**
     * 获取 [1, maxNum] 在 x 下的"价值和"
     */
    private long getCount(long maxNum, int x) {
        long res = 0;
        // long 的最大值有 63 位
        for (int i = 1; i <= 63; i++) {
            if (i % x == 0) {
                // 获取每个循环之和
                res += (maxNum + 1) / (1L << i) * (1L << i - 1);
                // 获取可能有的最后一个不完整的循环
                res += Math.max((maxNum + 1) % (1L << i) - (1L << i - 1), 0);
            }
        }

        return res;
    }


}
