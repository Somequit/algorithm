package leetcode.contest.double_127;

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
    public int minimumSubarrayLength(int[] nums, int k) {
        int res = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int val = 0;
            for (int j = i; j < nums.length; j++) {
                val |= nums[j];
                if (val >= k) {
                    res = Math.min(res, j - i + 1);
                    break;
                }
            }
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }


}
