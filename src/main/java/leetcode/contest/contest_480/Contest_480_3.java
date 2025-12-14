package leetcode.contest.contest_480;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/12/14 11:49 上午
 */
public class Contest_480_3 {
    public long minMoves(int[] balance) {
        int ngtIdx = -1;
        long pstTotal = 0;

        int n = balance.length;
        for (int i = 0; i < n; i++) {
            if (balance[i] < 0) {
                ngtIdx = i;

            } else {
                pstTotal += balance[i];
            }
        }

        if (ngtIdx == -1) {
            return 0;
        }
        if (pstTotal < -balance[ngtIdx]) {
            return -1;
        }

        long res = 0;
        int left = (ngtIdx - 1 + n) % n;
        int right = (ngtIdx + 1) % n;
        int curNum = -balance[ngtIdx];
        for (int i = 1; curNum > 0; i++) {
            int curTotal = Math.min(balance[left] + balance[right], curNum);
            res += (long) curTotal * i;
            curNum -= curTotal;

            left = (left - 1 + n) % n;
            right = (right + 1) % n;
        }

        return res;
    }
}
