package leetcode.contest.contest_369;


import java.util.Arrays;

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
    private long solution(int[] nums, int k) {
        int n = nums.length;
        long[] dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = Math.max(k - nums[0], 0);
        dp[2] = Math.max(k - nums[1], 0);

        for (int i = 3; i <= n; i++) {
            int operation = Math.max(k - nums[i - 1], 0);

            dp[i] = Math.min(Math.min(dp[i - 3], dp[i - 1]), dp[i - 2]) + operation;
        }
//        System.out.println(Arrays.toString(dp));

        return Math.min(Math.min(dp[n], dp[n - 1]), dp[n - 2]);
    }


}
