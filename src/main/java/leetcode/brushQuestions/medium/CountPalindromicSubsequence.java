package leetcode.brushQuestions.medium;

import java.util.Arrays;

/**
 * @author gusixue
 * @description 1930. 长度为 3 的不同回文子序列
 * @date 2025/11/21 6:16 下午
 */
public class CountPalindromicSubsequence {

    public int countPalindromicSubsequence(String s) {
        int n = s.length();
        int[][] charCnt = new int[n][26];
        int[][] charIndex = new int[2][26];
        Arrays.fill(charIndex[0], -1);
        Arrays.fill(charIndex[1], -1);
        charIndex[0][s.charAt(0) - 'a'] = 0;

        charCnt[0][s.charAt(0) - 'a']++;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                charCnt[i][j] = charCnt[i - 1][j];
            }

            int curChar = s.charAt(i) - 'a';
            charCnt[i][curChar]++;

            if (charIndex[0][curChar] == -1) {
                charIndex[0][curChar] = i;

            } else {
                charIndex[1][curChar] = i;
            }
        }

        int res = 0;
        for (int i = 0; i < 26; i++) {
            if (charIndex[0][i] > -1 && charIndex[1][i] > -1) {
                for (int j = 0; j < 26; j++) {
                    if (charCnt[charIndex[1][i] - 1][j] - charCnt[charIndex[0][i]][j] > 0) {
                        res++;
                    }
                }
            }
        }

        return res;
    }
}
