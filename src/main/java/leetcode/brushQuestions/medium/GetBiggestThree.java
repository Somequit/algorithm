package leetcode.brushQuestions.medium;

import java.util.*;

/**
 * @author gusixue
 * @description 1878. 矩阵中最大的三个菱形和
 * @date 2026/3/16 11:43 下午
 */
public class GetBiggestThree {

    // 斜向前缀和
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // 斜杠之和，grid(i,j)->prefixLeftTotal(i+1,j) 添加最上面一行为0，添加最右一列为0
        int[][] prefixLeftTotal = new int[m+1][n+1];
        // 反斜杠之和，grid(i,j)->prefixRightTotal(i+1,j+1)，添加最上面一行为0，添加最左一列为0
        int[][] prefixRightTotal = new int[m+1][n+1];
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                prefixLeftTotal[i+1][j] = prefixLeftTotal[i][j+1] + grid[i][j];
                prefixRightTotal[i+1][j+1] = prefixRightTotal[i][j] + grid[i][j];
            }
        }

        TreeSet<Integer> setRhombusSum = new TreeSet<>();
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                setRhombusSum.add(grid[i][j]);

                int maxL = Math.min(j, Math.min(n - j - 1, (m - i - 1) / 2));
                for (int l=1;l<=maxL;l++) {
                    int total = (prefixLeftTotal[i+l+1][j-l] - prefixLeftTotal[i+1][j]) + (prefixLeftTotal[i+2*l+1][j] - prefixLeftTotal[i+l+1][j+l])
                            + (prefixRightTotal[i+l+1][j+l+1] - prefixRightTotal[i+1][j+1]) + (prefixRightTotal[i+2*l+1][j+1] - prefixRightTotal[i+l+1][j-l+1])
                            - grid[i + 2 * l][j] + grid[i][j];
                    System.out.println(i + " : " + j + " : " + total);
                    setRhombusSum.add(total);
                }
            }
        }

        int[] res = new int[Math.min(3, setRhombusSum.size())];
        for (int i=0;i<res.length;i++) {
            res[i] = setRhombusSum.pollLast();
        }

        return res;
    }

}
