package leetcode.hot_100.medium;

import utils.AlgorithmUtils;

/**
 * 5. 最长回文子串
 * 最长回文子串,给你一个字符串 s，找到 s 中最长的回文子串。
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
     * KMP
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


    /**
     * 循环暴力
     * 假设[i，j]为回文串、那么有两种情况（注：i<=j）
     * 奇数个字符：以k（i+j>>1）为中心，[i,k]等于[j,k]
     * 偶数个字符：以k（i+j>>1）与k+1为中心，[i,k]等于[j,k+1]
     * 先枚举每个k为中心，查询最长的回文；再枚举k k+1为中心，查询最长的回文
     * 时间复杂度：O(n*n)，额外空间复杂度：O(1)
     * @param s 求回文的文本串
     */
    private static String solution2(String s) {
        char[] arrayChar = s.toCharArray();

        int targetIndex = 0;
        int targetCount = 0;
        for (int k = 0; k < arrayChar.length; k++) {
            // 奇数个字符 将奇数、偶数合并成相同处理
            int palindromeCount = calculatePalindrome(k ,k, arrayChar);
            if (palindromeCount > targetCount) {
                targetCount = palindromeCount;
                targetIndex = k - (palindromeCount >> 1);
            }
            // 偶数个字符
            palindromeCount = calculatePalindrome(k ,k + 1, arrayChar);
            if (palindromeCount > targetCount) {
                targetCount = palindromeCount;
                targetIndex = k + 1 - (palindromeCount >> 1);
            }
        }

//        System.out.println(targetIndex + ":" + targetCount);
//        System.out.println(s);
        return s.substring(targetIndex, targetIndex + targetCount);
    }

    /**
     * 查询以k与l为中心最大的回文
     * 返回回文的长度
     */
    private static int calculatePalindrome(int k, int l, char[] arrayChar) {
        while (k >= 0 && l < arrayChar.length && arrayChar[k] == arrayChar[l]) {
            k--;
            l++;
        }
        return l - k - 1;
    }


    /**
     * Manacher（马拉车）
     * Manacher（马拉车）算法：暴力的优化算法
     * 时间复杂度：O(n)，额外空间复杂度：O(n)
     * @param s 求回文的文本串
     */
    private static String solution3(String s) {
        final char extend = '#';
        // 预处理字符串 使用一定不会出现的字符，将字符串长度设置为:2 * len + 1
        char[] arrayChar = pretreatment(s, extend);
        System.out.println(arrayChar);
        // Manacher（马拉车）算法处理字符串
        String result = manacher(arrayChar, extend);
        return result;
    }

    /**
     * 预处理字符串
     * 使用一定不会出现的字符，将字符串长度设置为:2 * len + 1
     */
    private static char[] pretreatment(String s, final char extend) {
        char[] arrayChar = new char[(s.length() << 1) + 1];
        for (int i = 0; i < arrayChar.length; i++) {
            if ((i & 1) == 1) {
                arrayChar[i] = s.charAt(i >> 1);
            } else {
                arrayChar[i] = extend;
            }
        }
        return arrayChar;
    }

    /**
     * Manacher（马拉车）算法处理字符串
     */
    private static String manacher(char[] arrayChar, final char extend) {
        int maxLen = 0;
        int[] len = new int[arrayChar.length];
        int center = -1;
        int right = -1;
        for (int i = 0; i < arrayChar.length; i++) {
            if (i >= right) {
                len[i] = palindromeHalf(i, i+1, arrayChar);
                center = i;
                right = i + len[i];
            } else {
                int iMirror = (center << 1) - i;
                int leftMirror = iMirror - len[iMirror];
                int left = (center << 1) - right;
                if (left < leftMirror) {
                    len[i] = len[iMirror];
                } else if (left > leftMirror) {
                    len[i] = iMirror - left;
                } else {
                    len[i] = palindromeHalf(i, right, arrayChar);
                    center = i;
                    right = i + len[i];
                }
            }
            if (len[maxLen] < len[i]) {
                maxLen = i;
            }
        }
        AlgorithmUtils.systemOutArray(len);

        StringBuilder result = new StringBuilder();
        int targetLeft = maxLen - len[maxLen];
        int targetRight = maxLen + len[maxLen];
        for (int i = targetLeft + 1; i < targetRight; i++) {
            if (arrayChar[i] != extend) {
                result.append(arrayChar[i]);
            }
        }
        System.out.println(result);
        return result.toString();
    }

    /**
     * 以 i 为中心，从 start 开始搜索 arrayChar 字符串
     * 返回回文半径（回文串长度的一半且算上中心字符）
     */
    private static int palindromeHalf(int i, int start, char[] arrayChar) {
        while(start < arrayChar.length && (i << 1) >= start && arrayChar[start] == arrayChar[(i << 1) - start]) {
            start++;
        }
        return start - i;
    }
}
