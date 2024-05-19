package leetcode.contest.contest_398;

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
    public boolean isArraySpecial(int[] nums) {
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            if (nums[i] % 2 == nums[i - 1] % 2) {
                return false;
            }
        }

        return true;
    }


}
