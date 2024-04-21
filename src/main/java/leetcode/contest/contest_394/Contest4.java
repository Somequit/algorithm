package leetcode.contest.contest_394;

import java.util.*;
/**
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

//            int res = contest.solution(n, nums, list);
//            System.out.println(res);
        }

    }

    /**
     * @return
     */
    public boolean[] findAnswer(int n, int[][] edges) {
        DIJSparse dij = new DIJSparse(n, edges);

        long[] dis = dij.shortestPath(0);

        int m = edges.length;
        boolean[] vis = new boolean[n];
        vis[n - 1] = true;
        boolean[] res = new boolean[m];

        dfsEdges(n - 1, dis, dij.getEdgesList(), vis, res);

        return res;
    }

    private void dfsEdges(int curNode, long[] dis, List<int[]>[] edgesList, boolean[] vis, boolean[] res) {
        List<int[]> nextList = edgesList[curNode];

        for (int[] nextArr : nextList) {
            if (dis[curNode] - dis[nextArr[0]] == nextArr[1]) {
                res[nextArr[2]] = true;

                if (!vis[nextArr[0]]) {
                    vis[nextArr[0]] = true;
                    dfsEdges(nextArr[0], dis, edgesList, vis, res);
                }
            }
        }

    }

    class DIJSparse {

        // 防止更新最短路时加法溢出
        private static final long INF = Long.MAX_VALUE / 2;

        /**
         * 邻接表
         */
        private final List<int[]>[] edgesList;

        DIJSparse(int n, int[][] edges) {
            this.edgesList = new ArrayList[n];
            Arrays.setAll(this.edgesList, i -> new ArrayList<>());

            int index = 0;
            for (int[] edge : edges) {
                // 双向边需要分别添加
                addEdge(edge, index);

                index++;
            }
        }

        private void addEdge(int[] edge, int index) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            this.edgesList[u].add(new int[]{v, w, index});
            this.edgesList[v].add(new int[]{u, w, index});
        }

        public List<int[]>[] getEdgesList(){
            return this.edgesList;
        }

        /**
         * 找到 start 到 end 的最短路，路径不存在则返回 -1
         * 注意，如果需要找到 start 到所有点的最短路，则改成仅最后返回 dis 即可
         */
        public long[] shortestPath(int start) {
            int n = this.edgesList.length;
            long[] dis = new long[n];
            // 默认其他点为无穷，到自己为 0
            Arrays.fill(dis, INF);
            dis[start] = 0;

            // 使用 dis值小顶堆，每次将未出现过的最小的 dis 弹出；v-dis
            PriorityQueue<long[]> priorityQueue = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
            priorityQueue.add(new long[]{start, 0});

            while (!priorityQueue.isEmpty()) {
                long[] minPoints = priorityQueue.poll();

                // minPoints[0] 之前出现过则无需更新最短路
                if (minPoints[1] > dis[(int)minPoints[0]]) {
                    continue;
                }

                // 更新 当前最短路的点 到过的其他点的最短距离
                for (int[] nextPoints : this.edgesList[(int)minPoints[0]]) {
                    int v = nextPoints[0];
                    int w = nextPoints[1];

                    if (dis[v] > minPoints[1] + w) {
                        dis[v] = minPoints[1] + w;
                        priorityQueue.add(new long[]{v, dis[v]});
                    }
                }

            }

            return dis;
        }

    }


}
