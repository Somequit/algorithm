package leetcode.contest.double_108;

import utils.AlgorithmUtils;

import java.util.*;


/**
 * @author gusixue
 * 6923. 将字符串分割为最少的美丽子字符串
 * 给你一个二进制字符串 s ，你需要将字符串分割成一个或者多个 子字符串  ，使每个子字符串都是 美丽 的。
 * 如果一个字符串满足以下条件，我们称它是 美丽 的：
 * 它不包含前导 0 。
 * 它是 5 的幂的 二进制 表示。
 * 请你返回分割后的子字符串的 最少 数目。如果无法将字符串 s 分割成美丽子字符串，请你返回 -1 。
 * 子字符串是一个字符串中一段连续的字符序列。
 * 1 <= s.length <= 15
 * s[i] 要么是 '0' 要么是 '1' 。
 * @date 2023/5/14
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
            String s = AlgorithmUtils.systemInString();

            int res = contest.solution(s);
            System.out.println(res);

            int res2 = contest.solution2(s);
            System.out.println(res2);
        }

    }

    /**
     * 动态规划：首先第一位为 0 一定有前导 0，
     * 定义状态（子问题）：dp[i] 下标 i 前面结束，最少可以分割成多少美丽子字符串
     * 定义转移方程：当前为 1 前面才可以分割，然后当 [i,j] 以及 [j+1,n+1）均符合要求时 dp[i] = min(dp[j + 1] + 1)
     * 设 m 为 5 次幂的个数，n 为 s.length(n)，时间复杂度：O（n ^ 2），空间复杂度：O（n + m）
     */
    private int solution2(String s) {
        // 第一位为 0 一定有前导 0
        if (s.charAt(0) == '0') {
            return -1;
        }

        // 定义状态（子问题），多加一位方便计算
        int[] dp = new int[s.length() + 1];

        // 初始化
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[s.length()] = 0;

        // 将符合要求的 5 的次幂放入集合
        Set<Integer> beautifulSet = setBeautifulSet();

        // 定义转移方程
        doMinimumBeautifulSubstrings(s, beautifulSet, dp);

        return dp[0] == Integer.MAX_VALUE ? -1 : dp[0];
    }

    /**
     * 将符合要求的 5 的次幂放入集合
     */
    private Set<Integer> setBeautifulSet() {
        Set<Integer> beautifulSet = new HashSet<>();

        int beautifulNum = 1;
        for (int i = 0; i < 7; i++) {
            beautifulSet.add(beautifulNum);
            beautifulNum *= 5;
        }

        return beautifulSet;
    }

    /**
     * 定义转移方程
     */
    private void doMinimumBeautifulSubstrings(String s, Set<Integer> beautifulSet, int[] dp) {
        for (int i = s.length() - 1; i >= 0; i--) {

            // 当前为 1 前面才可以分割
            if (s.charAt(i) == '1') {
                int num = 0;

                // 当 [i,j] 以及 [j+1,n+1）均符合要求时 dp[i] = min(dp[j + 1] + 1)
                for (int j = i; j < s.length(); j++) {
                    // 计算 [i,j）二进制代表的数
                    num = (num << 1) + s.charAt(j) - '0';

                    if (dp[j + 1] != Integer.MAX_VALUE && beautifulSet.contains(num)) {
                        dp[i] = Math.min(dp[i], dp[j + 1] + 1);
                    }
                }
            }
//            System.out.println(i + " : " + dp[i]);
        }
    }

    private int solution(String s) {
        if (s.charAt(0) == '0') {
            return -1;
        }

        Set<Integer> beautifulSet = new HashSet<>();
        int beautifulNum = 1;
        for (int i = 0; i < 7; i++) {
            beautifulSet.add(beautifulNum);
            beautifulNum *= 5;
//            System.out.println(Integer.toBinaryString(beautifulNum));
        }

        int[] divide = new int[s.length()];
        int res = dfs(s, 0, s.length(), divide, 0, beautifulSet);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int dfs(String s, int index, int length, int[] divide, int divideNum, Set<Integer> beautifulSet) {
        if (index >= length - 1) {
//            System.out.println(Arrays.toString(divide));
            if (judgement(s, divide, beautifulSet)) {
                return divideNum + 1;
            }
            return Integer.MAX_VALUE;
        }

        int res = Integer.MAX_VALUE;
        if (s.charAt(index + 1) == '0') {
            // 不划分
            res = dfs(s, index + 1, length, divide, divideNum, beautifulSet);

        } else {
            // 不划分
            res = Math.min(res, dfs(s, index + 1, length, divide, divideNum, beautifulSet));

            // 划分
            divide[index] = 1;
            res = Math.min(res, dfs(s, index + 1, length, divide, divideNum + 1, beautifulSet));
            divide[index] = 0;

        }

        return res;
    }

    private boolean judgement(String s, int[] divide, Set<Integer> beautifulSet) {
        int now = 0;
        int pow = 0;
        for (int i = divide.length - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                now += 1 << pow;
            }
            pow++;

            if (i == 0 || divide[i - 1] == 1) {
//                System.out.print(now + " ");
                if (!beautifulSet.contains(now)) {
                    return false;
                }
                now = 0;
                pow = 0;
            }

        }
//        System.out.println();

        return true;
    }

}
