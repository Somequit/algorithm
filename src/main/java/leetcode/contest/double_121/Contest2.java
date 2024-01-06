package leetcode.contest.double_121;

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
    public int minOperations(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < 20; i++) {
            int bit = 0;
            if ((k & (1 << i)) != 0) {
                bit = 1;
            }

            int bitCount = 0;
            for (int num : nums) {
                if ((num & (1 << i)) != 0) {
                    bitCount++;
                }
            }

            if (bitCount % 2 != bit) {
                res++;
            }
        }

        return res;
    }


}
