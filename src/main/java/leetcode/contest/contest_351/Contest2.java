package leetcode.contest.contest_351;

import utils.AlgorithmUtils;


/**
 * @author gusixue
 * 给你两个整数：num1 和 num2 。
 * 在一步操作中，你需要从范围 [0, 60] 中选出一个整数 i ，并从 num1 减去 2 ^ i + num2 。
 * 请你计算，要想使 num1 等于 0 需要执行的最少操作数，并以整数形式返回。
 * 如果无法使 num1 等于 0 ，返回 -1 。
 * 1 <= num1 <= 10 ^ 9
 * -10 ^ 9 <= num2 <= 10 ^ 9
 * @date 2023/5/14
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
            int n = AlgorithmUtils.systemInNumberInt();
            int m = AlgorithmUtils.systemInNumberInt();

            int res = contest.solution(n, m);
            System.out.println(res);
        }

    }

    /**
     * 首先 num1 与 2 ^ i 大于 0，如果 num2 大于等于 num1，num1 无论减去多少次 num2+2 ^ i 都为负数，因此特殊判断
     * 然后分离 num2 与 2 ^ i，假设操作 x 次，可看做
     *     num1-num2*x 大于等于 x，因为 2 ^ i 大于 0
     *     num1-num2*x 二进制中 1 的个数小于等于 x 个，因为 2 ^ i 可以拼凑最多 x 个前 61 位 1，同时 num1-num2*x 不会超过 2 ^ 60
     * num1-num2*x 不会超过 2 ^ 60，因为最大可能结果为 10 ^ 9-(-10 ^ 9)*x，假设 x 等于 60，那么 2 ^ i 可以最大拼凑出 2 ^ 61 - 1 远大于 10 ^ 9 * 61
     * 注意：每次减去 -10 ^ 9 则会超过 int，因此需要使用 long
     * x 最大为 40，时间复杂度：O（1），空间复杂度：O（1）
     */
    public int solution(int num1, int num2) {
        // 特殊判断
        if (num1 <= num2) {
            return -1;
        }

        // 例如每次减去 -10 ^ 9 则会超过 int
        long numLong = num1;
        int res = 0;

        while (!(numLong  < 0 && num2 > 0) && res <= 40) {
            numLong -= num2;
            res++;
            // Long.bitCount 自带函数获取二进制 1 的个数，当然也可以循环减去 lowbit
            if (numLong >= res && Long.bitCount(numLong) <= res) {
                return res;
            }
        }

        return -1;
    }
}
