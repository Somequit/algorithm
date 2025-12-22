package leetcode.brushQuestions.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * @author gusixue
 * @description 2054. 两个最好的不重叠活动
 * @date 2025/12/23 6:09 上午
 */
public class MaxTwoEvents {
    public int maxTwoEvents(int[][] events) {
        int n = events.length;
        Arrays.sort(events, Comparator.comparingInt(e -> e[1]));

        int[] prefixMaxValue = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixMaxValue[i + 1] = Math.max(prefixMaxValue[i], events[i][2]);
        }

        int res = 0;
        TreeMap<Integer, Integer> treeMapEndIdx = new TreeMap<>();
        treeMapEndIdx.put(0, -1);
        for (int i = 0; i < n; i++) {
            int startTime = events[i][0];
            int endTime = events[i][1];
            int value = events[i][2];

            int idx = treeMapEndIdx.get(treeMapEndIdx.lowerKey(startTime)) + 1;
            res = Math.max(res, value + prefixMaxValue[idx]);

            treeMapEndIdx.put(endTime, i);
        }

        return res;
    }
}
