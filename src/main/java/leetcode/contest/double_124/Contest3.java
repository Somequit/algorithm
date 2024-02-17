package leetcode.contest.double_124;

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
    public int maxOperations(int[] nums) {
        int n = nums.length;
        if (n <= 3) {
            return 1;
        }

        int res = doMaxOperations(2, n, nums[0] + nums[1], n, nums);
        res = Math.max(res, doMaxOperations(0, n - 2, nums[n - 1] + nums[n - 2], n, nums));
        res = Math.max(res, doMaxOperations(1, n - 1, nums[0] + nums[n - 1], n, nums));

        return res;
    }

    private int doMaxOperations(int begin, int end, int score, int n, int[] nums) {
        int[][] dp = new int[n + 1][n + 1];
        dp[begin][end] = 1;

        int res = 1;
        for (int i = end - begin; i >= 2; i -= 2) {
            for (int j = begin; j + i <= end; j++) {
                int start = j;
                int stop = j + i;
                if (dp[start][stop] > 0) {
                    if (nums[start] + nums[start + 1] == score) {
                        dp[start + 2][stop] = dp[start][stop] + 1;
                        res = Math.max(res, dp[start + 2][stop]);
                    }
                    if (nums[stop - 1] + nums[stop - 2] == score) {
                        dp[start][stop - 2] = dp[start][stop] + 1;
                        res = Math.max(res, dp[start][stop - 2]);
                    }
                    if (nums[start] + nums[stop - 1] == score) {
                        dp[start + 1][stop - 1] = dp[start][stop] + 1;
                        res = Math.max(res, dp[start + 1][stop - 1]);
                    }
                }
            }
        }

        return res;
    }

    private int doMaxOperations2(int begin, int end, int score, int n, int[] nums) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(dp[i], -1);
        }

        dfs(begin, end, score, nums, dp);

        int res = 0;
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                res = Math.max(res, dp[i][j]);
            }
        }

        return res + 1;
    }

    private int dfs(int begin, int end, int score, int[] nums, int[][] dp) {
        if (end - begin <= 1) {
            return 0;
        }
        if (dp[begin][end] != -1) {
            return dp[begin][end];
        }

        int res = 0;
        if (nums[begin] + nums[begin + 1] == score) {
            res = Math.max(res, dfs(begin + 2, end, score, nums, dp) + 1);
        }
        if (nums[end - 1] + nums[end - 2] == score) {
            res = Math.max(res, dfs(begin, end - 2, score, nums, dp) + 1);
        }
        if (nums[begin] + nums[end - 1] == score) {
            res = Math.max(res, dfs(begin + 1, end - 1, score, nums, dp) + 1);
        }
        dp[begin][end] = res;

        return dp[begin][end];
    }


}
