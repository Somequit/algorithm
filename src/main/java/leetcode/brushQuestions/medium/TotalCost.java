package leetcode.brushQuestions.medium;

import java.util.*;

/**
 * @author gusixue
 * @description 2462. 雇佣 K 位工人的总代价
 * @date 2024/5/1
 */
public class TotalCost {

    /**
     * 优先队列存储 costs-index，每次弹出最小的 costs（相同 costs 弹出最小的 index），接着判断 index 是左边还是右边然后加入下一个值，
     * 注意弹出值后，index 可以不用改变，因为 index 不用改变、相同 costs 也可以求出最小的 index
     */
    public long totalCost(int[] costs, int k, int candidates) {
        int n = costs.length;

        // costs、index
        Queue<int[]> minHeap = new PriorityQueue<>((arr1, arr2) -> arr1[0] == arr2[0] ? arr1[1] - arr2[1] : arr1[0] - arr2[0]);

        // [0, leftLast]
        int leftLast = 0;
        for (int i = 0; i < candidates; i++) {
            minHeap.offer(new int[]{costs[leftLast], leftLast});
            leftLast++;
        }
        leftLast--;

        // [rightLast, n-1]
        int rightLast = n - 1;
        for (int i = 0; i < candidates && rightLast > leftLast; i++) {
            minHeap.offer(new int[]{costs[rightLast], rightLast});
            rightLast--;
        }
        rightLast++;

        long res = 0;
        for (int i = 0; i < k; i++) {
//            minHeap.stream().forEach(arr -> System.out.print(Arrays.toString(arr)));
//            System.out.println();
            int[] minCosts = minHeap.poll();

            res += minCosts[0];

            if (rightLast - leftLast > 1) {
                if (minCosts[1] <= leftLast) {
                    leftLast++;
                    minHeap.offer(new int[]{costs[leftLast], leftLast});

                } else {
                    rightLast--;
                    minHeap.offer(new int[]{costs[rightLast], rightLast});
                }
            }

        }

        return res;
    }
}
