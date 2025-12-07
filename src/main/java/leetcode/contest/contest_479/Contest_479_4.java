package leetcode.contest.contest_479;

import java.util.*;


/**
 * @author gusixue
 * @description
 * @date 2025/12/7 10:28 上午
 */
public class Contest_479_4 {

    public int[] maxSubgraphScore(int n, int[][] edges, int[] good) {
        List<Integer>[] edgeList = buildTree(n, edges);

        int[] res = Arrays.copyOf(good, n);
        dfsUpTraversalTree(0, -1, edgeList, good, res);
//        System.out.println(Arrays.toString(res));

        dfsDownTraversalTree(0, -1, edgeList, good, res);

        return res;
    }

    private void dfsDownTraversalTree(int son, int father, List<Integer>[] edgeList, int[] good, int[] res) {
        // 叶子节点
        if (edgeList[son].size() == 1 && edgeList[son].get(0).equals(father)) {
            return;
        }

        for (int next : edgeList[son]) {
            if (next != father) {
                if (res[son] > 0) {
                    if (res[next] < 0) {
                        res[next] += res[son];

                    } else {
                        res[next] = Math.max(res[next], res[son]);
                    }
                }
                dfsDownTraversalTree(next, son, edgeList, good, res);
            }
        }
    }

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

    private int dfsUpTraversalTree(int son, int father, List<Integer>[] edgeList, int[] good, int[] res) {
        // 叶子节点
        if (edgeList[son].size() == 1 && edgeList[son].get(0).equals(father)) {
            res[son] = (good[son] == 0 ? -1 : 1);
            return res[son];
        }

        int curNum = (good[son] == 0 ? -1 : 1);
        for (int next : edgeList[son]) {
            if (next != father) {
                curNum += Math.max(dfsUpTraversalTree(next, son, edgeList, good, res), 0);
            }
        }

        res[son] = curNum;
        return res[son];
    }
}
