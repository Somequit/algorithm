package leetcode.contest.contest_396;

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
    public boolean isValid(String word) {
        if (word.length() < 3) {
            return false;
        }

        boolean res = true;
        int aeiou = 0;
        int notAeiou = 0;
        for (char c : word.toCharArray()) {
            if (c == '@' || c == '#' || c == '$') {
                res = false;
                break;
            }

            if (c >= 'a' && c <= 'z') {
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    aeiou++;

                } else {
                    notAeiou++;
                }

            } else if (c >= 'A' && c <= 'Z') {
                if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                    aeiou++;

                } else {
                    notAeiou++;
                }
            }

        }

        return res && aeiou > 0 && notAeiou > 0;
    }


}
