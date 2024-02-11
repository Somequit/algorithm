package leetcode.contest.contest_384;

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
    public int maxPalindromesAfterOperations(String[] words) {
        int[] word = new int[26];

        int n = words.length;
        int[] count = new int[n];

        for (int i = 0; i < n; i++) {
            count[i] = words[i].length();

            for (int j = 0; j < words[i].length(); j++) {
                word[words[i].charAt(j) - 'a']++;
            }
        }

        Arrays.sort(count);
        Arrays.sort(word);

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (count[i] % 2 == 1) {

                for (int j = 0; j < 26; j++) {
                    if (word[j] % 2 == 1) {
                        word[j]--;
                        count[i]--;
                        break;
                    }
                }
            }

            for (int j = 0; j < 26; j++) {
                if (word[j] >= count[i]) {
                    word[j] -= count[i];
                    count[i] = 0;
                    res++;
                    break;

                } else {
                    count[i] -= word[j] / 2 * 2;
                    word[j] %= 2;
                }
            }
        }

        return res;
    }


}
