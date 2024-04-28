package leetcode.contest.contest_395;

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
    public int minimumAddedInteger(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        for (int i = 2; i >= 0; i--) {
            int x = nums2[0] - nums1[i];
            if (doMinimumAddedInteger(nums1, nums2, x)) {
                return x;
            }
        }

        return 0;
    }

    private boolean doMinimumAddedInteger(int[] nums1, int[] nums2, int x) {
        int j = 0;

        for (int i = 0; i < nums1.length && j < nums2.length; i++) {
            if (nums1[i] + x == nums2[j]) {
                j++;
            }
        }

        return j == nums2.length;
    }


}
