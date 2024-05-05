package leetcode.contest.contest_396;

import java.util.*;
import java.util.stream.Collectors;

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
    public boolean isValid1(String word) {
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

    public boolean isValid(String word) {
        if (word.length() < 3) {
            return false;
        }

        Set<Character> setLowerVowel = Arrays.stream(new Character[]{'a', 'e', 'i', 'o', 'u'}).collect(Collectors.toSet());
        Set<Character> setUpperVowel = Arrays.stream(new Character[]{'A','E','I','O','U'}).collect(Collectors.toSet());
        Set<Character> setLowerNotVowel = Arrays.stream(new Character[]{'b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','y','z'}).collect(Collectors.toSet());
        Set<Character> setUpperNotVowel = Arrays.stream(new Character[]{'B','C','D','F','G','H','J','K','L','M','N','P','Q','R','S','T','V','W','X','Y','Z'}).collect(Collectors.toSet());
        Set<Character> setNums = Arrays.stream(new Character[]{'0','1','2','3','4','5','6','7','8','9'}).collect(Collectors.toSet());

        boolean res = true;
        boolean vowel = false;
        boolean notVowel = false;
        for (char c : word.toCharArray()) {
            if (setNums.contains(c)) {
                continue;

            } else if (setLowerVowel.contains(c) || setUpperVowel.contains(c)) {
                vowel = true;

            } else if (setLowerNotVowel.contains(c) || setUpperNotVowel.contains(c)) {
                notVowel = true;

            } else {
                res = false;
                break;
            }
        }

        return res && vowel && notVowel;
    }


}
