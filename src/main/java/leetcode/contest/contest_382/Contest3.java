package leetcode.contest.contest_382;

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
    public long flowerGame(int n, int m) {
        long res = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 2 != 0) {
                res += m / 2;

            } else {
                res += (m + 1) / 2;
            }
        }

        return res;
    }


}
