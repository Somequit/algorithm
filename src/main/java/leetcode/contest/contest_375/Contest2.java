package leetcode.contest.contest_375;

import java.util.*;

/**
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

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
    public List<Integer> getGoodIndices(int[][] variables, int target) {
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < variables.length; i++) {
            if (checkGoodIndices(variables[i][0], variables[i][1], variables[i][2], variables[i][3], target)) {
                res.add(i);
            }
        }

        return res;
    }

    private boolean checkGoodIndices(int a, int b, int c, int m, int target) {
        // ((a ^ b % 10) ^ c) % m == target
        if (target == qPow(qPow(a, b, 10), c, m)) {
            return true;
        }
        return false;
    }

    private long qPow(long value, long pow, long mod) {
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


}
