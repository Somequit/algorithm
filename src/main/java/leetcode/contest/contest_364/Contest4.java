package leetcode.contest.contest_364;


import javafx.util.Pair;
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

            long res = contest.solution(n, edges);
            System.out.println(res);
        }

    }

    private static final int maxCount = 100000;
    private static final boolean[] notPrim = new boolean[maxCount + 1];
    private static final int[] primes = new int[maxCount + 1];

    static {
        int cnt = 0;
        for (int i = 2; i <= maxCount; i++) {
            if (!notPrim[i]) {
                primes[cnt] = i;
                cnt++;
            }

            for (int j = 0; primes[j] <= maxCount / i; j++) {
                notPrim[primes[j] * i] = true;
                if (i % primes[j] == 0) {
                    break;
                }
            }
        }
        notPrim[1] = true;

//        System.out.println(Arrays.toString(isPrim));
//        System.out.println(Arrays.toString(primes));

    }


    private static long countPath = 0;
    private long solution(int n, int[][] edges) {
        if (n <= 2) {
            return n - 1;
        }
        countPath = 0;

        List<Integer>[] treeList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            treeList[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            treeList[edges[i][0]].add(edges[i][1]);
            treeList[edges[i][1]].add(edges[i][0]);
        }
        System.out.println(Arrays.toString(treeList));

        boolean[] vis = new boolean[n + 1];
        vis[1] = true;
        Pair<Integer, Integer>[] primeCount = new Pair[n + 1];

        dfs(1, treeList, vis, primeCount);

        return countPath;
    }

    private Pair<Integer, Integer> dfs(int point, List<Integer>[] treeList, boolean[] vis, Pair<Integer, Integer>[] primeCount) {
        int zeroCount = 0;
        int oneCount = 0;

        List<Integer> list = treeList[point];
        List<Long> zeroList = new ArrayList<>();
        List<Long> oneList = new ArrayList<>();
        long totalOne = 0L;
        for (Integer nextPoint : list) {
            if (!vis[nextPoint]) {
                vis[nextPoint] = true;
                Pair<Integer, Integer> nextPair = dfs(nextPoint, treeList, vis, primeCount);
                zeroCount += nextPair.getKey();
                oneCount += nextPair.getValue();

                zeroList.add((long)nextPair.getKey());
                oneList.add((long)nextPair.getValue());
                totalOne += nextPair.getValue();
                System.out.println(point + " : " + nextPoint);
            }
        }

        Pair<Integer, Integer> curpair;
        System.out.println(point + " : " + notPrim[point]);
        if (notPrim[point]) {
            curpair = new Pair<>(zeroCount + 1, oneCount);
            for (int i = 0; i < zeroList.size(); i++) {
                countPath += zeroList.get(i) * (totalOne - oneList.get(i));
            }
            countPath += totalOne;

        } else {
            curpair = new Pair<>(0, zeroCount + 1);
            long total = 1;
            for (long zero : zeroList) {
                countPath += total * zero;
                total += zero;
            }
        }
        System.out.println(curpair);
        return curpair;
    }


}
