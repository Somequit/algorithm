package leetcode.contest.double_128;

import java.util.*;
/**
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

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
    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
//        int inf = (int) 1e5 + 1;
//        Map<String, Integer> map = new HashMap<>();
//        for (int i = 0; i < edges.length; i++) {
//            int u = edges[i][0];
//            int v = edges[i][1];
//            int w = edges[i][2];
//            if (u == v) {
//                continue;
//            }
//
//            String uv = Math.min(u, v) + "_" + Math.max(u, v);
//            map.put(uv, Math.min(map.getOrDefault(uv, inf), w));
//        }
//        int[][] edges2 = new int[map.size()][3];
//        int m = 0;
//        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            edges2[m][0] = Integer.parseInt(entry.getKey().split("_")[0]);
//            edges2[m][1] = Integer.parseInt(entry.getKey().split("_")[1]);
//            edges2[m][2] = entry.getValue();
//            m++;
//        }

//        System.out.println(m);
//        Arrays.stream(edges2).forEach(arr -> System.out.println(Arrays.toString(arr)));

        Graph graph = new Graph(n, edges);
        return graph.shortestPath(0, disappear);
    }

    static class Graph {
        private final List<int[]>[] g; // 邻接表

        Graph(int n, int[][] edges) {
            g = new ArrayList[n];
            Arrays.setAll(g, i -> new ArrayList<>());
            for (int[] e : edges) {
                addEdge(e);
            }
        }

        private void addEdge(int[] e) {
            g[e[0]].add(new int[]{e[1], e[2]});
            g[e[1]].add(new int[]{e[0], e[2]});
        }

        public int[] shortestPath(int start, int[] disappear) {
            // dis[i] 表示从起点 start 出发，到节点 i 的最短路长度
            int[] dis = new int[g.length];
            Arrays.fill(dis, Integer.MAX_VALUE);
            dis[start] = 0;
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
            pq.offer(new int[]{0, start});

            while (!pq.isEmpty()) {
                int[] p = pq.poll();
                int d = p[0];
                int x = p[1];
                if (d > dis[x]) { // x 之前出堆过，无需更新邻居的最短路
                    continue;
                }
                for (int[] e : g[x]) {
                    int y = e[0];
                    int w = e[1];
                    if (d + w < dis[y] && d + w < disappear[y]) {
                        dis[y] = d + w; // 更新最短路长度
                        pq.offer(new int[]{dis[y], y});
                    }
                }
            }

            for (int i = 0; i < g.length; i++) {
                if (dis[i] >= disappear[i]) {
                    dis[i] = -1;
                }
            }

            return dis;
        }
    }


}
