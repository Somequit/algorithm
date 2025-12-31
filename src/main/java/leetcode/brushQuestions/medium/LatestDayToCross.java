package leetcode.brushQuestions.medium;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author gusixue
 * @description 1970. 你能穿过矩阵的最后一天
 * @date 2025/12/31 8:05 上午
 */
public class LatestDayToCross {
    public int latestDayToCross(int row, int col, int[][] cells) {
        int left = 0;
        int right = cells.length;
        int res = 0;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (doMidDayToCross(cells, mid, row, col)) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return res;
    }

    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private boolean doMidDayToCross(int[][] cells, int mid, int row, int col) {
        boolean[][] grid = new boolean[row][col];
        boolean[][] vis = new boolean[row][col];
        for (int i = 0; i < mid; i++) {
            grid[cells[i][0] - 1][cells[i][1] - 1] = true;
        }

        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        for (int j = 0; j < col; j++) {
            if (!grid[0][j]) {
                vis[0][j] = true;
                queue.add(new Pair<>(0, j));
            }
        }

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> curPair = queue.poll();
            int x = curPair.getKey();
            int y = curPair.getValue();

            if (x == row - 1) {
                return true;
            }

            for (int i = 0; i < DIRS.length; i++) {
                int xx = x + DIRS[i][0];
                int yy = y + DIRS[i][1];

                if (xx >= 0 && xx < row && yy >= 0 && yy < col && !vis[xx][yy] && !grid[xx][yy]) {
                    vis[xx][yy] = true;
                    queue.offer(new Pair<>(xx, yy));
                }
            }
        }

        return false;
    }
}
