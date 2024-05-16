package leetcode.brushQuestions.hard;

import java.util.*;

/**
 * @author gusixue
 * @description 2589. 完成所有任务的最少时间
 * @date 2024/5/16
 */
public class FindMinimumTime {

    /**
     * 任务排序：仅结束时间升序，下一段任务要么与之前的任务无交集，要么是之前任务的后缀，因此每个任务没做完就取最后的时间来做
     */
    public int findMinimumTime(int[][] tasks) {
        // 任务排序：开始时间升序、结束时间升序
        Arrays.sort(tasks, (arr1, arr2) -> arr1[1] - arr2[1]);
        int len = tasks.length;
        int maxTime = tasks[len - 1][1];

        boolean[] time = new boolean[maxTime + 1];

        int res = 0;
        for (int i = 0; i < len; i++) {
            int start = tasks[i][0];
            int end = tasks[i][1];
            int duration = tasks[i][2];

            int curDuration = 0;
            for (int j = end; j >= start; j--) {
                if (time[j]) {
                    curDuration++;
                }
            }

            for (int j = end; curDuration < duration; j--) {
                if (!time[j]) {
                    time[j] = true;
                    curDuration++;
                    res++;
                }
            }

        }
        return res;
    }
}
