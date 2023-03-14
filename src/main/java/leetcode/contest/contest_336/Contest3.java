package leetcode.contest.contest_336;

import utils.AlgorithmUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 6317. 统计美丽子数组数目
 * 给你一个下标从 0 开始的整数数组nums 。每次操作中，你可以：
 * 选择两个满足 0 <= i, j < nums.length 的不同下标 i 和 j 。
 * 选择一个非负整数 k ，满足 nums[i] 和 nums[j] 在二进制下的第 k 位（下标编号从 0 开始）是 1 。
 * 将 nums[i] 和 nums[j] 都减去 2k 。
 * 如果一个子数组内执行上述操作若干次后，该子数组可以变成一个全为 0 的数组，那么我们称它是一个 美丽 的子数组。
 * 请你返回数组 nums 中 美丽子数组 的数目。
 * 子数组是一个数组中一段连续 非空 的元素序列。
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 106
 *
 * @author gusixue
 * @date 2023/3/12
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();

            long res = contest.solution(nums);
            System.out.println(res);
        }

    }

    /**
     * 题目转化下可得：连续子区间中，所有数转化为二进制后，每一位比特为 1 的个数是偶数，可以想到区间内所有数异或后得 0 就是美丽数，
     * 此时转化为：连续子区间内所有数异或后得 0 的个数，又因为异或满足：a ⊕ b ⊕ a = b，
     * 此时转化为：拿到前缀异或和 prefixSum[]，
     *  prefixSum[i] == 0 代表 [0, i] 是结果区间，就是 0 出现的次数，
     *  prefixSum[i] ⊕ prefixSum[j] = 0 代表 [i, j] 是结果区间，此时 prefixSum[i] == prefixSum[j]，就是任何数出现过两次，
     * 因此可以存储 prefixSum[] 中每个数出现的次数 num，然后使用排列组合的方式计算：num * (num - 1) / 2，加上 0 出现的次数，获得最终结果
     */
    private long solution(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        long res = 0L;
        // 前缀异或和每个数出现的次数（HashMap 初始化优化）
        Map<Integer, Integer> prefixSumNumMap = new HashMap<>(nums.length);

        int beauty = 0;
        for (int num : nums) {
            beauty ^= num;
            prefixSumNumMap.put(beauty, prefixSumNumMap.getOrDefault(beauty, 0) + 1);
        }
//        beautifulMap.forEach((k, v) -> {System.out.println(k + ":" + v);});

        for (Map.Entry<Integer, Integer> prefixSumNumEntry : prefixSumNumMap.entrySet()) {
            int prefixSum = prefixSumNumEntry.getKey();
            // 避免 int 计算溢出
            long num = prefixSumNumEntry.getValue();

            res += num * (num - 1) / 2;
            if (prefixSum == 0) {
                res += num;
            }
        }

        return res;
    }

}
