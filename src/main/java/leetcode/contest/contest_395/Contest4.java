package leetcode.contest.contest_395;

import java.util.*;
/**
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

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
    public int medianOfUniquenessArray(int[] nums) {
        int left = 1;
        int right = nums.length;
        int res = 1;

        int n = nums.length;
        long midCount = ((long) n * (n + 1) / 2 + 1) / 2;
//        System.out.println(midCount);
        while (left <= right) {
            int mid = (left + right) / 2;

            long count = doLetterThanCount(nums, mid);
            if(count > midCount) {
                right = mid - 1;
                res = mid;

            } else if (count < midCount) {
                left = mid + 1;

            } else {
                res = mid;
                break;
            }
        }

        return res;
    }

    private long doLetterThanCount(int[] nums, int target) {
        int[] arrCounts = new int[100_001];
        int arrCount = 0;

        long res = 0;
        for (int left = 0, right = 0; right < nums.length; right++) {
            if (arrCounts[nums[right]] == 0) {
                arrCount++;
            }
            arrCounts[nums[right]]++;

            while (arrCount > target) {
                if (arrCounts[nums[left]] == 1) {
                    arrCount--;
                }
                arrCounts[nums[left]]--;
                left++;

            }

            res += right - left + 1;
        }

//        System.out.println(target + " : " + res);

        return res;
    }


}
