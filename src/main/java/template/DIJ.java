package template;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author gusixue
 * @description 单源最短路-Dijkstra，必须保证没有负数边权
 * @date 2024/4/13
 */
public class DIJ {

    /**
     * 朴素 Dijkstra（适用于稠密图）
     * n 为点数，时间复杂度：O（n^2）
     * 空间复杂度：O（n^2）
     */
    static class DIJDense {
        /**
         * 防止更新最短路时加法溢出
         */
        private static final int INF = Integer.MAX_VALUE / 2;

        /**
         * 邻接矩阵
         */
        private final int[][] edgesArr;

        DIJDense(int n, int[][] edges) {
            this.edgesArr = new int[n][n];
            for (int[] row : edgesArr) {
                Arrays.fill(row, INF);
            }

            for (int[] edge : edges) {
                // 双向边需要分别添加
                addEdge(edge);
            }
        }

        private void addEdge(int[] edge) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            this.edgesArr[u][v] = w;
        }

        /**
         * 找到 start 到 end 的最短路，路径不存在则返回 -1
         * 注意，如果需要找到 start 到所有点的最短路，则改成仅最后返回 dis 即可
         */
        public int shortestPath(int start, int end) {
            int n = this.edgesArr.length;
            int[] dis = new int[n];
            // 默认其他点为无穷，到自己为 0
            Arrays.fill(dis, INF);
            dis[start] = 0;

            // 确定了最短路的点不会再次计算，不从 start 开始避免漏算 end==start
            boolean[] vis = new boolean[n];

            while (true) {
                // 未出现过同时 div 最短的就是下一个最短路的点
                int minPoint = -1;
                for (int i = 0; i < n; i++) {
                    if (!vis[i] && (minPoint < 0 || dis[i] < dis[minPoint])) {
                        minPoint = i;
                    }
                }

                // 没有点直接退出
                if (minPoint == -1 || dis[minPoint] == INF) {
                    return -1;
                }

                // 找到终点直接返回
                if (minPoint == end) {
                    return dis[end];
                }

                // 标记当前节点已使用
                vis[minPoint] = true;

                // 更新 当前最短路的点 到过的其他点的最短距离
                for (int i = 0; i < n; i++) {
                    if (dis[i] > dis[minPoint] + this.edgesArr[minPoint][i]) {
                        dis[i] = dis[minPoint] + this.edgesArr[minPoint][i];
                    }
                }
            }
        }

    }


    /**
     * 堆优化 Dijkstra（适用于稀疏图）
     * m 为边数，时间复杂度：O（mlogm）
     * 空间复杂度：O（m）
     */
    static class DIJSparse {

        // 防止更新最短路时加法溢出
        private static final int INF = Integer.MAX_VALUE / 2;

        /**
         * 邻接表
         */
        private final List<int[]>[] edgesList;

        DIJSparse(int n, int[][] edges) {
            this.edgesList = new ArrayList[n];
            Arrays.setAll(this.edgesList, i -> new ArrayList<>());

            for (int[] edge : edges) {
                // 双向边需要分别添加
                addEdge(edge);
            }
        }

        private void addEdge(int[] edge) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            this.edgesList[u].add(new int[]{v, w});
        }

        /**
         * 找到 start 到 end 的最短路，路径不存在则返回 -1
         * 注意，如果需要找到 start 到所有点的最短路，则改成仅最后返回 dis 即可
         */
        public int shortestPath(int start, int end) {
            int n = this.edgesList.length;
            int[] dis = new int[n];
            // 默认其他点为无穷，到自己为 0
            Arrays.fill(dis, INF);
            dis[start] = 0;

            // 使用 dis值小顶堆，每次将未出现过的最小的 dis 弹出；v-dis
            PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            priorityQueue.add(new int[]{start, 0});

            while (!priorityQueue.isEmpty()) {
                int[] minPoints = priorityQueue.poll();

                // 找到终点直接返回
                if (minPoints[0] == end) {
                    return minPoints[1];
                }

                // minPoints[0] 之前出现过则无需更新最短路
                if (minPoints[1] > dis[minPoints[0]]) {
                    continue;
                }

                // 更新 当前最短路的点 到过的其他点的最短距离
                for (int[] nextPoints : this.edgesList[minPoints[0]]) {
                    int v = nextPoints[0];
                    int w = nextPoints[1];

                    if (dis[v] > minPoints[1] + w) {
                        dis[v] = minPoints[1] + w;
                        priorityQueue.add(new int[]{v, dis[v]});
                    }
                }

            }

            return -1;
        }

    }
}
