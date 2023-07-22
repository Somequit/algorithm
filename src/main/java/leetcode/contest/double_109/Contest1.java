package leetcode.contest.double_109;

import utils.AlgorithmUtils;

import java.util.Arrays;


/**
 * @author gusixue
 * @date 2023/7/22
 */
public class Contest1 {

    public static void main(String[] args) {
        Contest1 contest = new Contest1();

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();

            boolean res = contest.solution(nums);
            System.out.println(res);
        }

    }

    private boolean solution(int[] nums) {
        if (nums.length == 1) {
            return false;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != i + 1) {
                return false;
            }
        }
        if (nums[nums.length - 1] != nums[nums.length - 2]) {
            return false;
        }
        return true;
    }



}
