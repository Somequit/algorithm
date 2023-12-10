package leetcode.contest.contest_375;

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
    public long countSubarrays(int[] nums, int k) {
        int maxNum = 0;
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
        }

        int n = nums.length;
        List<Integer> maxNumIndex = new ArrayList<>();
        maxNumIndex.add(-1);
        for (int i = 0; i < n; i++) {
            if (nums[i] == maxNum) {
                maxNumIndex.add(i);
            }
        }

        long res = 0;

        for (int i = k; i < maxNumIndex.size(); i++) {
            res += (long) (maxNumIndex.get(i - k + 1) - maxNumIndex.get(i - k)) * (n - maxNumIndex.get(i));
        }

        return res;
    }


}
