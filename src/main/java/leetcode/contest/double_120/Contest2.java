package leetcode.contest.double_120;

import java.util.*;
/**
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

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
    public long largestPerimeter(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        long[] prefix = new long[n];
        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }

        long res = -1;
        for (int i = n - 1; i >= 2; i--) {
            if (nums[i] < prefix[i-1]) {
                res = prefix[i];
                break;
            }
        }

        return res;
    }


}
