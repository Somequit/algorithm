package leetcode.contest.double_125;

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
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        int n = nums.length;

        List<Integer>[] edgeList = buildTree(n, edges);

        // 每个节点的 List 均包含父节点
        edgeList[0].add(-1);
        // dp[i]：以 i 为根的子树的最大总和，dp[i][0]：节点 i 经过偶数次异或、dp[i][1]：节点 i 经过奇数次异或
        long[][] dp = new long[n][2];
        dfsTraversalTree(0, -1, edgeList, nums, dp, k);

        return Math.max(dp[0][0], dp[0][1]);
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

    private long dfsTraversalTree(int son, int father, List<Integer>[] edgeList, int[] nums, long[][] dp, int k) {
        long res = 0L;

        // 叶子节点，叶子节点形成的子树无法异或
        if (edgeList[son].size() == 1 && edgeList[son].get(0).equals(father)) {
            dp[son][0] = nums[son];
            dp[son][1] = Integer.MIN_VALUE;
            return res;
        }

        // son 节点形成的子树中，孩子节点的最大总和
        long[][] sonNums = new long[edgeList[son].size() - 1][2];
        long sum = 0;
        int index = 0;
        for (int next : edgeList[son]) {
            if (next != father) {
                dfsTraversalTree(next, son, edgeList, nums, dp, k);

                // sonNums[son][0]：每个孩子节点不与当前节点异或的最大值（孩子 与 孙子 异或偶数次或者奇数次）
                sonNums[index][0] = Math.max(dp[next][0], dp[next][1]);
                sum += sonNums[index][0];
                // sonNums[son][1]：每个孩子节点与当前节点异或后的最大值（孩子 与 孙子 异或偶数次或者奇数次）
                sonNums[index][1] = Math.max(dp[next][0] - nums[next] + (nums[next] ^ k), dp[next][1] + nums[next] - (nums[next] ^ k));
                index++;
            }
        }

        // 所有孩子均不与当前节点异或（o0） 循环到 所有孩子均与当前节点异或（o1） 的最大值，o0-o1 升序、保证 x 个孩子与当前节点异或后均为最大值
        Arrays.sort(sonNums, Comparator.comparingLong(o -> o[0] - o[1]));

        dp[son][0] = sum;
        for (int i = 0; i < sonNums.length; i++) {
            sum = sum - sonNums[i][0] + sonNums[i][1];

            if (i % 2 == 0) {
                dp[son][1] = Math.max(sum, dp[son][1]);

            } else {
                dp[son][0] = Math.max(sum, dp[son][0]);
            }
        }
        // 加上当前节点（异或偶数次、异或奇数次）
        dp[son][0] += nums[son];
        dp[son][1] += (nums[son] ^ k);
//        System.out.println(son + " : " + edgeList[son]);
//        Arrays.stream(sonNums).forEach(t -> System.out.println(Arrays.toString(t)));
//        System.out.println(son + " : " + dp[son][0] + " : " + dp[son][1]);

        return res;
    }


}
