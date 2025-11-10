package leetcode.contest.contest_475;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/11/8 11:45 下午
 */
public class Contest_475_3 {

    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] score = new int[m][n][k + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < k + 1; l++) {
                    score[i][j][l] = -1;
                }
            }
        }

        score[0][0][0] = 0;

        int prefixCost = 0;
        int prefixScore = 0;
        for (int i = 1; i < m; i++) {
            prefixCost += grid[i][0] == 0 ? 0 : 1;
            prefixScore += grid[i][0];
            if (prefixCost <= k) {
                score[i][0][prefixCost] = prefixScore;

            } else {
                break;
            }
        }

        prefixCost = 0;
        prefixScore = 0;
        for (int i = 1; i < n; i++) {
            prefixCost += grid[0][i] == 0 ? 0 : 1;
            prefixScore += grid[0][i];
            if (prefixCost <= k) {
                score[0][i][prefixCost] = prefixScore;

            } else {
                break;
            }
        }

//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                for (int l = 0; l < k + 1; l++) {
//                    System.out.print(score[i][j][l] + " ");
//                }
//                System.out.print("          ");
//            }
//            System.out.println();
//        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int cost = grid[i][j] == 0 ? 0 : 1;
                for (int l = 0; l < k + 1 - cost; l++) {
                    if (score[i - 1][j][l] > -1) {
                        score[i][j][l + cost] = Math.max(score[i][j][l + cost], score[i - 1][j][l] + grid[i][j]);

                    }
                    if (score[i][j - 1][l] > -1) {
                        score[i][j][l + cost] = Math.max(score[i][j][l + cost], score[i][j - 1][l] + grid[i][j]);
                    }
                }
            }
        }

        int res = -1;
        for (int l = k; l >= 0; l--) {
            res = Math.max(score[m - 1][n - 1][l], res);
        }

        return res;
    }

}
