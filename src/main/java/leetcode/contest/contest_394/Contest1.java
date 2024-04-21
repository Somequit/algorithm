package leetcode.contest.contest_394;

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
    public int numberOfSpecialChars(String word) {
        int res = 0;
        int[] wordLowerCount = new int[26];
        int[] wordUpperCount = new int[26];

        for (char c : word.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                wordLowerCount[c - 'a']++;

            } else {
                wordUpperCount[c - 'A']++;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (wordLowerCount[i] > 0 && wordUpperCount[i] > 0) {
                res++;
            }
        }

        return res;
    }


}
