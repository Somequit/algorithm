package leetcode.contest.double_170;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/11/22 10:58 下午
 */
public class Double_170_1 {

    public int minimumFlips(int n) {
        String binaryString = Integer.toBinaryString(n);
        int len = binaryString.length();
        int res = 0;
        for (int i = 0; i < len; i++) {
            if (binaryString.charAt(i) != binaryString.charAt(len - i - 1)) {

                res++;
            }
        }

        return res;
    }
}
