package leetcode.contest.double_129;

import java.util.*;
/**
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

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
    public long numberOfRightTriangles(int[][] grid) {
        long res = 0;
        int m = grid.length;
        int n = grid[0].length;

        int[] rowOneCount = new int[m];
        int[] colOneCount = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowOneCount[i] += grid[i][j];
                colOneCount[j] += grid[i][j];
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }

                res += (rowOneCount[i] - 1) * (colOneCount[j] - 1);
            }
        }

        return res;
    }


}
