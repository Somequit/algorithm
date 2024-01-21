package leetcode.contest.contest_381;

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
    public int minimumPushes(String word) {
        Integer[] wordCount = new Integer[26];
        Arrays.fill(wordCount, 0);
        for (int i = 0; i < word.length(); i++) {
            wordCount[word.charAt(i) - 'a']++;
        }

        Arrays.sort(wordCount, (o1, o2) -> o2.compareTo(o1));
//        System.out.println(Arrays.toString(wordCount));
        int res = 0;
        for (int i = 0; i < 26; i++) {
            res += wordCount[i] * (i / 8 + 1);
        }

        return res;
    }


}
