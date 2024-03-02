package leetcode.contest.double_125;

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
    public int minOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int res = 0;
        for (int num : nums) {
            if (num < k) {
                res++;

            } else {
                break;
            }
        }

        return res;
    }


}
