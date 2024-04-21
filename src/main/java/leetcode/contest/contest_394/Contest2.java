package leetcode.contest.contest_394;

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
    public int numberOfSpecialChars(String word) {
        int res = 0;
        int[] wordLowerLastIndex = new int[26];
        Arrays.fill(wordLowerLastIndex, Integer.MAX_VALUE);
        int[] wordUpperFirstIndex = new int[26];
        Arrays.fill(wordUpperFirstIndex, Integer.MAX_VALUE);

        int index = 0;
        for (char c : word.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                wordLowerLastIndex[c - 'a'] = index;

            } else {
                wordUpperFirstIndex[c - 'A'] = Math.min(wordUpperFirstIndex[c - 'A'], index);
            }

            index++;
        }

        for (int i = 0; i < 26; i++) {
            if (wordUpperFirstIndex[i] != Integer.MAX_VALUE && wordLowerLastIndex[i] != Integer.MAX_VALUE
                    && wordUpperFirstIndex[i] > wordLowerLastIndex[i]) {
                res++;
            }
        }

        return res;
    }


}
