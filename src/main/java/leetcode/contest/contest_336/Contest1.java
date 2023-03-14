package leetcode.contest.contest_336;

import utils.AlgorithmUtils;


/**
 * @author gusixue
 * @date 2023/3/12
 */
public class Contest1 {

    public static void main(String[] args) {
        Contest1 contest = new Contest1();

        while (true) {
            int num = AlgorithmUtils.systemInNumberInt();
            String[] words = new String[num];
            for (int i = 0; i < num; i++) {
                words[i] = AlgorithmUtils.systemInString();
            }
            int left = AlgorithmUtils.systemInNumberInt();
            int right = AlgorithmUtils.systemInNumberInt();

            int res = contest.solution(words, left, right);
            System.out.println(res);
        }

    }

    private int solution(String[] words, int left, int right) {
        int res = 0;
        if (words == null) {
            return 0;
        }

        int leftI = Math.max(0, left);
        int rightI = Math.min(right + 1, words.length);
        for (int i = leftI; i < rightI; i++) {
            if (isVowel(words[i].substring(0, 1)) && isVowel(words[i].substring(words[i].length() - 1))) {
                res++;
            }
        }
        return res;
    }

    private boolean isVowel(String substring) {
        if (substring.equals("a")
                || substring.equals("e")
                || substring.equals("i")
                || substring.equals("o")
                || substring.equals("u")) {
            return true;
        }
        return false;
    }
}
