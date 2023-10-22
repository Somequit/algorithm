package leetcode.contest.contest_368_vp;


import utils.AlgorithmUtils;

import java.util.*;

/**
 * @author gusixue
 * @date 2023/10/08
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
            String s = AlgorithmUtils.systemInString();
            int k = AlgorithmUtils.systemInNumberInt();

            int res = contest.solution(s, k);
            System.out.println(res);
        }

    }

    /**String s, int k
     * @return
     */
    private int solution(String s, int k) {
        int n = s.length();

        List<Integer>[] factor = new ArrayList[n + 1];
        for (int i = 2; i < n + 1; i++) {
            factor[i] = new ArrayList<>();
            factor[i].add(1);
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    factor[i].add(j);
                }
            }
        }

        // [i,j] 长度 j-i+1 > 1，最小改变成半回文串
        Map<Integer, Integer>[][] minChangeMap = new HashMap[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int l = 0; l < factor[j - i + 1].size(); l++) {
                    memorySearch(s, minChangeMap, i, j, factor[j - i + 1].get(l));
                }
            }
        }


//        for (int i = 0; i < n; i++) {
//            for (int j = i + 1; j < n; j++) {
//                System.out.println(i + " : " + j + " : " + minChangeMap[i][j].entrySet());
//            }
//        }

        int[][] minChange = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                int minChangeNum = Integer.MAX_VALUE;
                for (int minChangeMapVal : minChangeMap[i][j].values()) {
                    minChangeNum = Math.min(minChangeNum, minChangeMapVal);
                }
                minChange[i][j] = minChangeNum;
            }
        }

        // 前 i 个字符分成 j-[0,k-1] 组最小改变，i >= 2*j+1
        int[][] dp = new int[n][k];
        // 初始化 j=0 分成一组
        for (int i = 1; i < n; i++) {
            dp[i][0] = minChange[0][i];
        }

        for (int i = 3; i < n; i++) {
            for (int j = 1; j < k; j++) {
                if (i >= 2 * j + 1) {

                    int dpVal = Integer.MAX_VALUE;
                    for (int l = 2; l <= i - 2 * j + 1; l++) {
                        dpVal = Math.min(dpVal, dp[i - l][j - 1] + minChange[i - l + 1][i]);
                    }

                    dp[i][j] = dpVal;
                } else {
                    break;
                }
            }
        }

        return dp[n - 1][k - 1];
    }

    private int memorySearch(String s, Map<Integer, Integer>[][] minChangeMap, int left, int right, int curGapLen) {
//        System.out.println("left = " + left + " : " + right);

        if (left + curGapLen > right) {
            return 0;
        }

        if (minChangeMap[left][right] != null && minChangeMap[left][right].containsKey(curGapLen)) {
            return minChangeMap[left][right].get(curGapLen);
        }

        int changeTemp = 0;
        for (int i = 1; i <= curGapLen; i++) {
            int curLeft = left + i - 1;
            int curRight = curLeft + (right - curLeft) / curGapLen * curGapLen;
//            System.out.println(curLeft + " : " + curRight);
            changeTemp += changeStr(s, curLeft, curRight);
        }
        changeTemp += memorySearch(s, minChangeMap, left + curGapLen, right - curGapLen, curGapLen);

        if (minChangeMap[left][right] == null) {
            minChangeMap[left][right] = new HashMap<>();
        }
        minChangeMap[left][right].put(curGapLen, changeTemp);

        return changeTemp;
    }

    private int changeStr(String s, int left, int right) {
        return (s.charAt(left) == s.charAt(right)) ? 0 : 1;
    }

}
