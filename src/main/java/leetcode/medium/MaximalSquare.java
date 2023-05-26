package leetcode.medium;

import utils.AlgorithmUtils;

import java.util.Arrays;

/**
 * @author gusixue
 * @description
 * 221. 最大正方形
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] 为 '0' 或 '1'
 * @date 2023/5/26
 */
public class MaximalSquare {

    public static void main(String[] args) {
        MaximalSquare maximalSquare = new MaximalSquare();
        while (true) {
            char[][] matrix = AlgorithmUtils.systemInTwoArrayChar();
            int res = maximalSquare.solution(matrix);
            System.out.println(res);
        }
    }

    /**
     * 动态规划+状态压缩：定义三个 dp，分别为：
     *     rowDp[i][j]：在第 i 行当前节点（含）往前连续有多少个 1
     *     colDp[i][j]：在第 j 列当前节点（含）往上连续有多少个 1
     *     squareDp[i][j]：以当前节点为右下角，连续 1 的最大正方形边长
     * 此时循环矩阵，如果为 0 则全是 0，如果为 1 转移方程为：
     *     rowDp[i][j] = rowDp[i][j - 1] + 1
     *     colDp[i][j] = colDp[i - 1][j] + 1，
     *     squareDp[i][j] = min(rowDp[i][j], rowDp[i][j], squareDp[i - 1][j - 1] + 1) 意为左上的正方形加上当前行、当前列可以形成的最大正方形
     * 空间压缩：rowDp 压缩后每个值等于前一个值加一（矩阵为 1），colDp 压缩后每个值等于上一轮循环当前位加一（矩阵为 1），
     * squareDp 必须使用二维数组，因为每次遍历时会将上一行的前一位数据更新掉，
     * 同时 squareDp[i][j] = min(rowDp[i][j]（当前行列）, rowDp[i][j]（当前行列）, squareDp[i - 1][j - 1] + 1（上一行、上一列）)
     * 时间复杂度：O（m*n），空间复杂度：O（n）
     */
    public int solution(char[][] matrix) {
        // 判空
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        // 定义三个 dp，空间压缩，避免判断下标 0 可以让 dp 往后/下移动一位
        int[] rowDp = new int[n + 1];
        int[] colDp = new int[n + 1];
        // 注意必须使用二维数组，因为顺序遍历时会将上一行的前一位数据更新
        int[][] squareDp = new int[2][n + 1];
        // 循环处理三个 dp
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    rowDp[j + 1] = 0;
                    colDp[j + 1] = 0;
                    squareDp[i & 1][j + 1] = 0;
                } else {
                    rowDp[j + 1] = rowDp[j] + 1;
                    colDp[j + 1] = colDp[j + 1] + 1;
                    // squareDp[i][j] = min(rowDp[i][j]（当前行列）, rowDp[i][j]（当前行列）, squareDp[i - 1][j - 1] + 1（上一行、上一列）)
                    squareDp[i & 1][j + 1] = Math.min(rowDp[j + 1], Math.min(colDp[j + 1], squareDp[(i + 1) & 1][j] + 1));
                    res = Math.max(res, squareDp[i & 1][j + 1]);
                }
//                System.out.print(matrix[i][j] + " : " + rowDp[j + 1] + " : " + colDp[j + 1] + " : " + squareDp[i & 1][j + 1] + "\t");
            }
//            System.out.println();
        }
        return res * res;
    }
}
