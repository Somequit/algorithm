package leetcode.contest.contest_392;

import java.util.*;
/**
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();
//        contest.minimumCost(0, new int[0][0], new int[0][0]);

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
    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        int[] parent = new int[n];
        int[] weight = new int[n];
        unionFind(n, edges, parent, weight);

        int qLen = query.length;
        int[] res = new int[qLen];
        for (int i = 0; i < qLen; i++) {
            int parentA = find(query[i][0], parent);
            int parentB = find(query[i][1], parent);
            if (query[i][0] == query[i][1]) {
                res[i] = 0;

            } else if (parentA != parentB) {
                res[i] = -1;

            } else {
                res[i] = weight[parent[parentA]];
            }
        }
        return res;
    }

    /**
     * 并查集将所有边对应的点连通，每条边（a，b）使用 a 指向 b
     */
    private int[] unionFind(int n, int[][] edges, int[] parent, int[] weight) {
        int edgesLen = edges.length;

        // 并查集中每个点指向的父亲点，刚开始是自己
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            weight[i] = (1 << 30) - 1;
        }

        // 每条边（a，b）使用 a 指向 b
        for (int i = 0; i < edgesLen; i++) {
            int pointA = edges[i][0];
            int pointB = edges[i][1];
            int curWeight = edges[i][2];

            union(pointA, pointB, parent, weight, curWeight);
        }

        return parent;
    }

    private void union(int pointA, int pointB, int[] parent, int[] weight, int curWeight) {
        int parentA = find(pointA, parent);
        int parentB = find(pointB, parent);
        weight[parent[parentB]] &= weight[parent[parentA]];
        weight[parent[parentB]] &= curWeight;
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


}
