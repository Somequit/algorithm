package leetcode.contest.double_113;


import utils.AlgorithmUtils;

import java.util.*;

/**
 * @author gusixue
 * @date 2023/7/22
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
            int n = AlgorithmUtils.systemInNumberInt();
            int[][] edges = AlgorithmUtils.systemInTwoArray();

            int[] res = contest.solution(n, edges);
            System.out.println(Arrays.toString(res));
        }

    }

    private int[] solution(int n, int[][] edges) {
        int[] inDegree = new int[n];
        Map<Integer, List<Integer>> edgesOutMap = new HashMap<>();
        Map<Integer, List<Integer>> edgesInMap = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            inDegree[v]++;

            List<Integer> tempList;
            if (edgesOutMap.containsKey(u)) {
                tempList = edgesOutMap.get(u);
            } else {
                tempList = new ArrayList<>();
                edgesOutMap.put(u, tempList);
            }
            tempList.add(v);

            if (edgesInMap.containsKey(v)) {
                tempList = edgesInMap.get(v);
            } else {
                tempList = new ArrayList<>();
                edgesInMap.put(v, tempList);
            }
            tempList.add(u);
        }
//        System.out.println(Arrays.toString(inDegree));
//        System.out.println(edgesOutMap);
//        System.out.println(edgesInMap);

        int inZeroP = -1;
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                inZeroP = i;
                break;
            }
        }
//        System.out.println(inZeroP);

        int[] res = new int[n];
        Arrays.fill(res, -1);

        res[inZeroP] = dfs(edgesOutMap, edgesInMap, inZeroP, -1);
//        System.out.println(res[inZeroP]);

        dfsAll(edgesOutMap, edgesInMap, inZeroP, -1, res);

        return res;
    }

    private void dfsAll(Map<Integer,List<Integer>> edgesOutMap, Map<Integer,List<Integer>> edgesInMap, int point, int prePoint, int[] res) {
        if (edgesOutMap.get(point) != null) {
            List<Integer> tempList = edgesOutMap.get(point);
            for (Integer nextPoint : tempList) {
                if (nextPoint != prePoint) {
                    if (res[nextPoint] == -1) {
                        res[nextPoint] = res[point] + 1;
                    }
                    dfsAll(edgesOutMap, edgesInMap, nextPoint, point, res);
                }
            }
        }

        if (edgesInMap.get(point) != null) {
            List<Integer> tempList = edgesInMap.get(point);
            for (Integer nextPoint : tempList) {
                if (nextPoint != prePoint) {
                    if (res[nextPoint] == -1) {
                        res[nextPoint] = res[point] - 1;
                    }
                    dfsAll(edgesOutMap, edgesInMap, nextPoint, point, res);
                }
            }
        }
    }

    private int dfs(Map<Integer,List<Integer>> edgesOutMap, Map<Integer,List<Integer>> edgesInMap, int point, int prePoint) {
        int res = 0;
        if (edgesOutMap.get(point) != null) {
            List<Integer> tempList = edgesOutMap.get(point);
            for (Integer nextPoint : tempList) {
                if (nextPoint != prePoint) {
                    res += dfs(edgesOutMap, edgesInMap, nextPoint, point);
                }
            }
        }


        if (edgesInMap.get(point) != null) {
            List<Integer> tempList = edgesInMap.get(point);
            for (Integer nextPoint : tempList) {
                if (nextPoint != prePoint) {
                    res++;
                    res += dfs(edgesOutMap, edgesInMap, nextPoint, point);
                }
            }
        }

        return res;

    }


}
