package leetcode.brushQuestions.medium;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author gusixue
 * @description 3346. 执行操作后元素的最高频率 I
 * @date 2025/10/21 3:22 上午
 */
public class MaxFrequency {

    /**
     * 遍历[min(nums), max(nums)]中每个数 i（不一定在 nums 中），
     * 二分找到 i-k 到 i+k 在 nums 中的个数 减去 i 的个数，在与 numOperations 求最小值加 i 的个数就是改成 i 的频率
     * 可用前缀个数和将 nums 个数存入 TreeMap 中
     * 优化：可看做形成了 max(nums)-min(nums) 个 [i-k, i+k] 的滑动窗口，
     *     此时如果 i 不在 nums 中，任何窗口都可以左滑/右滑使得 i+k/i-k 变成 nums 中的某个元素，此时窗口中不会减少数字，因此结果不会变小，此时中心为 nums[i]-k/nums[i]+k,
     *     否则因为有 numOperations 牵扯，区间内总个数虽然不表少，但是改变的数字会变多，因为中心点 i 不需要改变，
     *     因此仅需要对 nums[i] 和 nums[i]-k/nums[i]+k 作为中心点求滑动窗口
     */
    public int maxFrequency(int[] nums, int k, int numOperations) {
        Map<Integer, Integer> numMap = new HashMap<>();
        TreeMap<Integer, Integer> prefixCountTreeMap = new TreeMap<>();
        // 避免出现 null
        prefixCountTreeMap.put(Integer.MIN_VALUE, 0);
        for (int num : nums) {
            prefixCountTreeMap.merge(num, 1, Integer::sum);
            numMap.merge(num, 1, Integer::sum);
            numMap.merge(num - k, 0, Integer::sum);
//            numMap.merge(num + k, 0, Integer::sum);
        }
        int prefixCount = 0;
        for (Map.Entry<Integer, Integer> entry : prefixCountTreeMap.entrySet()) {
            prefixCount += entry.getValue();
            prefixCountTreeMap.put(entry.getKey(), prefixCount);
        }
//        System.out.println(prefixCountTreeMap);

        int res = 0;
        for (Map.Entry<Integer, Integer> entry : numMap.entrySet()) {
            int curNum = entry.getKey();
            int curCount = entry.getValue();
            int end = prefixCountTreeMap.floorKey(curNum + k);
            int begin = prefixCountTreeMap.lowerKey(curNum - k);
            res = Math.max(res, Math.min(numOperations, prefixCountTreeMap.get(end) - prefixCountTreeMap.get(begin) - curCount) + curCount);
//            System.out.println(i + " : " + curCount + " : " + end + " : " + begin);
        }

        return res;
    }

}
