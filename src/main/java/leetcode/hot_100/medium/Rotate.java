package leetcode.hot_100.medium;

import utils.AlgorithmUtils;

/**
 * @author gusixue
 * @description
 * 48. 旋转图像
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 * @date 2023/9/13
 */
public class Rotate {

    public static void main(String[] args) {
        while (true) {
            Rotate rotate = new Rotate();

            int[][] matrix = AlgorithmUtils.systemInTwoArray();
            rotate.solution(matrix);
            AlgorithmUtils.systemOutArray(matrix);
        }
    }

    private void solution(int[][] matrix) {
        // 判空
        if (matrix == null || matrix.length <= 0 || matrix[0] == null || matrix[0].length <= 0) {
            return;
        }

        int n = matrix.length;
        /**
         * 旋转原理：由外层到内层一圈一圈旋转，每一圈均为四个元素旋转
         * 旋转方式：行 i=[0, 2/n] 列 [i, n-1-i] 每个元素均与对应的另外三个元素替换
         *
         * 例如：
         * 00 01 02 03 04
         * 10 11 12 13 14
         * 20 21 22 23 24
         * 30 31 32 33 34
         * 40 41 42 43 44
         * 依次替换：00 04 44 40；01 14 43 30；02 24 42 20；03 34 41 10；11 13 33 31；12 23 32 21
         *
         * 例如：
         * 00 04 一 04 00 二 40 00 三 44 00
         * 44 40 次 44 40 次 44 04 次 40 04
         *
         */

        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - 1-i; j++) {
                swap(matrix, i, j, j, n - 1 - i);
                swap(matrix, i, j, n - 1 - i, n - 1 - j);
                swap(matrix, i, j, n - 1 - j, i);
//                System.out.println(i + "" + j + " : " + j + "" + (n - 1 - i) + " : " + (n - 1 - i) + "" + (n - 1 - j) + " : " + (n - 1 - j) + "" + i);
            }
        }

    }

    private void swap(int[][] matrix, int i1, int j1, int i2, int j2) {
        matrix[i1][j1] ^= matrix[i2][j2];
        matrix[i2][j2] ^= matrix[i1][j1];
        matrix[i1][j1] ^= matrix[i2][j2];
    }

}
