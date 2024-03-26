package leetcode.brushQuestions.hard;

import java.util.Arrays;

/**
 * @author gusixue
 * @description 2642. 设计可以求最短路径的图类
 * @date 2024/3/26
 */
public class Graph {

    private static final int INF = (int) (1e9);

    private final int[][] distance;

    // Floyd 算法，初始化与添加时求出任意两点的最短路，如无使用 Graph.INF
    public Graph(int n, int[][] edges) {
        distance = new int[n][n];

        doInitializationGraph(distance, n, edges);
    }

    private void doInitializationGraph(int[][] distance, int n, int[][] edges) {
        for (int i = 0; i < n; i++) {
            Arrays.fill(distance[i], Graph.INF);
            distance[i][i] = 0;
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            distance[u][v] = w;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }
    }

    public void addEdge(int[] edge) {
        int n = this.distance.length;

        int u = edge[0];
        int v = edge[1];
        int w = edge[2];
        distance[u][v] = Math.min(distance[u][v], w);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = Math.min(distance[i][j], distance[i][u] + distance[v][j] + w);
            }
        }
    }

    public int shortestPath(int node1, int node2) {
        return distance[node1][node2] == Graph.INF ? -1 : distance[node1][node2];
    }
}
