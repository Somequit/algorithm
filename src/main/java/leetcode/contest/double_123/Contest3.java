package leetcode.contest.double_123;

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
    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;

        // 下一个元素 - 0到上一个元素的区间和
        Map<Integer, Long> numSumMap = new HashMap<>();
        numSumMap.put(nums[0], 0L);

        long res = Long.MIN_VALUE;
        long prefixSum = nums[0];
        for (int i = 1; i < n; i++) {
            int preNum1 = nums[i] - k;
            int preNum2 = k + nums[i];
            if (numSumMap.containsKey(preNum1) && numSumMap.containsKey(preNum2)) {
                res = Math.max(res, prefixSum + nums[i] - Math.min(numSumMap.get(preNum1), numSumMap.get(preNum2)));

            } else if (numSumMap.containsKey(preNum1)) {
                res = Math.max(res, prefixSum + nums[i] - numSumMap.get(preNum1));

            } else if (numSumMap.containsKey(preNum2)) {
                res = Math.max(res, prefixSum + nums[i] - numSumMap.get(preNum2));
            }

            if (numSumMap.containsKey(nums[i])) {
                numSumMap.put(nums[i], Math.min(prefixSum, numSumMap.get(nums[i])));

            } else {
                numSumMap.put(nums[i], prefixSum);
            }

            prefixSum += nums[i];
        }

        return res == Long.MIN_VALUE ? 0 : res;
    }


}
