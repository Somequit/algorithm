package leetcode.brushQuestions.medium;

import java.util.*;

/**
 * @author gusixue
 * @description 3650. 边反转的最小路径总成本
 * @date 2026/1/27 9:51 下午
 */
public class MinCost {
    // Dijkstra 模板题
    public int minCost(int n, int[][] edges) {
        List<int[]>[] listGraph = new ArrayList[n];
        createGraph(n, edges, listGraph);

        return dijkstra(n, listGraph, 0, n-1);
    }

    private int dijkstra(int n, List<int[]>[] listGraph, int start, int stop) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<int[]> heapDistPointMin = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[0]));
        heapDistPointMin.offer(new int[]{start, 0});
        while (!heapDistPointMin.isEmpty()) {
            int[] curP = heapDistPointMin.poll();
            int distU = curP[0];
            int curU = curP[1];

            // curV 出过堆
            if (distU > dist[curU]) {
                continue;
            }

            if (curU == stop) {
                return distU;
            }

            for (int[] arrGraph : listGraph[curU]) {
                int v = arrGraph[0];
                int w = arrGraph[1];

                if (distU + w < dist[v]) {
                    dist[v] = distU + w;
                    heapDistPointMin.add(new int[]{dist[v], v});
                }
            }

        }

        return -1;
    }

    private void createGraph(int n, int[][] edges, List<int[]>[] listGraph) {
        Arrays.setAll(listGraph, list -> new ArrayList<int[]>());

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            listGraph[u].add(new int[]{v, w});
            listGraph[v].add(new int[]{u, 2 * w});
        }
    }
}
