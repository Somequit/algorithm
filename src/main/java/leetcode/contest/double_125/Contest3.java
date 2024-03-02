package leetcode.contest.double_125;

import javafx.util.Pair;

import java.util.*;
/**
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

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
    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        int n = edges.length + 1;
        List<Pair<Integer, Integer>>[] edgeList = buildTree(n, edges);

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            if (edgeList[i].size() == 1) {
                continue;
            }

            int[] countSpeed = new int[n];
            dfsTraversalTree(i, -1, edgeList, 0, signalSpeed, countSpeed);
//            System.out.println(i + " : " + Arrays.toString(countSpeed));

            int prefix = countSpeed[edgeList[i].get(0).getKey()];
            for (int j = 1; j < edgeList[i].size(); j++) {
                res[i] += countSpeed[edgeList[i].get(j).getKey()] * prefix;
                prefix += countSpeed[edgeList[i].get(j).getKey()];
            }
        }

        return res;
    }

    private List<Pair<Integer, Integer>>[] buildTree(int n, int[][] edges) {
        List<Pair<Integer, Integer>>[] edgeList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edgeList[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];
            edgeList[u].add(new Pair<>(v, w));
            edgeList[v].add(new Pair<>(u, w));
        }
        return edgeList;
    }

    private void dfsTraversalTree(int son, int father, List<Pair<Integer, Integer>>[] edgeList,
                                  int curSpeed, int signalSpeed, int[] countSpeed) {
        if (curSpeed % signalSpeed == 0) {
            countSpeed[son]++;
        }

        for (Pair<Integer, Integer> nextPair : edgeList[son]) {
            if (nextPair.getKey() != father) {
                dfsTraversalTree(nextPair.getKey(), son, edgeList, curSpeed + nextPair.getValue(), signalSpeed, countSpeed);
                countSpeed[son] += countSpeed[nextPair.getKey()];
            }
        }
    }


}
