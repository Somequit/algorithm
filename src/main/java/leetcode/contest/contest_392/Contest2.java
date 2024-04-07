package leetcode.contest.contest_392;

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
    public String getSmallestString(String s, int k) {
        StringBuilder res = new StringBuilder();

        for (char c : s.toCharArray()) {
            for (char c1 = 'a'; c1 <= c; c1++) {
                int dis = distance(c - 'a', c1 - 'a');
                if (dis <= k) {
                    res.append(c1);
                    k -= dis;
                    break;
                }
            }
        }

        return res.toString();
    }

    private int distance(int c, int c1) {
        return Math.min(c - c1, c1 + 26 - c);
    }


}
