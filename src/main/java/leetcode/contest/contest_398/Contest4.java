package leetcode.contest.contest_398;

import utils.AlgorithmUtils;

import java.util.*;
/**
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
            int k = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

            int res = contest.waysToReachStair(k);
            System.out.println(res);
        }

    }

    /**
     * @return
     */
    public int waysToReachStair(int k) {
        if (k == 0) {
            return 2;
        }
        if (k == 1) {
            return 4;
        }

        List<Integer> listStair = new ArrayList<>();
        for (int j = 2; j < 1_000_000_040; j *= 2) {
            listStair.add(j - 1);
        }

//        System.out.println(listStair);

        int res = 0;
        k--;
        for (int i = 0; i < listStair.size(); i++) {
            int maxStep = listStair.get(i);
            int minStep = maxStep - i - 2;
            if (minStep > k || k > maxStep) {
                continue;
            }

            if (k == minStep || k == maxStep) {
                res++;
                continue;
            }

            int negStair = k - minStep;
            int posStair = i + 1;

            res += comb(posStair + 1, negStair);
        }

        return res;
    }


    // 组合数模板
    private static final int MOD = (int)1e9+7;
    private static final int MX = 100;
    // 阶乘
    private static final long[] FAC = new long[MX];
    // 阶乘的逆元
    private static final long[] INV_FAC = new long[MX];

    static {
        FAC[0] = 1;
        for (int i = 1; i < MX; i++) {
            FAC[i] = FAC[i - 1] * i % MOD;
        }

        INV_FAC[MX - 1] = qPow(FAC[MX - 1], MOD - 2, MOD);
        for (int i = MX - 1; i > 0; i--) {
            INV_FAC[i - 1] = INV_FAC[i] * i % MOD;
        }
    }

    private static long qPow(long value, long pow, long mod) {
        long res = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) {
                res *= value;
                res %= mod;
            }

            value *= value;
            value %= mod;
            pow >>= 1;
        }
        return res;
    }

    private static long comb(int n, int k) {
        return FAC[n] * INV_FAC[k] % MOD * INV_FAC[n - k] % MOD;
    }


}
