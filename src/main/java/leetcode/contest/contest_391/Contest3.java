package leetcode.contest.contest_391;

import java.util.*;
/**
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

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
    public long countAlternatingSubarrays(int[] nums) {
        long res = 0;

        for (int left = 0, right = 0; right < nums.length; right++) {
            if (right > 0 && nums[right] == nums[right - 1]) {
                left = right;
            }
            res += right - left + 1;
        }

        return res;
    }


}
