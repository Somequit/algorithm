package leetcode.contest.double_115;


import utils.AlgorithmUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gusixue
 * @date 2023/7/22
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
            List<Integer> nums = AlgorithmUtils.systemInList();
            int l = AlgorithmUtils.systemInNumberInt();
            int r = AlgorithmUtils.systemInNumberInt();

            int res = contest.solution(nums, l, r);
            System.out.println(res);
        }

    }

    private int solution(List<Integer> nums, int l, int r) {
        int mod = 1_000_000_007;

        int[] count = new int[20001];
        for (int num : nums) {
            count[num]++;
        }
        int zeroCount = count[0];

        List<Integer> numTemp = new ArrayList<>();
        for (int i = 1; i < Math.min(20001, r + 1); i++) {
            if (count[i] > 0) {
                numTemp.add(i);
            }
        }

        if (numTemp.size() == 0) {
            if (l == 0) {
                return zeroCount + 1;

            } else {
                return 0;
            }
        }

        long[][] dp = new long[numTemp.size() + 1][r + 1];
        dp[0][0] = 1;

        for (int i = 1; i < numTemp.size() + 1; i++) {
            for (int j = 0; j < r + 1; j++) {
                dp[i][j] = dp[i - 1][j];
            }
            int numValue = numTemp.get(i - 1);

            long[] prev = new long[r + 1];
            for (int j = numValue; j < r + 1; j++) {
                prev[j] = prev[j - numValue] + dp[i - 1][j - numValue];
                if (j >= (count[numValue] + 1) * numValue) {
                    prev[j] = prev[j] - dp[i - 1][j - (count[numValue] + 1) * numValue] + mod;
                }
                prev[j] %= mod;
            }

            for (int j = numValue; j < r + 1; j++) {
                dp[i][j] += prev[j];
                dp[i][j] %= mod;
            }

        }
//        AlgorithmUtils.systemOutArray(dp);

        long res = 0;
        for (int i = l; i < r + 1; i++) {
            res += dp[numTemp.size()][i];
            res %= mod;
        }
        res = res * (zeroCount + 1) % mod;

        return (int) (res % mod);
    }



}
