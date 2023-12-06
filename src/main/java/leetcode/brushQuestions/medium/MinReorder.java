package leetcode.brushQuestions.medium;

import javafx.util.Pair;

import java.util.*;

/**
 * @author gusixue
 * @description 1466. 重新规划路线
 * @date 2023/12/7
 */
public class MinReorder {

    /**
     * 正难则反 DFS + 无向图转有向图：
     *
     * 第 1 步：
     * 先建立一个无向图 G0
     *
     * 第 2 步：
     * 从 0 开始走无向图 G0，直到所有节点
     * 记录此时经过的所有边形成有向图 G1
     *
     * 第 3 步：
     * 建立原图的有向图 G2
     *
     * **第 4 步**：
     * 遍历每个点，记录 G2 改成 G1 需要修改的边数 edge
     *     * 遍历每个点 p
     *     * 将 G1 从 p 可以转到的点放入 set
     *     * 遍历 G2 从 p 可以转移到的点去 map 中删除（不在 set 则不删）
     *     * set 剩下的元素个数，则是 G2 需要修改的个数
     * 此边数为重新规划后的**反向图**应该修改的边数
     * 注意仅有一种修改结果
     *
     * 第 5 步：
     * 答案就是：total(即 n-1) - edge
     * 时间复杂度：O（n），空间复杂度：O（n）
     *
     */
    public int minReorder(int n, int[][] connections) {
        // 先建立一个无向图 G0
        List<Integer>[] treeList0 = buildTree(n, connections);

        // 从 0 开始走无向图，直到所有节点，记录此时经过的所有边形成有向图 G1
        List<Integer>[] treeList1 = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            treeList1[i] = new ArrayList<>();
        }
        dfsTraversalTree(0, -1, treeList0, treeList1);

        // 建立原图的有向图 G2
        List<Integer>[] treeList2 = buildDirectedTree(n, connections);

        // 遍历每个点，记录 G2 改成 G1 需要修改的边数 edge
        int res = 0;
        Set<Integer> pointSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int point : treeList1[i]) {
                pointSet.add(point);
            }
            for (int point : treeList2[i]) {
                pointSet.remove(point);
            }

            res += pointSet.size();
            pointSet.clear();
        }

        return n - 1 - res;
    }

    /**
     * 建立有向图
     */
    private List<Integer>[] buildDirectedTree(int n, int[][] edges) {
        List<Integer>[] edgeList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edgeList[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            edgeList[u].add(v);
        }
        return edgeList;
    }
    /**
     * 从 0 开始走无向图，直到所有节点，记录此时经过的所有边形成有向图 G1
     */
    private void dfsTraversalTree(int son, int father, List<Integer>[] treeList0, List<Integer>[] treeList1) {

        for (int next : treeList0[son]) {
            if (next != father) {
                treeList1[son].add(next);
                dfsTraversalTree(next, son, treeList0, treeList1);
            }
        }

    }

    /**
     * 建立无向图
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


    /**
     * DFS 无向建图 + 设定权值 + 反向遍历:
     *
     * 第 1 步：
     * 建立无向图 G，注意无环无重边
     * 将正向边权值设为 1，反向边权值设为 0
     *
     * 第 2 步：
     * 从 0 开始走到所有的点，记录权值总和就是结果
     * 因为走的是需要结果的反向图（需要所有点到 0），因此走到正向边代表此边需要反转、走到反向边则不需要
     * 时间复杂度：O（n），空间复杂度：O（n）
     *
     */
    public int minReorderOptimization(int n, int[][] connections) {
        // 建无向图，将正向边权值设为 1，反向边权值设为 0
        List<Pair<Integer, Integer>>[] treeWeightList = buildWeightTree(n, connections);

        // 从 0 开始走到所有的点，记录权值总和就是结果
        return dfsTraversalWeightTree(0, -1, treeWeightList);
    }
    /**
     * 从 0 开始走到所有的点，记录权值总和就是结果
     */
    private int dfsTraversalWeightTree(int son, int father, List<Pair<Integer, Integer>>[] treeList) {
        int res = 0;

        for (Pair<Integer, Integer> nextPair : treeList[son]) {
            int next = nextPair.getKey();
            int weight = nextPair.getValue();

            if (next != father) {
                res += weight + dfsTraversalWeightTree(next, son, treeList);
            }
        }

        return res;
    }

    /**
     * 建无向图，将正向边权值设为 1，反向边权值设为 0
     */
    private List<Pair<Integer, Integer>>[] buildWeightTree(int n, int[][] edges) {
        List<Pair<Integer, Integer>>[] edgeList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edgeList[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            edgeList[u].add(new Pair<>(v, 1));
            edgeList[v].add(new Pair<>(u, 0));
        }
        return edgeList;
    }
}
