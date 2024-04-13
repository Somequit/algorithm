package leetcode.brushQuestions.medium;

import java.util.*;

/**
 * @author gusixue
 * @description 743. 网络延迟时间
 * @date 2024/4/14
 */
public class NetworkDelayTime {

    /**
     * 直接使用 DIJ，max(dis)=INF 返回 -1，否则返回 max(dis)
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        DIJSparse dijSparse = new DIJSparse(n, times);

        int[] dis = dijSparse.shortestPath(k - 1);
        int INF = Integer.MAX_VALUE / 2;
        int res = 0;
        for (int disNum : dis) {
            res = Math.max(res, disNum);
        }

        return res == INF ? -1 : res;
    }

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
            int u = edge[0] - 1;
            int v = edge[1] - 1;
            int w = edge[2];
            this.edgesList[u].add(new int[]{v, w});
        }

        /**
         * 找到 start 到 end 的最短路，路径不存在则返回 -1
         * 注意，如果需要找到 start 到所有点的最短路，则改成仅最后返回 dis 即可
         */
        public int[] shortestPath(int start) {
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

            return dis;
        }

    }
}
