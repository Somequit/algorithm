package template;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gusixue
 * @description DFS 遍历多叉树模板
 * @date 2023/11/5
 */
public class DfsTree {

    private long solution(int[][] edges, int[] values) {
        int n = values.length;

        List<Integer>[] edgeList = buildTree(n, edges);

        return dfsTraversalTree(0, -1, edgeList, values);
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

    private long dfsTraversalTree(int son, int father, List<Integer>[] edgeList, int[] values) {
        long res = 0L;

        // 叶子节点
        if (edgeList[son].size() == 1 && edgeList[son].get(0).equals(father)) {
            return res;
        }

        for (int next : edgeList[son]) {
            if (next != father) {
                dfsTraversalTree(next, son, edgeList, values);
            }
        }

        return res;
    }
}

