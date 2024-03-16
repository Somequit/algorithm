package leetcode.brushQuestions.medium;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author gusixue
 * @description 310. 最小高度树
 * @date 2024/3/17
 */
public class FindMinHeightTrees {

    /**
     * 找到每个点的度，循环将度为 1 的点删除，直到最后一批度为 1 的点就是结果
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            List<Integer> res = new ArrayList<>();
            res.add(0);
            return res;
        }

        int[] degree = new int[n];
        Map<Integer, Set<Integer>> edgesMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            edgesMap.put(i, new HashSet<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            degree[u]++;
            degree[v]++;
            edgesMap.get(u).add(v);
            edgesMap.get(v).add(u);
        }

        List<Integer> temp = new ArrayList<>();
        List<Integer> res = Stream.iterate(0, a -> a = a + 1).limit(n).filter(i -> degree[i] <= 1).collect(Collectors.toList());
        while (res.size() != edgesMap.size()) {
            temp = res;
            res = new ArrayList<>();

            for (int i = 0; i < temp.size(); i++) {
                int u = temp.get(i);
                int v = 0;
                for (int vTemp : edgesMap.get(u)) {
                    v = vTemp;
                }

                edgesMap.remove(u);
                edgesMap.get(v).remove(u);
                degree[u]--;
                degree[v]--;
                if (degree[v] == 1) {
                    res.add(v);
                }
            }
        }

        return res;
    }
}
