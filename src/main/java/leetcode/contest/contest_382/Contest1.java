package leetcode.contest.contest_382;

import java.util.*;
/**
 */
public class Contest1 {

    public static void main(String[] args) {
        Contest1 contest = new Contest1();

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
    public int countKeyChanges(String s) {
        int res = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) - s.charAt(i - 1) != 0 && s.charAt(i) - s.charAt(i - 1) != ('a' - 'A')
                    && s.charAt(i) - s.charAt(i - 1) != ('A' - 'a')) {
                res++;
            }
        }
        return res;
    }


}
