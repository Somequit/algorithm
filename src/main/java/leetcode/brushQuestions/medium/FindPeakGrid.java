package leetcode.brushQuestions.medium;

/**
 * @author gusixue
 * @description 1901. 寻找峰值 II
 * @date 2023/12/19
 */
public class FindPeakGrid {

    /**
     * Java + 列最大值 + 二分：
     *
     * 第 1 步：
     * 类似：162. 寻找峰值 FindPeakElement，在行内找严格大于左右的元素，再找每列的最大值（一定是大于上下）
     * 一定需要找该列的最大值，如果这也二分找极大值（仅严格大于左右），那么可能找到非该列最大值从而导致 左/右 列误判
     *
     * 第 2 步：
     * 具体做法：
     *     * 先找中间=mid 列，找到俩最大值 mat[maxRow][mid] ，元素一定严格大于上下的元素
     *     * 如果 mat[maxRow][mid] 严格大于左右的元素，则直接返回，否则下一步
     *     * 如果 mat[maxRow][mid] > mat[maxRow][mid+1] 则 maxRow 左边列一定存在，否则 maxRow 右边列一定存在
     * 时间复杂度：O（m*logn），空间复杂度：O（1）
     *
     *
     */
    public int[] findPeakGrid(int[][] mat) {
        int leftCol = 0;
        int rightCol = mat[0].length - 1;
        int resCol = 0;
        while (leftCol <= rightCol) {
            int midCol = ((rightCol - leftCol) >> 1) + leftCol;

            int maxRow = getMaxRow(mat, midCol);
            if ((midCol == 0 || mat[maxRow][midCol] > mat[maxRow][midCol - 1])
                    && (midCol == mat[0].length - 1 || mat[maxRow][midCol] > mat[maxRow][midCol + 1])) {
                resCol = midCol;
                break;
            }

            if (midCol == mat[0].length - 1 || mat[maxRow][midCol] > mat[maxRow][midCol + 1]) {
                rightCol = midCol - 1;

            } else {
                leftCol = midCol + 1;
            }

        }

        return new int[]{getMaxRow(mat, resCol), resCol};
    }

    private int getMaxRow(int[][] mat, int resCol) {
        int maxRow = 0;

        for (int i = 0; i < mat.length; i++) {
            if (mat[maxRow][resCol] < mat[i][resCol]) {
                maxRow = i;
            }
        }

        return maxRow;
    }
}
