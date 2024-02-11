package leetcode.contest.contest_384;

import java.util.*;
/**
 */
public class Contest1 {

    public static void main(String[] args) {
        Contest1 contest = new Contest1();

        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

//            int res = contest.solution(n, nums, list);
//            System.out.println(res);
        }

    }

    /**
     * @return
     */
    public int[][] modifiedMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] res = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != -1) {
                    res[i][j] = matrix[i][j];

                } else {
                    int temp = matrix[0][j];
                    for (int k = 0; k < m; k++) {
                        temp = Math.max(temp, matrix[k][j]);
                    }
                    res[i][j] = temp;
                }
            }
        }

        return res;
    }


}
