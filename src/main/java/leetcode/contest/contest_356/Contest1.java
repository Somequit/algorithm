package leetcode.contest.contest_356;

import utils.AlgorithmUtils;


/**
 *
 */
public class Contest1 {

    public static void main(String[] args) {
        Contest1 contest = new Contest1();

        while (true) {
            int[] hours = AlgorithmUtils.systemInArray();
            int target = AlgorithmUtils.systemInNumberInt();

            int res = contest.solution(hours, target);
            System.out.println(res);
        }

    }

    public int solution(int[] hours, int target) {
        int res = 0;

        for (int i = 0; i < hours.length; i++) {
            if (hours[i] >= target) {
                res++;
            }
        }

        return res;
    }


}
