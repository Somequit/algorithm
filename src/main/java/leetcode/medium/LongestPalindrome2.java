package leetcode.medium;

/**
 * 5. 最长回文子串
 * 最长回文子串，给你一个字符串 s，找到 s 中最长的回文子串。
 * 循环暴力解法
 */
public class LongestPalindrome2 {

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
        assert "a".equals(solution("ab"));
        System.out.println();
        assert "bcb".equals(solution("abcbd"));
        System.out.println();
        assert "AAAABAABAAAA".equals(solution("AAAABAABAAAABAAA"));
        System.out.println();
        assert "AAAABADABAAAA".equals(solution("AAAABADABAAAABAAA"));
        System.out.println();
        assert "bcb".equals(solution("eabcb"));

        }

/**
 * 假设[i，j]为回文串、那么有两种情况（注：i<=j）
 * 奇数个字符：以k（i+j>>1）为中心，[i,k]等于[j,k]
 * 偶数个字符：以k（i+j>>1）与k+1为中心，[i,k]等于[j,k+1]
 * 先枚举每个k为中心，查询最长的回文；再枚举k k+1为中心，查询最长的回文
 * 时间复杂度：O(n*n)，额外空间复杂度：O(1)
 * @param s 求回文的文本串
 */
private static String solution(String s) {
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

}
