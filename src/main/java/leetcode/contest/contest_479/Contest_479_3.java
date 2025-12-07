package leetcode.contest.contest_479;

import java.util.*;


/**
 * @author gusixue
 * @description
 * @date 2025/12/7 10:28 上午
 */
public class Contest_479_3 {

    public long totalScore(int hp, int[] damage, int[] requirement) {
        long res = 0;
        int n = requirement.length;
        int[] suffix = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + damage[i];
        }

        for (int i = 0; i < n; i++) {
            int curNum = hp - requirement[i];

            res += bS(0, i, suffix, suffix[i + 1], curNum);
//            System.out.println(res);
        }

        return res;
    }

    private int bS(int left, int right, int[] suffix, int subNum, int curNum) {
        curNum += subNum;
        int rightTmp = right;

        int res = right + 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (suffix[mid] <= curNum) {
                right = mid - 1;
                res = mid;

            } else {
                left = mid + 1;
            }
        }

        return rightTmp - res + 1;
    }
}
