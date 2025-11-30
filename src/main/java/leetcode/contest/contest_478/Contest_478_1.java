package leetcode.contest.contest_478;

import java.util.*;


/**
 * @author gusixue
 * @description
 * @date 2025/11/30 10:24 上午
 */
public class Contest_478_1 {
    public static void main(String[] args) {
        Contest_478_1 contest = new Contest_478_1();

        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

//            int res = contest.solution(n, nums, list);
//            System.out.println(res);
        }

    }

    public int countElements(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = n - k - 1; i >= 0; i--) {
            if (i == n - 1 || nums[i] != nums[i + 1]) {
                return i + 1;
            }
        }

        return 0;
    }

}
