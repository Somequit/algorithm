package leetcode.hot_100.first.hard;

import utils.AlgorithmUtils;

/**
 * @author gusixue
 * @description
 * 32. 最长有效括号
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * 0 <= s.length <= 3 * 10^4
 * s[i] 为 '(' 或 ')'
 * @date 2023/5/8
 */
public class LongestValidParentheses {

    public static void main(String[] args) {
        while (true) {
            String s = AlgorithmUtils.systemInString();

            int res1 = solution1(s);
            System.out.println(res1);

            int res2 = solution2(s);
            System.out.println(res2);

        }
    }

    /**
     * 最优解考虑贪心与 DP，这儿使用贪心 + 正难则反：
     * 考虑有效括号的定义：从头到尾遍历、假设 '(' 为 1、')' 为 -1，则任意前缀和非负且总和为 0 才是有效括号，
     * 从第一个 '(' 开始遍历字符串，每次出现 0 都更新结果，第一次出现负数一定是 -1 且当前为 ')'，那么前一个一定为 0 同时与开头 '(' 形成区间，
     * 接着从区间往后找到第一个 '(' 继续遍历字符串，直到遍历完成；
     *     如果遍历结束的区间和小于等于 0，那么答案就是这些区间的最大值，因为结果不会跨区间
     *     如果遍历结束的区间和大于 0，那么最后一个区间可能由于 '(' 太多，例如：'(()' 则无法处理
     *         那么正难则反、反过来循环处理一遍：从尾到该区间开头遍历、假设 '(' 为 -1、')' 为 1，则任意后缀和非负且总和为 0 才是有效括号，后续操作与上面一致
     * 结果不会跨区间原因：区间的整体和为 0、前缀和非负，如果非头开始到结尾一定为非正数（0 减非负数）、再加上后一个 ')' 则一定为负数
     * 时间复杂度为：O(n)，空间复杂度为：O(1)
     */
    private static int solution1(String s) {
        // 判空
        if (s == null || s.length() <= 0) {
            return 0;
        }

        int res = 0;
        // 从头到尾遍历
        int len = s.length();
        // 前缀和
        int prefixSum = 0;
        // 区间头下标与记录前一个区间头
        int head = -1;
        int prevHead = 0;
        for (int i = 0; i < len; i++) {
            // 找到第一个 '('
            if (head == -1) {
                if (s.charAt(i) == '(') {
                    head = i;
                    prefixSum++;

                }
            } else {
                if (s.charAt(i) == '(') {
                    prefixSum++;

                } else {
                    prefixSum--;

                    if (prefixSum == 0) {
                        res = Math.max(res, i - head + 1);

                    } else if (prefixSum == -1) {
                        prevHead = head;
                        // 区间结束，还原数据开始找下一个区间
                        prefixSum = 0;
                        head = -1;
                    }
                }
            }
        }

        // 最后一个区间和大于 0，从尾到头遍历
        if (prefixSum > 0) {

            prefixSum = 0;
            head = -1;
            for (int i = len - 1; i >= prevHead; i--) {
                // 找到第一个 ')'
                if (head == -1) {
                    if (s.charAt(i) == ')') {
                        head = i;
                        prefixSum++;

                    }
                } else {
                    if (s.charAt(i) == ')') {
                        prefixSum++;

                    } else {
                        prefixSum--;

                        if (prefixSum == 0) {
                            res = Math.max(res, head - i + 1);

                        } else if (prefixSum == -1) {
                            // 区间结束，还原数据开始找下一个区间
                            prefixSum = 0;
                            head = -1;
                        }
                    }
                }
            }
        }

        return res;
    }

    /**
     * 最优解考虑贪心与 DP，这儿使用 DP：定义一个数组 dp[i]，代表第 i 位为结尾的最长有效括号的长度，
     * 因此 '(' 位一定是 0，仅考虑 ')' 的 dp，为了方便添加第一位为非括号字符，dp 置为 0
     *     如果 i-1 位为 '('，则 i 与 i-1 成对，易得：dp[i] = dp[i-2] + 2
     *     如果 i-1 位为 ')'，如果 dp[i-1] 为 0，则 dp[i] 就为 0；否则
     *         如果 i-1-dp[i-1] 为 ')'，则无法匹配，dp[i] 为 0
     *         如果 i-1-dp[i-1] 为 '('，则可以匹配上，dp[i] = dp[i-1] + dp[i-1-dp[i-1] - 1](匹配上后再加更前一个) + 2
     * 结果就是 dp 中的最大值
     * 时间复杂度为：O(n)，空间复杂度为：O(n)
     */
    private static int solution2(String s) {
        // 判空
        if (s == null || s.length() <= 0) {
            return 0;
        }

        // 字符串头部添加 '#'
        StringBuffer sTemp = new StringBuffer(s);
        sTemp.insert(0, '#');

        int res = 0;

        // 第一位是添加符号、第二位也无法组成完整括号，因此从第三位开始循环字符串计算 dp
        int len = sTemp.length();
        int[] dp = new int[len];
        for (int i = 2; i < len; i++) {
            if (sTemp.charAt(i) == ')') {
                if (sTemp.charAt(i - 1) == '(') {
                    dp[i] = dp[i - 2] + 2;
                    res = Math.max(res, dp[i]);

                } else {
                    if (dp[i - 1] > 0) {
                        int prevParentheseIndex = i - 1 - dp[i - 1];
                        if (sTemp.charAt(prevParentheseIndex) == '(') {
                            dp[i] = dp[i - 1] + dp[prevParentheseIndex - 1] + 2;
                            res = Math.max(res, dp[i]);
                        }
                    }
                }
            }
        }

        return res;
    }
}
