package leetcode.hard;

import utils.AlgorithmUtils;

/**
 * @author gusixue
 * @description
 * 233. 数字 1 的个数
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 * 0 <= n <= 10 ^ 9
 * @date 2023/6/27
 */
public class CountDigitOne {

    public static void main(String[] args) {
        CountDigitOne countDigitOne = new CountDigitOne();

        countDigitOne.checkTwoMethod(10000);

        while (true) {
            int n = AlgorithmUtils.systemInNumberInt();
            int res = countDigitOne.solution(n);
            System.out.println(res);

            int res2 = countDigitOne.solution2(n);
            System.out.println(res2);
        }
    }

    private void checkTwoMethod(int num) {
        for (int i = 0; i <= num; i++) {
            int res = solution(i);
            int res2 = solution2(i);
            if (res != res2) {
                System.out.println(res + " : " + res2 + " Error!");
            }
        }
        System.out.println("OK");
    }

    private int solution2(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            int num = i;
            while (num > 0) {
                if (num % 10 == 1) {
                    res++;
                }
                num /= 10;
            }
        }
        return res;
    }

    /**
     * 计算 1 在每一位出现的次数，分为 最低位、中间位（非最低、最高）、最高位 1 出现的次数，假设数字为 abcd，如下
     *     最低位：则最低位出现的次数为 [0,abc）每次都出现，且仅有 d 大于 0 则还加上 abc1
     *     中间位：第二位分为左边 [0,ab]、右边 [0,9]，[0,ab）均会出现 [0,9]，接下来就看 [ab10,ab19] 出现的次数，使用 n-ab09 小于 0 则为 0，大于 10 则为 10，第三位类似
     *     最高位：首先只要大于 1 位就有最高位，然后最高位没有左边，接着仅需要看 [1000,1999] 有哪些包含即可，使用 n-999 小于 0 则为 0，大于 10 则为 10
     * 时间复杂度：O（logn），空间复杂度：O（1）
     */
    private int solution(int n) {
        // 最低位 1 出现的次数：假设 abcd，最低位出现的次数为 [0,abc）每次都出现，且仅有 d 大于 0 则还加上 abc1
        int lowestDigitOne = n / 10 + Math.min(n % 10, 1);

        // 中间位 1 出现的次数（非最低、最高）
        int middleDigitOne = 0;

        // abcd/100=ab 求第二位，abcd/1000=a 求第三位
        long nowDivTen = 100;
        for (; nowDivTen <= n; nowDivTen *= 10) {

            // 第二位分为左边 [0,ab]、右边 [0,9]
            long leftAllDigit = n / nowDivTen + 1;
            long rightAllDigit = nowDivTen / 10;
            // [0,ab）均会出现 [0,9]
            middleDigitOne += (leftAllDigit - 1) * rightAllDigit;

            // [ab10,ab19] 出现的次数，使用 n-ab09 小于 0 则为 0，大于 10 则为 10
            long highestAllDigit = (n / nowDivTen * nowDivTen) + (nowDivTen / 10 - 1);
            middleDigitOne += Math.min(Math.max(n - highestAllDigit, 0), rightAllDigit);
        }

        // 最高位 1 出现的次数
        int highestDigitOne = 0;
        if (n > 9) {
            // 假设 abcd，仅需要看 [1000,1999] 有哪些包含即可，使用 n-999 小于 0 则为 0，大于 1000 则为 1000
            long rightAllDigit = nowDivTen / 10;
            long highestAllDigit = nowDivTen / 10 - 1;
            highestDigitOne += Math.min(Math.max(n - highestAllDigit, 0), rightAllDigit);
        }

//        System.out.println(lowestDigitOne + ":" + middleDigitOne + ":" + highestDigitOne);
        return lowestDigitOne + middleDigitOne + highestDigitOne;
    }
}
