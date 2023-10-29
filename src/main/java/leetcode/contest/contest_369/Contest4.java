package leetcode.contest.contest_369;

import javafx.util.Pair;
import utils.AlgorithmUtils;

import java.util.*;

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
    private int solution(int[][] edges, int[] coins, int k) {
        int n = edges.length + 1;
        List<Integer>[] treeList = new ArrayList[n];
        for (int i = 0; i < treeList.length; i++) {
            treeList[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            treeList[edges[i][0]].add(edges[i][1]);
            treeList[edges[i][1]].add(edges[i][0]);
        }

        int[][][] dp = new int[2][14][n];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 14; j++) {
                for (int l = 0; l < n; l++) {
                    dp[i][j][l] = Integer.MIN_VALUE;
                }
            }
        }

        int res =  Math.max(dfs(-1, 0, 0, treeList, coins, k, 0, dp), dfs(-1, 0, 1, treeList, coins, k, 0, dp));

        return res;
    }

    private int dfs(int father, int cur, int method, List<Integer>[] treeList, int[] coins, int k, int div, int[][][] dp) {
        if (div >= 14) {
            return 0;
        }

        if (dp[method][div][cur] != Integer.MIN_VALUE) {
            return dp[method][div][cur];
        }

        int res;
        if (method == 0) {
            res = coins[cur] / (int) Math.pow(2, div) - k;

        } else {
            res = coins[cur] / (int) Math.pow(2, div + 1);
        }

        for (int next : treeList[cur]) {
            if (next != father) {
                res += Math.max(dfs(cur, next, 0, treeList, coins, k, div + method, dp),
                dfs(cur, next, 1, treeList, coins, k, div + method, dp));
            }
        }

        dp[method][div][cur] = res;

        return res;
    }


}
