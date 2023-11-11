package leetcode.hot_100.medium;

import utils.AlgorithmUtils;

/**
 * 5. 最长回文子串
 * 最长回文子串，给你一个字符串 s，找到 s 中最长的回文子串。
 * Manacher（马拉车）算法
 */
public class LongestPalindrome3 {

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
        assert "aa".equals(solution("ecaa"));
        System.out.println();
        assert "aa".equals(solution("aace"));
        System.out.println();
        assert "aa".equals(solution("dfaace"));
        System.out.println();

        assert "aba".equals(solution("aba"));
        System.out.println();
        assert "aba".equals(solution("ecaba"));
        System.out.println();
        assert "aba".equals(solution("abace"));
        System.out.println();
        assert "aba".equals(solution("ddabace"));
        System.out.println();

        assert "aaaa".equals(solution("aaaab"));
        System.out.println();
        assert "abcdcecdcba".equals(solution("abcdcecdcba"));
        System.out.println();
        }

    /**
     * Manacher（马拉车）算法：暴力的优化算法
     * 时间复杂度：O(n)，额外空间复杂度：O(n)
     * @param s 求回文的文本串
     */
private static String solution(String s) {
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
