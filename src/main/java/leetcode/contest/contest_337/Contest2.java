package leetcode.contest.contest_337;

import utils.AlgorithmUtils;


/**
 * @author gusixue
 * @date 2023/3/19
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
            int[][] grid = AlgorithmUtils.systemInTwoArray();

            boolean res = contest.solution(grid);
            System.out.println(res);
        }

    }

    private boolean solution(int[][] grid) {
        int n = grid.length;
        int num = n * n;

        int row = 0;
        int col = 0;
        int[] moveRow = new int[]{1, 1, -1, -1, 2, 2, -2, -2};
        int[] moveCol = new int[]{2, -2, 2, -2, 1, -1, 1, -1};

        for (int i = 1; i < num; i++) {
            boolean res = false;
            for (int j = 0; j < 8; j++) {
                if (row + moveRow[j] >= 0 && row + moveRow[j] < n &&
                        col + moveCol[j] >= 0 && col + moveCol[j] < n &&
                        grid[row + moveRow[j]][col + moveCol[j]] == i) {
                    row += moveRow[j];
                    col += moveCol[j];
                    res = true;
                    break;
                }
            }
            if (!res) {
                return false;
            }
        }

        return true;
    }

}
