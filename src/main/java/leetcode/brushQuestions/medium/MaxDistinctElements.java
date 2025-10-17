package leetcode.brushQuestions.medium;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author gusixue
 * @description 3397. 执行操作后不同元素的最大数量
 * @date 2025/10/18 3:50 上午
 */
public class MaxDistinctElements {

    /**
     * 问题可转化为每个数字经可能向两边移动（+/- i），尽可能腾出更多的位置，因此排序数组后可以从大到小，也能从小到大，
     * 选择从大到小，最大元素先 +k 移到最右边，然后接下来的元素一定不大于之前的元素，因此接下来的元素移动后一定需要小于之前的元素，
     * 如果可以小 1 就行，否则 +k 到达最右边，同时保证不能大于等于之前选择过的元素 curMaxNum
     * 时间复杂度：O（nlogn），空间复杂度：O（1）
     */
    public int maxDistinctElements(int[] nums, int k) {
        List<Integer> numSortedList = Arrays.stream(nums).sorted().boxed().collect(Collectors.toList());

        int res = 0;
        // 最大元素先 +k 移到最右边
        int curMaxNum = numSortedList.get(numSortedList.size() - 1) + k;
        for (int i = numSortedList.size() - 1; i >= 0; i--) {

            // 接下来的元素移动后一定需要小于之前的元素
            if (numSortedList.get(i) + k >= curMaxNum && numSortedList.get(i) - k <= curMaxNum) {
                res++;
                // 如果可以小 1 就行
                curMaxNum--;

                // 否则 +k 到达最右边
            } else if (numSortedList.get(i) + k < curMaxNum) {
                res++;
                curMaxNum = numSortedList.get(i) + k - 1;

            }
            // 保证不能大于等于之前选择过的元素 curMaxNum
            // else 丢弃 numSortedList.get(i)
        }

        return res;
    }
}
