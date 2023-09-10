package leetcode.medium;

import utils.AlgorithmUtils;

/**
 * 5. 最长回文子串
 * 最长回文子串,给你一个字符串 s，找到 s 中最长的回文子串。
 * KMP解法
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        unitTest();
//        while (true) {
//            String s = AlgorithmUtils.systemInString();
//            System.out.println(solution(s));
//        }
    }

    private static void unitTest() {
        assert "a".equals(solution("a"));
        System.out.println();
        assert "aa".equals(solution("aa"));
        System.out.println();
        assert "b".equals(solution("ab"));
        System.out.println();
        assert "bcb".equals(solution("abcbd"));
        System.out.println();
        assert "AAAABAABAAAA".equals(solution("AAAABAABAAAABAAA"));
        System.out.println();
        assert "AAAABADABAAAA".equals(solution("AAAABADABAAAABAAA"));
        System.out.println();
        assert "bcb".equals(solution("eabcb"));
        System.out.println();
        assert "bcb".equals(solution("bcbea"));

        }

    /**
     * kmp算法模板，match 文本串倒序后当做模式串 pattern，求模式串的 next 数组后与文本串进行匹配，
     * 当匹配到文本串的末尾、这时匹配上的个数大于2（假设为res），就代表当前文本串的回文存在、从文本串结尾往前 res 个字符为回文
     * 然后将文本串删除最后一个字符，模式串删除第一个字符，再次求模式串的 next 数组后匹配...结果与 res 求最大值
     * 直到结果的回文长度等于模式串的长度为止
     * 时间复杂度：O(n*n)，额外空间复杂度：O(n)
     * @param match 求回文的文本串
     */
    private static String solution(String match) {
        StringBuilder matchTemp = new StringBuilder(match);
        StringBuilder pattern = new StringBuilder(match).reverse();

        int beginIndex = -1;
        int count = 0;
        int len = pattern.length();
        int[] next = new int[len];
        for (int i = 0; i < len; i++) {

            getNext(pattern, next);
            System.out.print("matchTemp:" + matchTemp + " pattern:" + pattern + " next:");
            AlgorithmUtils.systemOutArray(next);

            int result = kmpMatch(matchTemp, pattern, next);
            if (result > count) {
                count = result;
                beginIndex = len - result - i;
            }
            System.out.println("result:" + result + " count:" + count + " beginIndex:" + beginIndex);

            if (count >= matchTemp.length() - 1) {
                break;
            }

            matchTemp.deleteCharAt(matchTemp.length() - 1);
            pattern.delete(0, 1);
        }

        return match.substring(beginIndex, beginIndex + count);
    }

    /**
     * 求 next 数组，next 数组指的是模式串当前下标的为结尾的前缀、最大能与从头开始的后缀匹配的个数
     *
     * @param pattern 模式串
     */
    private static void getNext(StringBuilder pattern, int[] next) {
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
    }

    /**
     * kmp根据next数组匹配
     *
     * @param matchTemp 文本串
     * @param pattern   匹配串
     * @param next      next数组
     * @return 返回文本串匹配到最后一个时，匹配上匹配串的最大个数
     */
    private static int kmpMatch(StringBuilder matchTemp, StringBuilder pattern, int[] next) {
        int p = 0;
        for (int i = 0; i < matchTemp.length(); i++) {
            char matchChar = matchTemp.charAt(i);
            while (p > 0 && matchChar !=pattern.charAt(p)){
                p = next[p - 1];
            }

            if (matchChar == pattern.charAt(p)) {
                p++;
            }
        }
        return p;
    }

}
