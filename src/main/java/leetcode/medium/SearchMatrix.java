package leetcode.medium;

import utils.AlgorithmUtils;
import utils.TreeNode;

/**
 * @author gusixue
 * @description
 * 240. 搜索二维矩阵 II
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -10 ^ 9 <= matrix[i][j] <= 10 ^ 9
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -10 ^ 9 <= target <= 10 ^ 9
 * @date 2023/9/22
 */
public class SearchMatrix {

    public static void main(String[] args) {
        SearchMatrix searchMatrix = new SearchMatrix();
        while (true) {
            int[][] matrix = AlgorithmUtils.systemInTwoArray();
            int target = AlgorithmUtils.systemInNumberInt();
            boolean res = searchMatrix.solution(matrix, target);
            System.out.println(res);

        }
    }

    /**
     * 从右上角开始二分搜索，
     * 首先从上到下搜索大于 target 的最小值，此时上面同列一定小于 target、同时左上就更小于 target 了（升序）
     * 然后从左到右搜索小于 target 的最大值，此时右边一行大于 target、同时右下就更大于 target 了（升序）
     * 其实就是每次搜索后左下区间才可能出现 target，过程中出现等于 target 的值就是结果，如果到达左下角或任何一次二分找不到答案则没有 target
     * 额外时间复杂度：O（n+m）每次至少移动一格，空间复杂度：O（1）
     */
    private boolean solution(int[][] matrix, int target) {
        // 判空
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
            return false;
        }


        boolean res = false;
        // 从右上角开始二分搜索
        int n = matrix.length;
        int m = matrix[0].length;

        // 特殊判断右上角
        if (matrix[0][m-1] == target) {
            return true;
        }

        for (int i = 0, j = m - 1; !(i == n - 1 && j == 0); ) {
            // 从上到下搜索大于 target 的最小值
            i = binarySearchGreater(i, n - 1, j, matrix, target);
            if (i == -1) {
                break;
            }
            if (target == matrix[i][j]) {
                res = true;
                break;
            }

            // 从左到右搜索小于 target 的最大值
            j = binarySearchLess(i, 0, j, matrix, target);
            if (j == -1) {
                break;
            }
            if (target == matrix[i][j]) {
                res = true;
                break;
            }
//            System.out.println(i +" : " + j);
        }

        return res;
    }

    /**
     * 从左到右搜索小于 target 的最大值
     */
    private int binarySearchLess(int i, int leftJ, int rightJ, int[][] matrix, int target) {
        int res = -1;

        while (leftJ <= rightJ) {
            int midJ = ((rightJ - leftJ + 1) >> 1) + leftJ;

            if (matrix[i][midJ] > target) {
                rightJ = midJ - 1;

            } else if (matrix[i][midJ] < target) {
                leftJ = midJ + 1;
                res = midJ;

            } else {
                res = midJ;
                break;
            }
        }

        return res;
    }

    /**
     * 从上到下搜索大于 target 的最小值
     */
    private int binarySearchGreater(int leftI, int rightI, int j, int[][] matrix, int target) {
        int res = -1;

        while (leftI <= rightI) {
            int midI = ((rightI - leftI) >> 1) + leftI;

            if (matrix[midI][j] > target) {
                rightI = midI - 1;
                res = midI;

            } else if (matrix[midI][j] < target) {
                leftI = midI + 1;

            } else {
                res = midI;
                break;
            }
        }

        return res;
    }
}
