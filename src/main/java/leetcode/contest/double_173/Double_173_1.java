package leetcode.contest.double_173;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2026/1/3 10:29 下午
 */
public class Double_173_1 {
    public String reversePrefix(String s, int k) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < k; i++) {
            res.insert(0, s.charAt(i));
        }
        for (int i = k; i < s.length(); i++) {
            res.append(s.charAt(i));
        }
        return res.toString();
    }
}
