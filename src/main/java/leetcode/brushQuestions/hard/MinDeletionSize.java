package leetcode.brushQuestions.hard;

import java.util.*;

/**
 * @author gusixue
 * @description 960. 删列造序 III
 * @date 2025/12/23 5:16 上午
 */
public class MinDeletionSize {

    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();

        int[][] dp = new int[m][2];
        dp[0][1] = 1;

        for (int j = 1; j < m; j++) {
            dp[j][0] = Math.max(dp[j - 1][0], dp[j - 1][1]);
            dp[j][1] = 1;

            for (int jj = 0; jj < j; jj++) {

                boolean curLetterMax = true;
                for (int i = 0; i < n; i++) {
                    if (strs[i].charAt(jj) > strs[i].charAt(j)) {
                        curLetterMax = false;
                        break;
                    }
                }
                if (curLetterMax){
                    dp[j][1] = Math.max(dp[j][1], dp[jj][1] + 1);
                }

            }
        }

        return m - Math.max(dp[m - 1][0], dp[m - 1][1]);
    }
}
