package leetcode.contest.double_119;


import utils.AlgorithmUtils;

import java.util.*;

/**
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
            int n = AlgorithmUtils.systemInNumberInt();
            int maxDistance = AlgorithmUtils.systemInNumberInt();
            int[][] roads = AlgorithmUtils.systemInTwoArray();

            int res = contest.numberOfSets(n, maxDistance, roads);
            System.out.println(res);
        }

    }

    /**
     * @return
     */
    public int numberOfSets(int n, int maxDistance, int[][] roads) {
        int res = 1;
        int inf = (int)1e6+10;

        int[][] edges = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(edges[i], inf);
        }
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int w = road[2];
            edges[u][v] = Math.min(edges[u][v], w);
            edges[v][u] = Math.min(edges[v][u], w);
        }
//        AlgorithmUtils.systemOutArray(edges);

        for (int i = (1 << n) - 1; i > 0; i--) {
            List<Integer> pointList = getTotalPoint(i);
//            System.out.println(pointList);

            if (pointList.size() == 1) {
                res++;
                continue;
            }

            if (check(n, pointList, edges, maxDistance)) {
                res++;
            }

        }
        return res;
    }

    private boolean check(int n, List<Integer> pointList, int[][] edges, int maxDistance) {
        int m = pointList.size();

        int[][][] dp = new int[n + 1][n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                int u = pointList.get(i);
                int v = pointList.get(j);
                dp[0][u + 1][v + 1] = edges[u][v];
            }
        }
//        AlgorithmUtils.systemOutArray(dp[0]);

        for (int k = 1; k <= m; k++) {
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= m; j++) {
                    int u = pointList.get(i - 1);
                    int v = pointList.get(j - 1);
                    int kk = pointList.get(k - 1);
                    dp[k][u + 1][v + 1] = Math.min(dp[k - 1][u + 1][v + 1], dp[k - 1][u + 1][kk + 1] + dp[k - 1][kk + 1][v + 1]);
                }
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= m; j++) {
                int u = pointList.get(i - 1);
                int v = pointList.get(j - 1);

//                System.out.println(u + " : " + v + " : " + dp[m][u][v]);
                if (i != j &&  dp[m][u + 1][v + 1] > maxDistance) {
                    return false;
                }
            }
        }
        return true;

    }

    private List<Integer> getTotalPoint(int i) {
        List<Integer> pointList = new ArrayList<>();
        int count = 0;
        while (i > 0) {
            if (i % 2 == 1) {
                pointList.add(count);
            }
            i >>= 1;
            count++;
        }

        return pointList;
    }


}
