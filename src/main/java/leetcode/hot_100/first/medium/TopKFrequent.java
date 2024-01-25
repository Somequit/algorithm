package leetcode.hot_100.first.medium;

import utils.AlgorithmUtils;

import java.util.*;

/**
 * @author gusixue
 * @description
 * 347. 前 K 个高频元素
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * 1 <= nums.length <= 10 ^ 5
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 * @date 2023/9/22
 */
public class TopKFrequent {

    public static void main(String[] args) {
        TopKFrequent topKFrequent = new TopKFrequent();
        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
            int k = AlgorithmUtils.systemInNumberInt();

            int[] res = topKFrequent.solution(nums, k);
            System.out.println(Arrays.toString(res));
        }
    }

    /**
     * HashMap 统计每种元素的个数，遍历 Map 将个数放入 List 数组的下标（空间开个数的最大值）、值放到对应 List 中，
     * 最后倒序遍历 List、每次 k 将去对应下标的 List 长度，直到为 0（不会重复结果、代表一定不会减到负数）
     * maxCount 代表某种元素最大的个数，，时间复杂度：O（maxCount+n）；空间复杂度：O（maxCount+n）
     */
    private int[] solution(int[] nums, int k) {
        // 判空
        if (nums == null || nums.length <= 0) {
            return new int[0];
        }

        // 最大的个数
        int maxCount = 0;
        // 统计每种元素个数
        Map<Integer, Integer> countMap = new HashMap<>(nums.length << 1);
        for (int num : nums) {
            int curCount = countMap.getOrDefault(num, 0) + 1;
            countMap.put(num, curCount);

            maxCount = Math.max(maxCount, curCount);
        }

        // List 数组统计 个数-零到多个元素
        List<Integer>[] countNumList = new List[maxCount + 1];
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (countNumList[entry.getValue()] == null) {
                countNumList[entry.getValue()] = new ArrayList<>();
            }
            countNumList[entry.getValue()].add(entry.getKey());
        }

        int[] res = new int[k];
        // 倒序遍历 List 数组最多 k 个
        for (int i = maxCount, resIndex = 0; i >= 0; i--) {
            // 注意 List 数组没有每个位置均赋值
            if (countNumList[i] == null) {
                continue;
            }

            k -= countNumList[i].size();

            for (int j = 0; j < countNumList[i].size(); j++) {
                res[resIndex] = countNumList[i].get(j);
                resIndex++;
            }

            if (k == 0) {
                break;
            }
        }

        return res;
    }
}
