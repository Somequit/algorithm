package leetcode.contest.double_120;

import java.util.*;
import java.util.stream.Collectors;

/**
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

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
    public long[] placedCoins(int[][] edges, int[] cost) {
        int n = edges.length + 1;
        List<Integer>[] edgeList = buildTree(n, edges);


        long[] res = new long[n];
        dfsTraversalTree(0, -1, edgeList, cost, res);
        return res;
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

    private List<Integer> dfsTraversalTree(int son, int father, List<Integer>[] edgeList, int[] cost, long[] res) {
        // 叶子节点
        if (edgeList[son].size() == 1 && edgeList[son].get(0).equals(father)) {
            List<Integer> list = new ArrayList<>();
            list.add(cost[son]);
            res[son] = 1;
            return list;
        }

        List<Integer> list = new ArrayList<>();
        list.add(cost[son]);
        for (int next : edgeList[son]) {
            if (next != father) {
                List<Integer> listTemp = dfsTraversalTree(next, son, edgeList, cost, res);
                list.addAll(listTemp);

                if (list.size() > 6) {
                    List<Integer> list2 = list;
                    list = new ArrayList<>();
                    list.addAll(list2.stream().sorted().limit(3).collect(Collectors.toList()));
                    list.addAll(list2.stream().sorted((o1, o2) -> o2 - o1).limit(3).collect(Collectors.toList()));
                }
            }
        }

        if (list.size() < 3) {
            res[son] = 1;

        } else {
            Collections.sort(list);
            long resNum = (long)list.get(0) * list.get(1) * list.get(2);
            resNum = Math.max(resNum, (long)list.get(0) * list.get(1) * list.get(list.size() - 1));
            resNum = Math.max(resNum, (long)list.get(0) * list.get(list.size() - 2) * list.get(list.size() - 1));
            resNum = Math.max(resNum, (long)list.get(list.size() - 3) * list.get(list.size() - 2) * list.get(list.size() - 1));

            if (resNum < 0) {
                res[son] = 0;
            } else {
                res[son] = resNum;
            }
        }

        return list;
    }


}
