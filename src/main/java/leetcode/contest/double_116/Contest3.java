package leetcode.contest.double_116;


import java.util.List;

/**
 * @author gusixue
 * @date 2023/10/28
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
//            int purchaseAmount = AlgorithmUtils.systemInNumberInt();

//            int res = contest.solution(purchaseAmount);
//            System.out.println(res);
        }

    }

    /**
     * @return
     */
    private int solution(List<Integer> nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int i = 0; i < nums.size(); i++) {
            for (int j = target; j >= nums.get(i); j--) {
                if (dp[j - nums.get(i)] > 0) {
                    dp[j] = Math.max(dp[j], dp[j - nums.get(i)] + 1);
                }
            }
        }

        return dp[target] - 1;
    }


}
