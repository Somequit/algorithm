package leetcode.contest.contest_345;

import utils.AlgorithmUtils;

import java.util.*;


/**
 * @author gusixue
 * @description
 * 6432. 统计完全连通分量的数量
 * 给你一个整数 n 。现有一个包含 n 个顶点的 无向 图，顶点按从 0 到 n - 1 编号。
 * 给你一个二维整数数组 edges 其中 edges[i] = [ai, bi] 表示顶点 ai 和 bi 之间存在一条 无向 边。
 * 返回图中 完全连通分量 的数量。
 * 如果在子图中任意两个顶点之间都存在路径，并且子图中没有任何一个顶点与子图外部的顶点共享边，则称其为 连通分量 。
 * 如果连通分量中每对节点之间都存在一条边，则称其为 完全连通分量 。
 * 1 <= n <= 50
 * 0 <= edges.length <= n * (n - 1) / 2
 * edges[i].length == 2
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * 不存在重复的边
 * @date 2023/5/14
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
            int n = AlgorithmUtils.systemInNumberInt();
            int[][] edges = AlgorithmUtils.systemInTwoArray();
//            AlgorithmUtils.systemOutArray(edges);

            int res = contest.countCompleteComponents(n, edges);
            System.out.println(res);
        }

    }

    /**
     * 并查集 + 图论：通过并查集找到所有连通分量，然后判断每块连通分量的所有边数是否为 n*(n-1)/2，是则代表完全连通分量，因为：边对应的点不错误、无向、不重复、不自连
     * n 为节点数，m 为边数，时间复杂度：O（α(n)+m），空间复杂度：O（n+m）
     */
    private int countCompleteComponents(int n, int[][] edges) {
        // 并查集将连通分量合并在一起
        int[] parent = unionFind(n, edges);
//        System.out.println(Arrays.toString(parent));

        // 每个连通分量确定是否是完全连通分量
        int res = determineCompleteComponents(parent, n, edges);

        return res;
    }

    /**
     * 并查集将所有边对应的点连通，每条边（a，b）使用 a 指向 b
     */
    private int[] unionFind(int n, int[][] edges) {
        int edgesLen = edges.length;

        // 并查集中每个点指向的父亲点，刚开始是自己
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // 每条边（a，b）使用 a 指向 b
        for (int i = 0; i < edgesLen; i++) {
            int pointA = edges[i][0];
            int pointB = edges[i][1];

            union(pointA, pointB, parent);
        }

        return parent;
    }

    private void union(int pointA, int pointB, int[] parent) {
        int parentA = find(pointA, parent);
        int parentB = find(pointB, parent);
        parent[parentA] = parent[parentB];
    }

    private int find(int pointA, int[] parent) {
        if (parent[pointA] == pointA) {
            return pointA;
        }
        // 路径压缩
        parent[pointA] = find(parent[pointA], parent);
        return parent[pointA];
    }

    /**
     * 判断每块连通分量的所有边数是否为 n*(n-1)/2，是则代表完全连通分量，因为：边对应的点不错误、无向、不重复、不自连
     */
    private int determineCompleteComponents(int[] parent, int n, int[][] edges) {
        Map<Integer, List<Integer>> completeMap = new HashMap<>((n / 3) << 2);

        // 根据并查集将同一个连通分量放在同一个 List 中
        for (int i = 0; i < n; i++) {
            int parentPoint = find(i, parent);

            List<Integer> completeList = completeMap.getOrDefault(parentPoint, new ArrayList<>(n));
            completeList.add(i);
            completeMap.put(parentPoint, completeList);
        }
//        System.out.println(completeMap);

        // 将边计数到对应连通分量中
        Map<Integer, Integer> completeEdgesCountMap = new HashMap<>((completeMap.size() / 3) << 2);
        int edgesLen = edges.length;
        for (int i = 0; i < edgesLen; i++) {
            int parentPoint = find(edges[i][0], parent);

            completeEdgesCountMap.put(parentPoint, completeEdgesCountMap.getOrDefault(parentPoint, 0) + 1);
        }
//        System.out.println(completeEdgesCountMap);

        // 每个连通分量判断是否是完全连通分量
        int count = 0;
        for (Map.Entry<Integer, List<Integer>> completeEntry : completeMap.entrySet()) {

            int completeEdgesCount = completeEdgesCountMap.getOrDefault(completeEntry.getKey(), 0);
            int completePointCount = completeEntry.getValue().size();

            if (completePointCount * (completePointCount - 1) / 2 == completeEdgesCount) {
                count++;
            }
        }

        return count;
    }

}
