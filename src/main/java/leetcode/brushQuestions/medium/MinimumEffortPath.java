package leetcode.brushQuestions.medium;

import javafx.util.Pair;

import java.util.*;

/**
 * @author gusixue
 * @description 1631. 最小体力消耗路径
 * @date 2023/12/11
 */
public class MinimumEffortPath {

    /**
     * 二分答案 + BFS：
     */
    public int minimumEffortPath(int[][] heights) {
        int left = 0;
        int right = (int)1e6;
        int res = 0;

        while (left <= right) {
            int mid = ((right - left) >> 1) + left;

            if (checkMinimumEffortPath(heights, mid)) {
                res = mid;
                right = mid - 1;

            } else {
                left = mid + 1;
            }

        }

        return res;

    }

    /**
     * BFS：(0,0) 到 (row-1, col-1) ，能够上下左右移动，相邻距离差不大于 maxHeight
     */
    private boolean checkMinimumEffortPath(int[][] heights, int maxHeight) {
        int row = heights.length;
        int col = heights[0].length;
        int[][] vis = new int[row][col];

        return bfs(0, 0, row - 1, col - 1, heights, maxHeight, vis);
    }

    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    /**
     * BFS：(0,0) 到 (row-1, col-1) ，能够上下左右移动，相邻距离差不大于 maxHeight
     */
    private boolean bfs(int beginX, int beginY, int endX, int endY, int[][] heights, int maxHeight, int[][] vis) {
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(beginX, beginY));

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

                if (checkPosition(x, y, xx, yy, heights, maxHeight, vis)) {
                    vis[xx][yy] = 1;
                    queue.offer(new Pair<>(xx, yy));
                }
            }
        }

        return false;
    }

    private boolean checkPosition(int x, int y, int xx, int yy, int[][] heights, int maxHeight, int[][] vis) {
        int row = heights.length;
        int col = heights[0].length;

        if (xx >= 0 && xx < row && yy >= 0 && yy < col && vis[xx][yy] == 0
                && Math.abs(heights[xx][yy] - heights[x][y]) <= maxHeight) {
            return true;
        }
        return false;
    }
}
