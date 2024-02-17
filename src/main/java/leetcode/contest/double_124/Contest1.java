package leetcode.contest.double_124;

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
    public int maxOperations(int[] nums) {
        int res = 1;
        int score = nums[0] + nums[1];

        for (int i = 3; i < nums.length; i += 2) {
            if (score == nums[i - 1] + nums[i]) {
                res++;

            } else {
                break;
            }
        }

        return res;
    }


}
