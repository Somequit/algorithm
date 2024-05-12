package leetcode.contest.contest_397;

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
    public int maximumEnergy(int[] energy, int k) {
        int res = Integer.MIN_VALUE;

        for (int i = energy.length - 1; i >= energy.length - k; i--) {
            int curEnergy = 0;

            for (int j = i; j >= 0; j -= k) {
                curEnergy += energy[j];
                res = Math.max(res, curEnergy);
            }

        }

        return res;
    }


}
