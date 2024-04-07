package leetcode.contest.contest_392;

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
    public long minOperationsToMakeMedianK(int[] nums, int k) {
        Arrays.sort(nums);

        int mid = nums.length / 2;
        if (nums[mid] == k) {
            return 0;
        }

        long res = 0;
        if (nums[mid] > k) {
            for (int i = mid; i >= 0; i--) {
                if (nums[i] > k) {
                    res += nums[i] - k;
                }
            }
        } else {
            for (int i = mid; i < nums.length; i++) {
                if (nums[i] < k) {
                    res += k - nums[i];
                }
            }
        }

        return res;
    }


}
