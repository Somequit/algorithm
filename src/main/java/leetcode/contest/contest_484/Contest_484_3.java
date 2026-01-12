package leetcode.contest.contest_484;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2026/1/11 10:13 上午
 */
public class Contest_484_3 {
    public long countPairs(String[] words) {
        Map<String, Integer> mapCnt = new HashMap<>();
        long res = 0;
        for (String word : words) {
            StringBuilder wordTmp = doWord(word);

            mapCnt.merge(wordTmp.toString(), 1, Integer::sum);
            res += mapCnt.get(wordTmp.toString()) - 1;
        }

        return res;
    }

    private StringBuilder doWord(String word) {
        int cnt = ('z' - word.charAt(0) + 1) % 26;

        StringBuilder res = new StringBuilder();
        res.append('a');
        for (int i = 1; i < word.length(); i++) {
            res.append((char) (((word.charAt(i) - 'a' + cnt) % 26) + 'a'));
        }
//        System.out.println(res.toString());

        return res;
    }
}
