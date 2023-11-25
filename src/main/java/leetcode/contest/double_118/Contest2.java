package leetcode.contest.double_118;

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
    private int solution(int n, int m, int[] hBars, int[] vBars) {
        Arrays.sort(hBars);
        int hMax = getContinuousMax(hBars);

        Arrays.sort(vBars);
        int vMax = getContinuousMax(vBars);

        return (Math.min(hMax, vMax) + 1) * (Math.min(hMax, vMax) + 1);
    }

    private int getContinuousMax(int[] bars) {
        int res = 1;
        int temp = 1;
        for (int i = 1; i < bars.length; i++) {
            if (bars[i] - 1 == bars[i - 1]) {
                temp++;

            } else {
                temp = 1;
            }
            res = Math.max(res, temp);
        }
        return res;
    }


}
