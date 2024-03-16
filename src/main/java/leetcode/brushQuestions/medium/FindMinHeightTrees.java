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
        int[] degree = new int[n];
        Set<Integer>[] edgesSet = Stream.generate(HashSet::new).limit(n).toArray(Set[]::new);

        Stream.of(edgesSet).forEach(System.out::println);

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            degree[u]++;
            degree[v]++;
            edgesSet[u].add(v);
            edgesSet[v].add(u);
        }

        List<Integer> res = Stream.iterate(0, a -> a = a + 1).limit(n).filter(i -> degree[i] <= 1).collect(Collectors.toList());
        System.out.println(Arrays.toString(degree));
        System.out.println(res);
        while (true) {
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < res.size(); i++) {
                int u = res.get(i);
                if (edgesSet[u].size() == 0) {
                    continue;
                }
                int v = edgesSet[u].toArray(new Integer[1])[0];

                System.out.println(u + " : " + v);
                edgesSet[u].remove(v);
                edgesSet[v].remove(u);
                degree[u]--;
                degree[v]--;
                System.out.println(degree[u] + " : " + degree[v]);
                if (degree[v] == 1) {
                    temp.add(v);
                }
            }

            if (temp.size() == 0) {
                break;
            }

            res = temp;
        }

        return res;
    }
}
