package leetcode.contest.contest_391;

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
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int res = numBottles;

        int cur = numBottles;
        while (cur >= numExchange) {
            cur -= numExchange;
            res++;
            cur++;
            numExchange++;
        }

        return res;
    }


}
