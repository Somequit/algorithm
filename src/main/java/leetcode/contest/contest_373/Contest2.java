package leetcode.contest.contest_373;

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
    private int solution(String s, int k) {
        int n = s.length();
        int res = 0;

        for (int i = 0; i < n; i++) {
            int v = 0;
            int c = 0;

            for (int j = i; j < n; j++) {
                if (isVowels(s.charAt(j))) {
                    v++;

                } else {
                    c++;
                }

                if (v == c && (v * c) % k == 0) {
                    res++;
                }
            }
        }

        return res;
    }

    private boolean isVowels(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }


}
