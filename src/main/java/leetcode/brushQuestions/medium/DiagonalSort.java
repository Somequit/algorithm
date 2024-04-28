package leetcode.brushQuestions.medium;

import java.util.*;

/**
 * @author gusixue
 * @description 1329. 将矩阵按对角线排序
 * @date 2024/4/29
 */
public class DiagonalSort {

    /**
     * （0，0）-（0，n-1） + (1，0)-（m-1，0） 为开头找到结尾，塞入 List 排序后，从小到大按照斜线更新即可
     * @param mat
     * @return
     */
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        // val-x-y
        List<Integer> listDiagonal = new ArrayList<>();

        // （0，0）-（0，n-1） +
        for (int j = 0; j < n; j++) {
            doDiagonalSort(0, j, m, n, listDiagonal, mat);
        }

        // (1，0)-（m-1，0）
        for (int i = 1; i < m; i++) {
            doDiagonalSort(i, 0, m, n, listDiagonal, mat);
        }

        return mat;
    }

    private void doDiagonalSort(int i, int j, int m, int n, List<Integer> listDiagonal, int[][] mat) {
        int x = i;
        int y = j;
        while (x < m && y < n) {
            listDiagonal.add(mat[x][y]);
            x++;
            y++;
        }

        Collections.sort(listDiagonal);

        x = i;
        y = j;
        for (int val : listDiagonal) {
            mat[x][y] = val;
            x++;
            y++;
        }

        listDiagonal.clear();
    }
}
