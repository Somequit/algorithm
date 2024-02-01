package leetcode.brushQuestions.hard;

import java.util.*;

/**
 * @author gusixue
 * @description LCP 24. 数字游戏
 * @date 2024/2/1
 */
public class NumsGame {

    /**
     * 转化：nums[i]-i 需要转化为相同值的最小代价，相同值为中位数，使用对顶堆解决
     */
    public int[] numsGame(int[] nums) {
        int mod = 1_000_000_007;

        // 大顶堆存放较小的一半
        Queue<Integer> leftHeap = new PriorityQueue<>((o1, o2) -> o2.compareTo(o1));
        long leftHeapSum = 0;

        // 小顶堆存放较大的一半
        Queue<Integer> rightHeap = new PriorityQueue<>((o1, o2) -> o1.compareTo(o2));
        long rightHeapSum = 0;

        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i] - i;

            // 每个值先放小顶堆，再将小顶堆的堆顶元素放入大顶堆（保证每个元素可以进入俩堆进行处理）
            rightHeap.add(num);
            rightHeapSum += num;

            int temp = rightHeap.poll();
            rightHeapSum -= temp;
            leftHeap.add(temp);
            leftHeapSum += temp;

            // 大顶堆个数比小顶堆多 2 个则将大顶堆堆顶元素放入小顶堆，最后大顶堆顶元素可以看出中位数
            if (leftHeap.size() - rightHeap.size() == 2) {
                temp = leftHeap.poll();
                leftHeapSum -= temp;
                rightHeap.add(temp);
                rightHeapSum += temp;
            }

            long pivot = leftHeap.peek();
            res[i] = (int) (((pivot * leftHeap.size() - leftHeapSum) + (rightHeapSum - pivot * rightHeap.size())) % mod);
        }

        return res;
    }
}
