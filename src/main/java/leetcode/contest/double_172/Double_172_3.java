package leetcode.contest.double_172;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/12/20 10:27 下午
 */
public class Double_172_3 {
    public long maximumScore(int[] nums, String s) {
        long res = 0;
        Queue<Integer> maxHeap = new PriorityQueue<>((i1, i2) -> i2 - i1);

        for (int i = 0; i < nums.length; i++) {
            maxHeap.add(nums[i]);
            if (s.charAt(i) == '1') {
                res += maxHeap.poll();
            }
        }

        return res;
    }
}
