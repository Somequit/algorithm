package leetcode.contest.double_129;

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
    public int numberOfStableArrays(int zero, int one, int limit) {
        int mod = (int) (1e9 + 7);
        // dp0[i][j][k] - i个0，j个1，连续k个0
        long[][][] dp0 = new long[zero + 1][one + 1][limit + 1];
        // dp1[i][j][k] - i个0，j个1，连续k个1
        long[][][] dp1 = new long[zero + 1][one + 1][limit + 1];

        for (int i = 0; i < Math.min(one, limit) + 1; i++) {
            dp0[0][i][0] = 1;
            dp1[0][i][i] = 1;
        }

        for (int i = 0; i < Math.min(zero, limit) + 1; i++) {
            dp1[i][0][0] = 1;
            dp0[i][0][i] = 1;
        }

        for (int i = 1; i < zero + 1; i++) {
            for (int j = 1; j < one + 1; j++) {
                // dp0
                for (int l = 1; l < Math.min(limit, i) + 1; l++) {
                    dp0[i][j][0] += dp0[i][j - 1][l];
                    dp0[i][j][0] %= mod;
                }
                for (int l = 1; l < Math.min(limit, j - 1 + 1); l++) {
                    dp0[i][j][0] += dp1[i][j - 1][l];
                    dp0[i][j][0] %= mod;
                }


                for (int k = 1; k < Math.min(limit, i) + 1; k++) {
                    dp0[i][j][k] += dp0[i - 1][j][k - 1];
                    dp0[i][j][k] %= mod;
                }


                // dp1
                for (int l = 1; l < Math.min(limit, i - 1 + 1); l++) {
                    dp1[i][j][0] += dp0[i - 1][j][l];
                    dp1[i][j][0] %= mod;
                }
                for (int l = 1; l < Math.min(limit, j) + 1; l++) {
                    dp1[i][j][0] += dp1[i - 1][j][l];
                    dp1[i][j][0] %= mod;
                }


                for (int k = 1; k < Math.min(limit, j) + 1; k++) {
                    dp1[i][j][k] += dp1[i][j - 1][k - 1];
                    dp1[i][j][k] %= mod;
                }
            }
        }

//         Arrays.stream(dp0).forEach(dp0Arr ->
//                 Arrays.stream(dp0Arr).forEach(dp0Long ->
//                         System.out.println(Arrays.toString(dp0Long))));
//         System.out.println();

//         Arrays.stream(dp1).forEach(dp1Arr ->
//                 Arrays.stream(dp1Arr).forEach(dp1Long ->
//                         System.out.println(Arrays.toString(dp1Long))));

//         System.out.println();

        long res = dp0[zero][one][0] + dp1[zero][one][0];

        return (int) (res % mod);
    }


}
