package leetcode.contest.contest_485;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2026/1/18 10:26 上午
 */
public class Contest_485_1 {
    public int vowelConsonantScore(String s) {
        int v = 0;
        int c = 0;
        for (char cStr : s.toCharArray()) {
            if (cStr >= 'a' && cStr <= 'z') {
                if (isVowel(cStr)) {
                    v++;
                } else {
                    c++;
                }
            }

        }

        return c == 0 ? 0 : (v / c);
    }

    private boolean isVowel(char cStr) {
        return "aeiou".indexOf(cStr) != -1;
    }

}
