package leetcode.brushQuestions.hard;

import javafx.util.Pair;

import java.util.*;

/**
 * @author gusixue
 * @description 100178. 将数组分成最小总代价的子数组 II
 * @date 2024/1/21
 */
public class MinimumCost {

    public long minimumCost(int[] nums, int k, int dist) {
        // 存下标 i，按照 nums[i] 升序
        TreeSet<Pair<Integer, Integer>> treeSetIndex = new TreeSet<>((o1, o2) -> {
            if (!o1.getValue().equals(o2.getValue())) {
                return o1.getValue() - o2.getValue();

            } else {
                return o1.getKey() - o2.getKey();
            }
        });

        // 存下标 i，按照 nums[i] 升序
        Queue<Integer> heapIndex = new PriorityQueue<>((o1, o2) -> nums[o1] - nums[o2]);

        for (int i = 1; i < dist + 2; i++) {
            heapIndex.offer(i);
        }

        long curCost = nums[0];
        for (int i = 1; i < k; i++) {
            int index = heapIndex.poll();

            treeSetIndex.add(new Pair<>(index, nums[index]));
            curCost += nums[index];
        }
        long res = curCost;
        System.out.println(curCost + " : " + treeSetIndex + " : " + heapIndex);
        for (int i = dist + 2; i < nums.length; i++) {
            // 删除 nums[i-dist-1]，添加 nums[i]
            int indexDel = i - dist - 1;
            if (treeSetIndex.remove(new Pair<>(indexDel, nums[indexDel]))) {
                curCost -= nums[indexDel];
            }

            heapIndex.add(i);
            int minIndex = heapIndex.poll();
            while (!heapIndex.isEmpty() && minIndex <= indexDel) {
                minIndex = heapIndex.poll();
            }

            treeSetIndex.add(new Pair<>(minIndex, nums[minIndex]));
            curCost += nums[minIndex];

            if (treeSetIndex.size() > k - 1) {
                int maxIndex = treeSetIndex.last().getKey();
                treeSetIndex.remove(new Pair<>(maxIndex, nums[maxIndex]));
                heapIndex.add(maxIndex);
                curCost -= nums[maxIndex];
            }
            System.out.println(curCost + " : " + treeSetIndex + " : " + heapIndex);
            res = Math.min(res, curCost);
        }

        return res;
    }
}
