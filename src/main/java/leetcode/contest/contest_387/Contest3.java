package leetcode.contest.contest_387;

import java.util.*;
/**
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

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
    public int minimumOperationsToWriteY(int[][] grid) {
        int[] countY = new int[3];
        int[] countNotY = new int[3];

        int n = grid.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i == j && i <= n / 2) || (i + j == n - 1 && i <= n / 2) || (j == n / 2 && i >= n / 2)) {
                    countY[grid[i][j]]++;

                } else {
                    countNotY[grid[i][j]]++;
                }
            }
        }

        int res = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != j) {
                    res = Math.max(res, countY[i] + countNotY[j]);
                }
            }
        }

        return n * n - res;
    }


}
