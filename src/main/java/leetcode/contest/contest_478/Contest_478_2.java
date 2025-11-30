package leetcode.contest.contest_478;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/11/30 10:24 上午
 */
public class Contest_478_2 {
    public static void main(String[] args) {
        Contest_478_2 contest = new Contest_478_2();

        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

//            int res = contest.solution(n, nums, list);
//            System.out.println(res);
        }

    }

    public int maxDistinct(String s) {
        int res = 0;
        boolean[] sCnt = new boolean[26];
        for (char c : s.toCharArray()) {
            if (!sCnt[c - 'a']) {
                res++;
                sCnt[c - 'a'] = true;
            }
        }
        return res;
    }


}
