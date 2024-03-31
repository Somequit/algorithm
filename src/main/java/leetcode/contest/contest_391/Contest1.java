package leetcode.contest.contest_391;

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
    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int res = -1;

        int val = 0;
        String xStr = x + "";
        for (int i = 0; i < xStr.length(); i++) {
            val += xStr.charAt(i) - '0';
        }

        if (x % val == 0) {
            res = val;
        }
        return res;
    }


}
