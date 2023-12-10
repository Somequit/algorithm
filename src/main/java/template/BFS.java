package template;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author gusixue
 * @description 广度优先搜索模板
 * @date 2023/12/11
 */
public class BFS {

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
