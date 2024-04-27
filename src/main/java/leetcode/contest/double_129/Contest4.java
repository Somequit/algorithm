package leetcode.contest.double_129;

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
    public int numberOfStableArrays(int zero, int one, int limit) {
        int mod = (int) (1e9 + 7);
        // dp0[i][j] - i个0，j个1，最后为 0 的稳定个数
        long[][] dp0 = new long[zero + 1][one + 1];
        // dp1[i][j] - i个0，j个1，最后为 1 的稳定个数
        long[][] dp1 = new long[zero + 1][one + 1];

        // dp0[i][j] 的 每行 i 计算 j 的前缀和
        long[][] dp0Prefix = new long[zero + 1][one + 2];
        // dp1[i][j] 的 每列 j 计算 i 的前缀和
        long[][] dp1Prefix = new long[zero + 2][one + 1];
        for (int i = 0; i < Math.min(zero, limit) + 1; i++) {
            dp0[i][0] = 1;
            dp0Prefix[i][1] += dp0Prefix[i][0] + dp0[i][0];
        }

        for (int j = 0; j < Math.min(one, limit) + 1; j++) {
            dp1[0][j] = 1;
            dp1Prefix[1][j] += dp1Prefix[0][j] + dp1[0][j];
        }


        for (int i = 1; i < zero + 1; i++) {
            for (int j = 1; j < one + 1; j++) {
//                // 最后是1，放入 [1, min(limit, i)] 个 0
//                for (int l = 1; l < Math.min(limit, i) + 1; l++) {
//                    dp0[i][j] += dp1[i - l][j];
//                    dp0[i][j] %= mod;
//                }
//
//                // 最后是0，放入 [1, min(limit, j)] 个 1
//                for (int l = 1; l < Math.min(limit, j) + 1; l++) {
//                    dp1[i][j] += dp0[i][j - l];
//                    dp1[i][j] %= mod;
//                }


                // 前缀和优化
                dp0[i][j] += dp1Prefix[i][j] + mod - dp1Prefix[i - Math.min(limit, i)][j];
                dp0[i][j] %= mod;

                dp1[i][j] += dp0Prefix[i][j] + mod - dp0Prefix[i][j - Math.min(limit, j)];
                dp1[i][j] %= mod;


                dp0Prefix[i][j + 1] += dp0Prefix[i][j] + dp0[i][j];
                dp0Prefix[i][j + 1] %= mod;

                dp1Prefix[i + 1][j] += dp1Prefix[i][j] + dp1[i][j];
                dp1Prefix[i + 1][j] %= mod;

            }
        }

        long res = dp0[zero][one] + dp1[zero][one];

        return (int) (res % mod);
    }


}
