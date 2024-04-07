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
        DSU dsu = new DSU(n);

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];

            dsu.union(u, v, w);
        }

        int qLen = query.length;
        int[] res = new int[qLen];
        for (int i = 0; i < qLen; i++) {
            int u = query[i][0];
            int v = query[i][1];

            if (u == v) {
                res[i] = 0;

            } else if (dsu.find(u) != dsu.find(v)) {
                res[i] = -1;

            } else {
                res[i] = dsu.getCostByIndex(dsu.find(u));
            }
        }

        return res;
    }

    static class DSU {
        // 代价为集合中所有边按位与
        private final int[] cost;
        private final int[] parent;
        private final int size;

        public DSU(int n) {
            this.size = n;

            parent = new int[n];
            cost = new int[n];
            for (int i = 0; i < n; ++i) {
                parent[i] = i;
                // (2^31)-1，31 位 1
                cost[i] = Integer.MAX_VALUE;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                // 路径压缩
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int u, int v, int w) {
            int xAncestor = find(u);
            int yAncestor = find(v);
            parent[xAncestor] = yAncestor;
            cost[yAncestor] &= cost[xAncestor];
            cost[yAncestor] &= w;
        }

        public int getCostByIndex(int index) {
            return cost[index];
        }
    }



}
