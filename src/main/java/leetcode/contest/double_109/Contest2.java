package leetcode.contest.double_109;

import utils.AlgorithmUtils;


/**
 * @author gusixue
 * @date 2023/7/22
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
            String s = AlgorithmUtils.systemInString();

            String res = contest.solution(s);
            System.out.println(res);
        }

    }

    private String solution(String s) {
        StringBuilder t = new StringBuilder();
        int[] vowelNum = new int[10];
        char[] vowel = new char[]{'A','E','I','O','U','a','e','i','o','u'};

        for (int i = 0; i < s.length(); i++) {
            int index = vowelIndex(s.charAt(i), vowel);
            if (index >= 0) {
                vowelNum[index]++;
            }
        }

        StringBuilder vowelTemp = new StringBuilder();
        for (int i = 0; i < vowelNum.length; i++) {
            for (int j = 0; j < vowelNum[i]; j++) {
                vowelTemp.append(vowel[i]);
            }
        }
        System.out.println(vowelTemp);

        for (int i = 0; i < s.length(); i++) {
            int index = vowelIndex(s.charAt(i), vowel);
            if (index >= 0) {
                t.append(vowelTemp.charAt(0));
                vowelTemp.deleteCharAt(0);
            } else {
                t.append(s.charAt(i));
            }
        }

        return t.toString();
    }

    private int vowelIndex(char c, char[] vowel) {
        for (int i = 0; i < vowel.length; i++) {
            if (c == vowel[i]) {
                return i;
            }
        }
        return -1;
    }


}
