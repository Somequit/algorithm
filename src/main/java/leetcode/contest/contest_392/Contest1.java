package leetcode.contest.contest_392;

import java.util.*;
import java.util.stream.Collectors;

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
    public int longestMonotonicSubarray(int[] nums) {
        int res = 1;

        int left = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                res = Math.max(res, i - left + 1);

            } else {
                left = i;
            }
        }

        left = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                res = Math.max(res, i - left + 1);

            } else {
                left = i;
            }
        }

        return res;
    }


}
