package leetcode.contest.contest_474;

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
    public long minimumTime(int[] d, int[] r) {
        long left = 2;
        long right = (long)1e18;
//        System.out.println(right);

        long res = right;
        while (left <= right) {
            long mid = (left + right) / 2;

            if (checkMinimumTime(mid, d, r)) {
                res = mid;
                right = mid - 1;

            } else {
                left = mid + 1;
            }
        }

        return res;
    }

    private boolean checkMinimumTime(long mid, int[] d, int[] r) {
        long dronesNot1 = mid / r[0];
        long dronesNot2 = mid / r[1];
        long dronesNot = mid / lcm(r[0], r[1]);

        long dronsOnly1 = dronesNot2 - dronesNot;
        long dronsOnly2 = dronesNot1 - dronesNot;
        long dronsAll = mid - dronsOnly1 - dronsOnly2 - dronesNot;
        return Math.max(0, d[0] - dronsOnly1) + Math.max(0, d[1] - dronsOnly2) <= dronsAll;
    }

    private static long gcd(long a,long b){
        while (a != 0) {
            long tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }

    // 先除后乘，避免溢出
    private static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
}
