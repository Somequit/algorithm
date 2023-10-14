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
//        System.out.println(numTemp);


        if (numTemp.size() == 0) {
            return zeroCount + 1;
        }

        long[][] dp = new long[numTemp.size()][r + 1];
        for (int i = 0; i < numTemp.size(); i++) {
            dp[i][0] = 1;
        }
        for (int k = 1; k * numTemp.get(0) <= Math.min(count[numTemp.get(0)] * numTemp.get(0), r); k++) {
            dp[0][k * numTemp.get(0)] = 1;
        }
//        AlgorithmUtils.systemOutArray(dp);

        for (int i = 1; i < numTemp.size(); i++) {
            for (int j = 0; j <=r ; j++) {
                dp[i][j] = dp[i - 1][j];
            }
            for (int k = 1; k <= count[numTemp.get(i)]; k++) {
                int numK = numTemp.get(i) * k;
                for (int j = r; j >= numK; j--) {
                    dp[i][j] += dp[i - 1][j - numK];
                    dp[i][j] %= mod;
                }
            }

        }
//        AlgorithmUtils.systemOutArray(dp);

        long res = 0;
        for (int i = l; i <= r; i++) {
            res += dp[numTemp.size() - 1][i];
            res %= mod;
        }
        res = res * (zeroCount + 1) % mod;

        return (int) (res % mod);
    }



}
