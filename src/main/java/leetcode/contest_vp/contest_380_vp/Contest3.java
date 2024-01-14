package leetcode.contest_vp.contest_380_vp;

import java.util.*;
/**
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();
        System.out.println(contest.getCount(6, 1));

        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

//            int res = contest.solution(n, nums, list);
//            System.out.println(res);

        }

    }

    /**
     * Java + 二分答案 + 规律：
     * @return
     */
    public long findMaximumNumber(long k, int x) {
        long left = 1;
        long right = (1L << 50);

        long res = 1;
        while (left <= right) {
            long mid = ((right - left) >> 1) + left;
            long count = getCount(mid, x);
            if (count <= k) {
                res = mid;
                left = mid + 1;

            } else {
                right = mid - 1;
            }
        }

        return res;
    }

    private long getCount(long maxNum, int x) {
        long res = 0;
        for (int i = 1; i <= 63; i++) {
            if (i % x == 0) {
                res += (maxNum + 1) / (1L << i) * (1L << i - 1);
                res += Math.max((maxNum + 1) % (1L << i) - (1L << i - 1), 0);
            }
        }

        return res;
    }


}
