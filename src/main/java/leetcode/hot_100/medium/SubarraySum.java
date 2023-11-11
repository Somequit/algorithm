package leetcode.hot_100.medium;

import utils.AlgorithmUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gusixue
 * @description
 * 560. 和为 K 的子数组
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的连续子数组的个数 。
 * 子数组是数组中元素的连续非空序列。
 *
 * 1 <= nums.length <= 2 * 10 ^ 4
 * -1000 <= nums[i] <= 1000
 * -10 ^ 7 <= k <= 10 ^ 7
 * @date 2023/9/19
 */
public class SubarraySum {

    public static void main(String[] args) {
        SubarraySum subarraySum = new SubarraySum();
        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
            int k = AlgorithmUtils.systemInNumberInt();
            
            int res = subarraySum.solution(nums, k);
            System.out.println(res);
        }
    }

    /**
     * 使用前缀和的区间减法，循环 nums 数组记录 [0,i] 中 prefix 数出现的次数 countMap[prefix]，
     * 然后使用 prefixCur-prefixPrev=k 转化为 prefixCur-k=prefixPrev，
     * 代表每次结果加上 countMap[prefixCur-k]，代表前缀中出现 prefixCur-k 的个数，也就是能组成区间和为 k 的个数
     * 注意需要初始化 countMap[0]=1，然后每次加完后设置 countMap[prefix]++
     * 时间复杂度：O（n），空间复杂度：O（n）
     */
    private int solution(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>(nums.length << 1);
        countMap.put(0, 1);

        int res = 0;
        int prefix = 0;
        for (int num : nums) {
            prefix += num;
            res += countMap.getOrDefault(prefix-k, 0);
            countMap.put(prefix, countMap.getOrDefault(prefix, 0) + 1);
        }

        return  res;
    }
}
