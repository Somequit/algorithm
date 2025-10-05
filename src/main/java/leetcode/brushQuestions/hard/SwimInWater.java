package leetcode.brushQuestions.hard;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author gusixue
 * @description 778. 水位上升的泳池中游泳
 * @date 2025/10/6 2:03 上午
 */
public class SwimInWater {

    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * 二分答案+BFS
     */
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        // 首先水位必须淹没起始俩点
        int left = Math.max(grid[0][0], grid[n - 1][n - 1]);
        int right = n * n - 1;

        int[][] vis = new int[n][n];
        int res = left;
        while (left <= right) {
            int middle = (right - left) / 2 + left;

            // 如果可以游到，则 middle >= 答案
            Arrays.stream(vis).forEach(arr -> Arrays.fill(arr, 0));
            if (isSwimToEnd(0, 0, n - 1, n - 1, grid, middle, vis)) {
                res = middle;
                right = middle - 1;

            } else {
                left = middle + 1;
            }
        }

        return res;
    }

    /**
     * BFS：(0,0) 到 (row-1, col-1) ，能够上下左右移动，相邻距离差不大于 maxHeight
     */
    private boolean isSwimToEnd(int beginX, int beginY, int endX, int endY, int[][] heights, int maxHeight, int[][] vis) {
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(beginX, beginY));
        vis[beginX][beginY] = 1;

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> curPair = queue.poll();
            int x = curPair.getKey();
            int y = curPair.getValue();

            if (x == endX && y == endY) {
                return true;
            }

            for (int i = 0; i < DIRS.length; i++) {
                int xx = x + DIRS[i][0];
                int yy = y + DIRS[i][1];

                if (checkPosition(xx, yy, heights, maxHeight, vis)) {
                    vis[xx][yy] = 1;
                    queue.offer(new Pair<>(xx, yy));
                }
            }
        }

        return false;
    }

    private boolean checkPosition(int xx, int yy, int[][] heights, int maxHeight, int[][] vis) {
        int row = heights.length;
        int col = heights[0].length;

        if (xx >= 0 && xx < row && yy >= 0 && yy < col && vis[xx][yy] == 0 && heights[xx][yy] <= maxHeight) {
            return true;
        }
        return false;
    }
}
