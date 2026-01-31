package leetcode.contest.double_175;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2026/1/31 10:29 下午
 */
public class Double_175_1 {
    public String reverseByType(String s) {
        StringBuilder wordStr = new StringBuilder();
        StringBuilder otherStr = new StringBuilder();
        StringBuilder res = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                wordStr.append(c);
            } else {
                otherStr.append(c);
            }
        }

        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                res.append(wordStr.charAt(wordStr.length() - 1));
                wordStr.deleteCharAt(wordStr.length() - 1);
            } else {
                res.append(otherStr.charAt(otherStr.length() - 1));
                otherStr.deleteCharAt(otherStr.length() - 1);
            }
        }
        return res.toString();
    }
}
