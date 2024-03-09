package leetcode.brushQuestions.hard;

import javafx.util.Pair;

import java.util.*;

/**
 * @author gusixue
 * @description 2386. 找出数组的第 K 大和
 * @date 2024/3/9
 */
public class KSum {

    /**
     * sum = 和的最大值，需要求 sum - |nums[i..]| 的第 K 大，转化为 |nums[i..]| 的第 K 小
     */
    public long kSum(int[] nums, int k) {
        // 和的最大值
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                sum += nums[i];

            } else {
                nums[i] = -nums[i];
            }
        }

        // 转化为 |nums[i..]| 的第 K 小
        Arrays.sort(nums);

        long minKSum = 0;
        // sumNums - nextIndex(写一个可添加/替换的下标)
        Queue<Pair<Long, Integer>> smallestHeap = new PriorityQueue<>(Comparator.comparing(Pair::getKey));
        // 最小值为空即 0
        smallestHeap.offer(new Pair<>(0L, 0));

        int count = 0;
        while (!smallestHeap.isEmpty()) {
            Pair<Long, Integer> curPair = smallestHeap.poll();

            minKSum = curPair.getKey();
            int nextIndex = curPair.getValue();

            if (nextIndex < nums.length) {
                // 要么加上 nums[i]
                smallestHeap.offer(new Pair<>(minKSum + nums[nextIndex], nextIndex + 1));
                if (nextIndex > 0) {
                    // 要么将 nums[i-1] 替换为 nums[i]
                    smallestHeap.offer(new Pair<>(minKSum + nums[nextIndex] - nums[nextIndex - 1], nextIndex + 1));
                }
            }

            count++;
            if (count == k) {
                break;
            }
        }

        return sum - minKSum;
    }
}
