package leetcode.contest.contest_484;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2026/1/11 10:13 上午
 */
public class Contest_484_1 {
    public int residuePrefixes(String s) {
        int res = 0;
        Set<Character> set = new HashSet<>();
        int preCnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (!set.contains(s.charAt(i))) {
                set.add(s.charAt(i));
                preCnt++;
            }

            if (preCnt == (i + 1) % 3) {
                res++;
            }
        }

        return res;
    }
}
