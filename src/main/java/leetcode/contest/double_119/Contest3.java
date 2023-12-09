package leetcode.contest.double_119;


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
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> numCountMap = new HashMap<>();

        int res = 1;

        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = numCountMap.getOrDefault(nums[i], 0) + 1;
            numCountMap.put(nums[i], count);

            while (left < i && numCountMap.get(nums[i]) > k) {
                numCountMap.put(nums[left], numCountMap.get(nums[left]) - 1);
                left++;
            }

            res = Math.max(res, i - left + 1);

        }

        return res;
    }


}
