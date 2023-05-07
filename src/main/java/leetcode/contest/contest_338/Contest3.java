package leetcode.contest.contest_338;


import utils.AlgorithmUtils;

import java.util.*;

/**
 * 6357. 使数组元素全部相等的最少操作次数
 * 给你一个正整数数组 nums 。
 * 同时给你一个长度为 m 的整数数组 queries 。第 i 个查询中，你需要将 nums 中所有元素变成 queries[i] 。你可以执行以下操作 任意 次：
 * 将数组里一个元素 增大 或者 减小 1 。
 * 请你返回一个长度为 m 的数组 answer ，其中 answer[i]是将 nums 中所有元素变成 queries[i] 的 最少 操作次数。
 * 注意，每次查询后，数组变回最开始的值。
 *
 * n == nums.length
 * m == queries.length
 * 1 <= n, m <= 105
 * 1 <= nums[i], queries[i] <= 109
 *
 * @author gusixue
 * @date 2023/3/19
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
            int[] queries = AlgorithmUtils.systemInArray();

            List<Long> res = contest.minOperations(nums, queries);
            System.out.println(res);
        }

    }

    /**
     * 排序 nums 后求出前后缀和，二分找到小于与不小于 queries[i] 的个数，此时通过前后缀和可快速求出两段总差值，就是结果
     * 注意如果所有数均大于 queries[i]，以及均小于 queries[i] 特殊解决
     * 时间复杂度：O（（n + q） logn），空间复杂度：O（n）
     *
     */
    public List<Long> minOperations(int[] nums, int[] queries) {

        Arrays.sort(nums);
//        System.out.println(Arrays.toString(nums));

        long[] prefixSum = getPrefixSum(nums);
//        System.out.println(Arrays.toString(prefixSum));

        long[] suffixSum = getSuffixSum(nums);
//        System.out.println(Arrays.toString(suffixSum));

        List<Long> answer = calAnswer(nums, queries, prefixSum, suffixSum);

        return answer;
    }

    private List<Long> calAnswer(int[] nums, int[] queries, long[] prefixSum, long[] suffixSum) {
        List<Long> answer = new ArrayList<>();
        int queLen = queries.length;
        int numsLen = nums.length;

        for (int i = 0; i < queLen; i++) {

            if (nums[0] >= queries[i]) {
                answer.add(suffixSum[0] - (long)queries[i] * numsLen);
//                System.out.println(nums[0]);

            } else if (nums[numsLen - 1] <= queries[i]) {
                answer.add((long)queries[i] * numsLen - prefixSum[numsLen - 1]);
//                System.out.println(nums[numsLen - 1]);

            } else {
                int prefixIndex = binarySearchMin(nums, queries[i]);
//                System.out.println(prefixIndex);

                long prefixAnswer = (long)queries[i] * (prefixIndex + 1) - prefixSum[prefixIndex];
                long suffixAnswer = suffixSum[prefixIndex + 1] - (long)queries[i] * (numsLen - prefixIndex - 1);

//                System.out.println(prefixSum[prefixIndex] + ":" + (prefixIndex + 1) + ":" + prefixAnswer);
//                System.out.println(suffixSum[prefixIndex + 1] + ":" + (numsLen - prefixIndex - 1) + ":" + suffixAnswer);

                answer.add(prefixAnswer + suffixAnswer);
            }

//            System.out.println();
        }

        return answer;
    }

    /**
     * nums 数组中小于等于 query 的最大下标
     * 如果均大于 query 返回 0
     */
    public int binarySearchMin(int[] nums, int query) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            // 避免 left + right 越界
            int mid = ((right + 1 - left) >> 1) + left;
            if (nums[mid] > query) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }

    private long[] getSuffixSum(int[] nums) {
        int numsLen = nums.length;
        long[] suffixSum = new long[numsLen];
        suffixSum[numsLen - 1] = nums[numsLen - 1];

        for (int i = numsLen - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + nums[i];
        }

        return suffixSum;
    }

    private long[] getPrefixSum(int[] nums) {
        int numsLen = nums.length;
        long[] prefixSum = new long[numsLen];
        prefixSum[0] = nums[0];

        for (int i = 1; i < numsLen; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }

        return prefixSum;
    }


}
