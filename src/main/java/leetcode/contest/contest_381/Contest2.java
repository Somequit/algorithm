package leetcode.contest.contest_381;

import utils.AlgorithmUtils;

import java.util.*;
/**
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
            int n = AlgorithmUtils.systemInNumberInt();
            int x = AlgorithmUtils.systemInNumberInt();
            int y = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

            int[] res = contest.countOfPairs(n, x, y);
            System.out.println(Arrays.toString(res));
        }

    }

    /**
     * @return
     */
    public int[] countOfPairs(int n, int x, int y) {
        int inf = n + 1;
        // 建图
        int[][][] graph = new int[n + 1][n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(graph[0][i], inf);
        }

        for (int i = 1; i < n; i++) {
            graph[0][i][i + 1] = 1;
            graph[0][i + 1][i] = 1;
            graph[0][i][i] = 0;
        }
        graph[0][n][n] = 0;

        if (x != y) {
            graph[0][x][y] = 1;
            graph[0][y][x] = 1;
        }

        // Floyd 算法
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    graph[k][i][j] = Math.min(graph[k - 1][i][j], graph[k - 1][i][k] + graph[k - 1][k][j]);
                }
            }
        }

        int[] res = new int[n];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    res[graph[n][i][j] - 1]++;
                }
            }
        }

        return res;
    }


}
