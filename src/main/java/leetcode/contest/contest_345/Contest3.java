package leetcode.contest.contest_345;

import utils.AlgorithmUtils;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;


/**
 * @author gusixue
 * @date 2023/5/14
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
            int[][] grid = AlgorithmUtils.systemInTwoArray();
            AlgorithmUtils.systemOutArray(grid);

            int res = contest.maxMoves(grid);
            System.out.println(res);
        }

    }

    private int maxMoves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[] mark = new boolean[m];

        int res = 0;
        Queue<Integer> queue = new ArrayDeque<>(2 * m);
        for (int i = 0; i < m; i++) {
            queue.add(getGridPoint(i, 0));
        }

        int[] moveX = new int[]{-1, 0, 1};
        int[] moveY = new int[]{1, 1, 1};
        while (!queue.isEmpty()) {
            Integer nowPoint = queue.poll();
            int x = getGridPointX(nowPoint);
            int y = getGridPointY(nowPoint);
            if (y > res) {
                res = y;
                Arrays.fill(mark, false);
            }

            for (int i = 0; i < 3; i++) {
                if (checkGridPoint(x, y, x + moveX[i], y + moveY[i], grid, mark)) {
                    queue.add(getGridPoint(x + moveX[i], y + moveY[i]));
                }
            }
        }

        return res;
    }

    private boolean checkGridPoint(int x, int y, int moveX, int moveY, int[][] grid, boolean[] mark) {
        int m = grid.length;
        int n = grid[0].length;

        if (moveX >= 0 && moveX < m && moveY >= 0 && moveY < n && grid[moveX][moveY] > grid[x][y] && !mark[moveX]) {
            mark[moveX] = true;
            return true;
        }

        return false;
    }


    private Integer getGridPoint(int x, int y) {
        return x * 100000 + y;
    }

    private int getGridPointX(Integer nowGridPoint) {
        return nowGridPoint / 100000;
    }

    private int getGridPointY(Integer nowGridPoint) {
        return nowGridPoint % 100000;
    }


}
