package leetcode.contest.double_176;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2026/2/14
 */
public class Double_176_1 {

    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder res = new StringBuilder();
        for (String word : words) {
            int curVal = 0;
            for (char c : word.toCharArray()) {
                curVal += weights[c - 'a'];
            }

            res.append((char) (25 - (curVal % 26) + 'a'));
        }
        return res.toString();
    }
}
