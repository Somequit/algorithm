package leetcode.brushQuestions.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gusixue
 * @description
 * 2646. 最小化旅行的价格总和
 * @date 2023/12/6
 */
public class MinimumTotalPrice {

    /**
     * DFS + 树上DP：
     *
     * 第 1 步：
     * 求出每个点使用次数 * 价格
     *
     *
     * 第 2 步：
     * 树上 dp[i][0]- i不折半，dp[i][1] - i折半
     *
     *
     */
    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        // 建树
        List<Integer>[] treeList = buildTree(n, edges);

        // 每个点使用次数 * 价格
        int[] pointPrice = new int[n];

        // 枚举 trips 计算每次 start 到 end 的最短路径（不走回头路）
        List<Integer> pointList = new ArrayList<>();
        for (int[] trip : trips) {
            int start = trip[0];
            int end = trip[1];

            pointList.clear();
            pointList.add(-1);
            dfsShortestPath(start, -1, treeList, end, pointList);

            // 求出每个点使用次数 * 价格
            for (int i = 1; i < pointList.size(); i++) {
                int point = pointList.get(i);
                pointPrice[point] += price[point];
            }
        }

        // 树上 dp[i][0]- i不折半，dp[i][1] - i折半
        int[][] dp = new int[n][2];

        dfsMinPrice(0, -1, treeList, pointPrice, dp);


        return Math.min(dp[0][0], dp[0][1]);

    }

    /**
     * 树上 dp[i][0]- i不折半，dp[i][1] - i折半
     */
    private void dfsMinPrice(int son, int father, List<Integer>[] treeList, int[] pointPrice, int[][] dp) {

        int nextNot = 0;
        int nextMin = 0;
        for (int next : treeList[son]) {
            if (next != father) {

                dfsMinPrice(next, son, treeList, pointPrice, dp);
                nextNot += dp[next][0];
                nextMin += Math.min(dp[next][0], dp[next][1]);
            }
        }

        dp[son][0] = nextMin + pointPrice[son];
        dp[son][1] = nextNot + (pointPrice[son] >> 1);

    }

    /**
     * 计算每次 start 到 end 的最短路径（不走回头路）
     * 返回路径上所有的点
     */
    private void dfsShortestPath(int son, int father, List<Integer>[] treeList, int end, List<Integer> pointList) {
        if (son == end) {
            pointList.add(son);
            return;
        }

        for (int next : treeList[son]) {
            if (next != father) {
                pointList.add(son);

                dfsShortestPath(next, son, treeList, end, pointList);

                // 遍历到 end 节点，不用清理只直接退出
                if (pointList.get(pointList.size() - 1) == end) {
                    return;
                }

                pointList.remove(pointList.size() - 1);
            }
        }

    }

    /**
     * 建树
     */
    private List<Integer>[] buildTree(int n, int[][] edges) {
        List<Integer>[] edgeList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edgeList[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            edgeList[u].add(v);
            edgeList[v].add(u);
        }
        return edgeList;
    }

}
