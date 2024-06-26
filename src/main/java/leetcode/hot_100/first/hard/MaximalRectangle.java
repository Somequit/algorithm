package leetcode.hot_100.first.hard;

import utils.AlgorithmUtils;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author gusixue
 * @description
 * 85. 最大矩形
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * rows == matrix.length
 * cols == matrix[0].length
 * 1 <= row, cols <= 200
 * matrix[i][j] 为 '0' 或 '1'
 * @date 2023/9/22
 */
public class MaximalRectangle {

    public static void main(String[] args) {
        MaximalRectangle maximalRectangle = new MaximalRectangle();
        while (true) {
            char[][] matrix = AlgorithmUtils.systemInTwoArrayChar();

            int res = maximalRectangle.solution(matrix);
            System.out.println(res);

            int res2 = maximalRectangle.solution2(matrix);
            System.out.println(res2);

        }
    }

    /**
     * 每列顺序遍历矩阵每个点，如果是 0 则直接进下一个点，如果是 1 则记录到当前列中连续 1 的个数，
     * 接着每行顺序遍历，每个点求出左边与右边第一个比其小的下标（或者 -1 与 m），左右之间的距离乘以当前 1 的个数，就是以当前为高的最大矩形的面积
     * 时间复杂度：O（n*m），空间复杂度：O（n*m）
     * @param matrix
     * @return
     */
    private int solution2(char[][] matrix) {
        // 判空
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
            return 0;
        }

        int n = matrix.length;
        int m = matrix[0].length;
        int[][] continuousOne = new int[n][m];
        // 每列顺序遍历矩阵每个点，如果是 0 则直接进下一个点，如果是 1 则记录到当前列中连续 1 的个数，
        for (int j = 0; j < m; j++) {
            int continuous = 0;
            for (int i = 0; i < n; i++) {
                if (matrix[i][j] == '0') {
                    continuous = 0;

                } else {
                    continuous++;
                    continuousOne[i][j] = continuous;
                }
            }
        }
//        AlgorithmUtils.systemOutArray(continuousOne);

        int res = 0;
        int[] left = new int[m];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {

            // 递增单调栈求出左边第一个比其小的下标或 -1
            for (int j = 0; j < m; j++) {
                while (!stack.isEmpty() && continuousOne[i][j] <= continuousOne[i][stack.getFirst()]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    left[j] = stack.getFirst();

                } else {
                    left[j] = -1;
                }

                stack.push(j);
            }
//            System.out.println(Arrays.toString(left));
            stack.clear();

            // 递增单调栈求出右边第一个比其小的下标或 m，左右之间的距离乘以当前 1 的个数，就是以当前为高的最大矩形的面积
            for (int j = m - 1; j >= 0; j--) {
                while (!stack.isEmpty() && continuousOne[i][j] <= continuousOne[i][stack.getFirst()]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    res = Math.max(res, continuousOne[i][j] * (stack.getFirst() - left[j] - 1));
//                    System.out.print(stack.getFirst() + " ");

                } else {
                    res = Math.max(res, continuousOne[i][j] * (m - left[j] - 1));
//                    System.out.print(m + " ");
                }

                stack.push(j);
            }
//            System.out.println();
            stack.clear();
        }

        return res;
    }

    /**
     * 顺序遍历矩阵每个点，如果是 0 则直接进下一个点，
     * 如果是 1 则记录当前行中连续 1 的个数，接着在从此点开始往上计算、能够凑成的最大矩阵并更新答案，矩阵面积=经过行数 * 此列这些行的最小连续 1 个数
     * 优化：如果最大矩阵面积 >= 该行数*当前最小连续 1 个数，则不再往上计算
     * 时间复杂度：O（n*m*n），空间复杂度：O（n*m）
     */
    private int solution(char[][] matrix) {
        // 判空
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
            return 0;
        }

        int res = 0;
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] continuousOneMatrix = new int[n][m];
        // 顺序遍历矩阵
        for (int i = 0; i < n; i++) {

            int continuousOneCount = 0;
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '0') {
                    continuousOneCount = 0;
                    continue;
                }

                continuousOneCount++;
                continuousOneMatrix[i][j] = continuousOneCount;

                int minOneCount = continuousOneCount;
                for (int k = i; k >= 0; k--) {
                    minOneCount = Math.min(minOneCount, continuousOneMatrix[k][j]);
                    res = Math.max(res, minOneCount * (i - k + 1));

                    // 优化：如果最大矩阵面积 >= 该行数*当前最小连续 1 个数，则不再往上计算
                    if (res >= (i + 1) * minOneCount) {
                        break;
                    }
                }

            }
        }

//        AlgorithmUtils.systemOutArray(continuousOneMatrix);

        return res;
    }
}
