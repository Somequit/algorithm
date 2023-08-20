package leetcode.contest.contest_359;

import utils.AlgorithmUtils;

import java.util.List;


/**
 *
 */
public class Contest1 {

    public static void main(String[] args) {
        Contest1 contest = new Contest1();

        while (true) {
//            int[] nums = AlgorithmUtils.systemInArray();

//            int res = contest.solution(nums);
//            System.out.println(res);
        }

    }

    public boolean solution(List<String> words, String s) {
        if (words.size() != s.length()) {
            return false;
        }

        for (int i = 0; i < words.size(); i++) {
            if (s.charAt(i) != words.get(i).charAt(0)) {
                return false;
            }
        }
        return true;
    }


}
