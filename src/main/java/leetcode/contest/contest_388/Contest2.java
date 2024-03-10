package leetcode.contest.contest_388;

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
    public long maximumHappinessSum(int[] happiness, int k) {
        Arrays.sort(happiness);

        int count = 0;
        long res = 0;
        for (int i = happiness.length - 1; count < k; i--) {
            res += (happiness[i] - count) > 0 ? (happiness[i] - count) : 0;
            count++;
        }

        return res;
    }


}
