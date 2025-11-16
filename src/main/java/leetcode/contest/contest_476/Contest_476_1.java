package leetcode.contest.contest_476;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/11/15 10:37 下午
 */
public class Contest_476_1 {

    public static void main(String[] args) {
        leetcode.Contest1 contest = new leetcode.Contest1();

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
    public int maximizeExpressionOfThree(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return nums[n - 1] + nums[n - 2] - nums[0];
    }


}
