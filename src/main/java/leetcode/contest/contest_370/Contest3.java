package leetcode.contest.contest_370;


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
    private long solution(int[][] edges, int[] values) {
        int n = values.length;
        List<Integer>[] edgeList = new ArrayList[n];
        long total = 0L;

        for (int i = 0; i < n; i++) {
            edgeList[i] = new ArrayList<>();

            total += values[i];
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            edgeList[u].add(v);
            edgeList[v].add(u);
        }

        return total - dfs(0, -1, edgeList, values);
    }

    private long dfs(int cur, int father, List<Integer>[] edgeList, int[] values) {
        long res = 0L;

        for (int next : edgeList[cur]) {
            if (next != father) {
                res += dfs(next, cur, edgeList, values);
            }
        }

        if (res == 0) {
            return values[cur];
        }

        return Math.min(values[cur], res);
    }


}
