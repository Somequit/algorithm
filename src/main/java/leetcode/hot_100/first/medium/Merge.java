package leetcode.hot_100.first.medium;

import utils.AlgorithmUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author gusixue
 * @description
 * 56. 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * 1 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10^4
 * @date 2023/6/21
 */
public class Merge {

    public static void main(String[] args) {
        Merge merge = new Merge();
        while (true) {
            int[][] intervals = AlgorithmUtils.systemInTwoArray();
            int[][] res = merge.solution(intervals);
            AlgorithmUtils.systemOutArray(res);
        }
    }

    /**
     * 排序 + 遍历：排序第一维升序、第二维升序降序均可，遍历数组将第一个元素 starti 放入 start、endi 放入 end，对比 end 与下一个元素的 starti，
     *     如果 end 小于 starti，代表下一个元素为下一个区间，start 与 end 放入结果，重新赋值为下一个元素
     *     如果 end 大于等于 starti，代表俩区间有重叠，更新 end、取 end 与 endi 的最大值，即当前合并后区间最右端点
     * 直到数组遍历结束，将最后的 start、end 放入结果，
     * 解释：第一维升序后，每次遍历一定是当前剩余元素的最小 starti
     * 时间复杂度：O（nlogn）排序复杂度，空间复杂度：O（n）最后存储结果需要集合转数组
     *
     * 在 JDK7 版本以上，Comparator 要满足自反性，传递性，对称性，不然 Arrays.sort，Collections.sort 会抛出 IllegalArgumentException 异常
     *     自反性：两个相同的元素相比时，compare 必须返回0，也就是 compare(o1, o1) = 0
     *     反对称性：如果 compare(o1,o2) = 1，则 compare(o2, o1) 必须返回符号相反的值也就是 -1
     *     传递性：如果 a>b, b>c, 则 a 必然大于 c。也就是 compare(a,b)>0, compare(b,c)>0, 则 compare(a,c)>0
     */
    private int[][] solution(int[][] intervals) {
        // 判空
        if (intervals == null || intervals.length <= 0 || intervals[0] == null || intervals[0].length <= 0) {
            return new int[0][0];
        }

        // 排序第一维升序、第二维升序
//        Arrays.sort(intervals, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] interval1, int[] interval2) {
//                return interval1[0] - interval2[0];
//            }
//        });
        sortIntervals(intervals);
//        AlgorithmUtils.systemOutArray(intervals);

        // 遍历放入结果
        return doMerge(intervals);
    }

    /**
     * 排序第一维升序、第二维升序
     */
    private void sortIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] > o2[0]) {
                    return 1;

                } else if (o1[0] < o2[0]) {
                    return -1;

                } else {
                    if (o1[1] > o2[1]) {
                        return 1;

                    } else if (o1[1] < o2[1]) {
                        return -1;

                    } else {
                        return 0;
                    }
                }
            }
        });
    }

    /**
     * 遍历返回结果
     */
    private int[][] doMerge(int[][] intervals) {
        int start = intervals[0][0];
        int end = intervals[0][1];

        List<String> resList = new ArrayList<>();
        int len = intervals.length;
        for (int i = 1; i < len; i++) {

            if (end < intervals[i][0]) {
                resList.add(start + "_" + end);
                start = intervals[i][0];
                end = intervals[i][1];

            } else {
                end = Math.max(end, intervals[i][1]);
            }
        }
        resList.add(start + "_" + end);

        return resList.stream()
                .map(l -> IntStream.of(Integer.valueOf(l.split("_")[0]), Integer.valueOf(l.split("_")[1])).toArray())
                .toArray(int[][]::new);
    }
}
