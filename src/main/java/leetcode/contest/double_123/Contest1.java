package leetcode.contest.double_123;

import java.util.Arrays;

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
    public String triangleType(int[] nums) {
        Arrays.sort(nums);

        if (nums[0] + nums[1] <= nums[2]) {
            return "none";
        }

        if (nums[0] == nums[1] && nums[2] == nums[1]) {
            return "equilateral";

        } else if (nums[0] == nums[1] || nums[2] == nums[1]) {
            return "isosceles";

        } else {
            return "scalene";
        }
    }


}
