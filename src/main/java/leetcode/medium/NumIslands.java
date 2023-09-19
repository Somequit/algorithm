package leetcode.medium;

import javafx.util.Pair;
import utils.AlgorithmUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author gusixue
 * @description
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 * @date 2023/9/19
 */
public class NumIslands {

    public static void main(String[] args) {
        NumIslands numIslands = new NumIslands();
        while (true) {
            char[][] grid = AlgorithmUtils.systemInTwoArrayChar();

            int res = numIslands.solution(grid);
            System.out.println(res);
        }
    }

    /**
     * 基础 BFS
     */
    private int solution(char[][] grid) {
        int[][] move = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        int n = grid.length;
        int m = grid[0].length;
        int[][] vis = new int[n][m];

        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (vis[i][j] == 1) {
                    continue;
                }

                if (grid[i][j] == '1') {
                    bfs(i, j, grid, vis, move);
                    res++;
                }
                vis[i][j] = 1;
            }
        }

        return res;
    }

    private void bfs(int x, int y, char[][] grid, int[][] vis, int[][] move) {
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(x, y));

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> curPosition = queue.poll();
//            System.out.println(curPosition);

            for (int i = 0; i < move.length; i++) {
                int xx = curPosition.getKey() + move[i][0];
                int yy = curPosition.getValue() + move[i][1];
                if (checkPosition(xx, yy, grid, vis)) {
//                    System.out.println(xx + " : " + yy);
                    vis[xx][yy] = 1;
                    queue.offer(new Pair<>(xx, yy));
                }
            }
        }

    }

    private boolean checkPosition(int x, int y, char[][] grid, int[][] vis) {
        int n = grid.length;
        int m = grid[0].length;

        if (x >= 0 && x < n && y >= 0 && y < m && vis[x][y] == 0 && grid[x][y] == '1') {
            return true;
        }
        return false;
    }


}
