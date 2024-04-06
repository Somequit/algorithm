package leetcode.brushQuestions.hard;

import java.util.*;

/**
 * @author gusixue
 * @description 1483. 树节点的第 K 个祖先
 * @date 2024/4/6
 */
public class TreeAncestor {

    private final Map<Integer, Integer>[] mapParent;

    /**
     * 模拟树状数组，建立 mapParent[i][j] 代表节点 i 向上 j（2 的递增次方） 个祖先节点的值
     */
    public TreeAncestor(int n, int[] parent) {
        mapParent = new HashMap[n];
        Arrays.setAll(mapParent, i -> new HashMap<>());

        // 建树顺序只能根据从顶到底，而非递增
        for (int i = 0; i < n; i++) {
            dfsCreateTree(i, parent);
        }
    }

    private Map<Integer, Integer> dfsCreateTree(int curNode, int[] parent) {
        if (mapParent[curNode].size() > 0) {
            return mapParent[curNode];
        }

        int parNode = parent[curNode];
        for (int powDouble = 1; parNode != -1; powDouble *= 2) {
            parNode = dfsCreateTree(parNode, parent).getOrDefault(powDouble, -1);
            mapParent[curNode].put(powDouble * 2, parNode);
        }

        mapParent[curNode].put(1, parent[curNode]);

        return mapParent[curNode];
    }

    /**
     * 模拟树状数组求和，将 k 的最小的二进制 1 对应的值 bitVal 返回，等于 node=mapParent[node][bitVal]，
     * 直到 k=0 或者 node=-1
     */
    public int getKthAncestor(int node, int k) {
        while (k > 0 && node > -1) {
            int bitVal = lowbit(k);
            k -= bitVal;
            node = mapParent[node].getOrDefault(bitVal, -1);
        }
        return node;
    }

    private int lowbit(int k) {
        return (k & -k);
    }

}
