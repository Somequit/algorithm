package leetcode.contest_vp.double_107_vp;


import template.Algorithm;
import utils.AlgorithmUtils;

import java.util.Arrays;

/**
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
            String[] words = AlgorithmUtils.systemInArrayString();

            int res = contest.solution(words);
            System.out.println(res);

            int res2 = contest.solutionOptimization(words);
            System.out.println(res2);
        }

    }

    /**
     * @return
     */
    private int solution(String[] words) {
        int n = words.length;
        if (n == 1) {
            return words[0].length();
        }

        int inf = 100_000;
        // 前 i 个 words，以 [a,z] 开头，以 [a,z] 结尾
        int[][][] dp = new int[n][26][26];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                Arrays.fill(dp[i][j], inf);
            }
        }
        dp[1][getStrBegin(words[0])][getStrEnd(words[1])] = words[0].length() + words[1].length()
                - (getStrEnd(words[0]) == getStrBegin(words[1]) ? 1 : 0);

        dp[1][getStrBegin(words[1])][getStrEnd(words[0])] = words[0].length() + words[1].length()
                - (getStrEnd(words[1]) == getStrBegin(words[0]) ? 1 : 0);
//        AlgorithmUtils.systemOutArray(dp[1]);
//        System.out.println();

        for (int i = 2; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                for (int k = 0; k < 26; k++) {
                    dp[i][getStrBegin(words[i])][k] = Math.min(dp[i][getStrBegin(words[i])][k], dp[i - 1][j][k] + words[i].length()
                            - (getStrEnd(words[i]) == j ? 1 : 0));

                    dp[i][j][getStrEnd(words[i])] = Math.min(dp[i][j][getStrEnd(words[i])], dp[i - 1][j][k] +  + words[i].length()
                            - (getStrBegin(words[i]) == k ? 1 : 0));
                }
            }
//            AlgorithmUtils.systemOutArray(dp[i]);
//            System.out.println();
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                res = Math.min(res, dp[n - 1][i][j]);
            }
        }

        return res;
    }

    private int getStrBegin(String word) {
        return word.charAt(0) - 'a';
    }
    private int getStrEnd(String word) {
        return word.charAt(word.length() - 1) - 'a';
    }

    private int solutionOptimization(String[] words) {
        int inf = 100_000;
        int n = words.length;

        int[][] headDp = new int[2][26];
        Arrays.fill(headDp[0], inf);
        headDp[0][words[0].charAt(0) - 'a'] = words[0].length();

        int[][] tailDp = new int[2][26];
        Arrays.fill(tailDp[0], inf);
        tailDp[0][words[0].charAt(words[0].length() - 1) - 'a'] = words[0].length();

        for (int i = 1; i < n; i++) {
            int preHead = words[i - 1].charAt(0) - 'a';
            int preTail = words[i - 1].charAt(words[i - 1].length() - 1) - 'a';
            int curHead = words[i].charAt(0) - 'a';
            int curTail = words[i].charAt(words[i].length() - 1) - 'a';

            Arrays.fill(headDp[i & 1], inf);
            Arrays.fill(tailDp[i & 1], inf);
            for (int j = 0; j < 26; j++) {
                if (headDp[(i - 1) & 1][j] == inf) {
                    continue;
                }
                // 放后面
                headDp[i & 1][j] = Math.min(headDp[i & 1][j], headDp[(i - 1) & 1][j] - (preTail == curHead ? 1 : 0) + words[i].length());

                // 放前面
                tailDp[i & 1][preTail] = Math.min(tailDp[i & 1][preTail], headDp[(i - 1) & 1][j] - (j == curTail ? 1 : 0) + words[i].length());
            }

            for (int j = 0; j < 26; j++) {
                if (tailDp[(i - 1) & 1][j] == inf) {
                    continue;
                }
                // 放前面
                tailDp[i & 1][j] = Math.min(tailDp[i & 1][j], tailDp[(i - 1) & 1][j] - (preHead == curTail ? 1 : 0) + words[i].length());

                // 放后面
                headDp[i & 1][preHead] = Math.min(headDp[i & 1][preHead], tailDp[(i - 1) & 1][j] - (j == curHead ? 1 : 0) + words[i].length());
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            res = Math.min(res, Math.min(headDp[(n - 1) & 1][i], tailDp[(n - 1) & 1][i]));
        }
        return res;
    }

}
