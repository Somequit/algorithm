package leetcode.contest_vp.contest_380_vp;

import java.util.*;
/**
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

//            int res = contest.solution(n, nums, list);
//            System.out.println(res);
        }

    }

    /**
     * @return
     */
    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        List<Integer> res = new ArrayList<>();

        List<Integer> bMatchList = KmpMatchTotal(s, b, getNext(new StringBuilder(b)));
        int[] prefix = new int[s.length() + 1];
        for (int bMatchIndex : bMatchList) {
            prefix[bMatchIndex + 1]++;
        }
        for (int i = 1; i < prefix.length; i++) {
            prefix[i] += prefix[i - 1];
        }

        List<Integer> aMatchList = KmpMatchTotal(s, a, getNext(new StringBuilder(a)));
        for (int aMatchIndex : aMatchList) {
            if (prefix[Math.min(aMatchIndex + k + 1, s.length())] - prefix[Math.max(aMatchIndex - k, 0)] > 0) {
                res.add(aMatchIndex);
            }
        }

        return res;
    }

    /**
     * 求 next 数组，next 数组指的是匹配串当前下标的为结尾的前缀、最大能与从头开始的后缀匹配的个数
     *
     * @param pattern 匹配串（小串）
     */
    private static int[] getNext(StringBuilder pattern) {
        int[] next = new int[pattern.length()];

        int p = 0;
        for (int i = 1; i < pattern.length(); i++) {
            char patCharI = pattern.charAt(i);
            while (p > 0 && pattern.charAt(p) != patCharI) {
                /**
                 * 核心跳转：（p - 1）为结尾的 next 个字符、与从头开始的 next 个字符与相匹配、也与 （i - 1）为结尾的 next 个字符相匹配
                 * 举例子：
                 * 下标：    0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
                 * 数组：    A A A A B A A B A A A  A  B  A  A  A
                 * next数组：0 1 2 3 0 1 2 0 1 2 3  4  5  6  7  3
                 * next[15]可以看出来，此时 i 为 15，p 为 7
                 */
                p = next[p - 1];
            }

            if (patCharI == pattern.charAt(p)) {
                p++;
            }

            next[i] = p;
        }
        return next;
    }

    /**
     * kmp根据next数组匹配
     *
     * @param matchStr  文本串（大串）
     * @param pattern   匹配串（小串）
     * @param next      next数组
     * @return 返回文本串匹配上所有初始位置
     */
    public List<Integer> KmpMatchTotal(String matchStr, String pattern, int[] next) {
        List<Integer> res = new ArrayList<>();

        for (int i = 0, j = 0; i < matchStr.length(); i++) {
            if (j > 0 && matchStr.charAt(i) != pattern.charAt(j)){
                j = next[j - 1];
            }


            if (matchStr.charAt(i) == pattern.charAt(j)) {
                j++;
            }

            // 匹配成功
            if (j == pattern.length()) {
                res.add(i + 1 - pattern.length());
                // 继续寻找下一个
                j = next[j - 1];
            }
        }
        // 匹配结束
        return res;
    }

}
