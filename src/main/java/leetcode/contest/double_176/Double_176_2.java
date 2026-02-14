package leetcode.contest.double_176;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2026/2/14
 */
public class Double_176_2 {

    public int prefixConnected(String[] words, int k) {
        Map<String, Integer> mapCnt = new HashMap<>();
        int res = 0;

        for (String word : words) {
            if (word.length() >= k) {
                String tmp = word.substring(0, k);
                mapCnt.merge(tmp, 1, Integer::sum);

                if (mapCnt.get(tmp) == 2) {
                    res++;
                }
            }
        }

        return res;
    }
}
