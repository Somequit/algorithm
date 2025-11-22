package leetcode.contest.double_170;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/11/22 10:58 下午
 */
public class Double_170_3 {

    public int[] lexSmallestNegatedPerm(int n, long target) {
        long total = (long) n * (n + 1) / 2;
        if (total < Math.abs(target) || (total - Math.abs(target)) % 2 != 0) {
            return new int[0];
        }

        long subNum = (total - target) / 2;

//        System.out.println(subNum);
        int[] res = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            if (subNum >= i + 1) {
                res[i] = -i - 1;
                subNum -= i + 1;

            } else {
                res[i] = i + 1;
            }
        }

        Arrays.sort(res);
        return res;
    }
}
