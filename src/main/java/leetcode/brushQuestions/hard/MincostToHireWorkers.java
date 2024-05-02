package leetcode.brushQuestions.hard;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author gusixue
 * @description 857. 雇佣 K 名工人的最低成本
 * @date 2024/5/2
 */
public class MincostToHireWorkers {

    /**
     * 按照 wage/quality=wq 升序，然后将最小的 k 个 wq 放入优先队列，
     * 优先队列按照 quality 降序，堆顶为最大的 quality，形成大顶堆，
     * 大顶堆中按照每次计算得到 sum(quality)，最后入堆的 max(wq) * sum(quality) 则是当前最小工资和，
     * 接着删除堆顶 wq，放入下一个 wq，同时维护 sum(quality)，得到当前最小工资和，直到数组遍历完成
     */
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        List<Integer> listIndex = IntStream.range(0, n).boxed().collect(Collectors.toList());

//        listIndex.sort((i1, i2) -> doubleCalculate((double)wage[i1] / quality[i1], (double)wage[i2] / quality[i2]));
        // 注意：除法相减 转化 乘法相减
        listIndex.sort((i1, i2) -> wage[i1] * quality[i2] - wage[i2] * quality[i1]);
//        System.out.println(listIndex);

        Queue<Integer> maxWageQualityHeap = new PriorityQueue<>((i1, i2) -> quality[i2] - quality[i1]);

        long totalQuality = 0L;
        for (int i = 0; i < k; i++) {
            int index = listIndex.get(i);

            totalQuality += quality[index];

            maxWageQualityHeap.offer(index);
        }

        double res = totalQuality * ((double)wage[listIndex.get(k - 1)] / quality[listIndex.get(k - 1)]);

        for (int i = k; i < n; i++) {
            int index = listIndex.get(i);
            int delIndex = maxWageQualityHeap.poll();

            totalQuality -= quality[delIndex];
            totalQuality += quality[index];

            maxWageQualityHeap.offer(index);

            res = Math.min(res, totalQuality * ((double)wage[index] / quality[index]));
        }

        return res;
    }

    private static int doubleCalculate(double g, double h) {
        double diff = 1e-6;
        if (Math.abs(g - h) < diff) {
            return 0;
        } else {
            return g - h >= diff ? 1 : -1;
        }
    }

}
