package leetcode.brushQuestions.medium;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author gusixue
 * @description 2192. 有向无环图中一个节点的所有祖先
 * @date 2024/4/4
 */
public class GetAncestors {

    /**
     * 入度为 0 的点入队，类似 BFS 将点的所有祖先与自己、分别加入他指向的节点 v，v 的入度均 -1、当 v 的入度为 0 时入队
     * @param n
     * @param edges
     * @return
     */
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        List<Set<Integer>> resListSet = Stream.generate(() -> new TreeSet<Integer>()).limit(n).collect(Collectors.toList());
        if (edges.length == 0) {
            return resListSet.stream().map(set -> new ArrayList<Integer>()).collect(Collectors.toList());
        }

        int[] inDegree = new int[n];
        List<Integer>[] edgesList = buildTree(n, edges, inDegree);

        Deque<Integer> queueFromPoint = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queueFromPoint.offer(i);
            }
        }

        while (!queueFromPoint.isEmpty()) {
            int u = queueFromPoint.poll();

            for (int v : edgesList[u]) {
                Set<Integer> setV = resListSet.get(v);
                setV.add(u);
                setV.addAll(resListSet.get(u));

                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queueFromPoint.offer(v);
                }
            }
        }

        return resListSet.stream().map(ArrayList::new).collect(Collectors.toList());
    }

    private List<Integer>[] buildTree(int n, int[][] edges, int[] inDegree) {
        List<Integer>[] edgeList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edgeList[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            edgeList[u].add(v);
            inDegree[v]++;
        }
        return edgeList;
    }
}
