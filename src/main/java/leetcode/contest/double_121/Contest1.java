package leetcode.contest.double_121;

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
    public int missingInteger(int[] nums) {
        int total = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1] + 1) {
                break;
            }

            total += nums[i];
        }

        int[] vis = new int[10000];
        for (int num : nums) {
            vis[num] = 1;
        }

        for (; vis[total] == 1; total++) {

        }

        return total;
    }


}
