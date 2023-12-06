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
     * 暴力 DFS + 树形 DP：
     *
     * 第 1 步：
     * 思考在不折半的情况下，每次旅行的最小值：start 到 end 的最短路径（不走回头路）
     *
     * 第 2 步：
     * 先建树，然后枚举 trips 计算每次 start 到 end 的最短路径
     * 找到该路径所有点，求出每个点使用 次数 * 价格
     * 此时问题转化为：每个点的价格固定（次数 * 价格），求 非相邻节点 后、所有节点总和的最小值
     *
     * 第 3 步：
     * 类似：337. 打家劫舍 III
     * 树形 DP
     * dp[i][j] 代表 i 子树处、所有旅行的最小价格总和，j=0-不折半，j=1-i折半
     * 动规转移方程：
     *     * dp[i][0] = min(dp[child][0]，dp[child][1]) + pointPrice[i]，i 不折半则 i 的孩子可以折半也可以不折半，
     *     * dp[i][1] = dp[child][0] + pointPrice[i]/2，i 折半则 i 的孩子一定不折半，
     * 时间复杂度：O（n * m），空间复杂度：O（n）
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

        // dp[i][j] 代表 i 子树处、所有旅行的最小价格总和，j=0-不折半，j=1-i折半
        int[][] dp = new int[n][2];

        dfsMinPrice(0, -1, treeList, pointPrice, dp);


        return Math.min(dp[0][0], dp[0][1]);

    }

    /**
     * dp[i][j] 代表 i 子树处、所有旅行的最小价格总和，j=0-不折半，j=1-i折半
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
