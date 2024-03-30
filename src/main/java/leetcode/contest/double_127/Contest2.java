package leetcode.contest.double_127;

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
//    public int minimumLevels(int[] possible) {
//        int n = possible.length;
//        int[] suffix = new int[n];
//
//        for (int i = n - 2; i >= 0; i--) {
//            suffix[i] = suffix[i + 1] + (possible[i + 1] == 0 ? -1 : possible[i + 1]);
//        }
//
//        int res = -1;
//        int curVal = 0;
//        for (int i = 0; i < n - 1; i++) {
//            curVal += (possible[i] == 0 ? -1 : possible[i]);
//
//            if (curVal > suffix[i]) {
//                res = i + 1;
//                break;
//            }
//        }
//
//        return res;
//    }

    public int minimumLevels(int[] possible) {
        int score1 = 0;
        int score2 = 0;
        for (int i = 0; i < possible.length; i++) {
            possible[i] = possible[i] == 0 ? -1 : 1;
            score2 += possible[i];
        }

        int res = -1;
        for (int i = 0; i < possible.length - 1; i++) {
            score1 += possible[i];
            score2 -= possible[i];

            if (score1 > score2) {
                res = i + 1;
                break;
            }
        }

        return res;
    }


}
