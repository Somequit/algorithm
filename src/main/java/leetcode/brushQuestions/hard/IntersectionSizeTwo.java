package leetcode.brushQuestions.hard;

import java.util.*;

/**
 * @author gusixue
 * @description 757. 设置交集大小至少为2
 * @date 2025/11/20 7:43 下午
 */
public class IntersectionSizeTwo {

    /**
     * 删除包含情况（A 包含 B 则 B 满足 A 一定满足，因此删除 A），start 升序、end 升序，遍历时先拿第一个的 end-1,end，遍历下一个值、看是否满足，满足则继续遍历，不满足则添加 新end 不够再加 新end-1
     * 优化：不删除包含情况，直接根据 end 升序，遍历不变
     */
    public int intersectionSizeTwo(int[][] intervals) {
        int n = intervals.length;
        List<int[]> listIntervals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            listIntervals.add(intervals[i]);
        }

        listIntervals.sort(Comparator.comparingInt(l -> l[1]));
//        listIntervals.stream().forEach(arr -> System.out.println(arr[0] + " " + arr[1]));

        int res = 2;
        int maxNum = listIntervals.get(0)[1];
        int maxSecondNum = maxNum - 1;
        for (int i = 1; i < listIntervals.size(); i++) {
            int start = listIntervals.get(i)[0];
            int end = listIntervals.get(i)[1];

            // 如果包含次大值一定包含最大值
            if (maxSecondNum >= start && maxSecondNum <= end) {
                continue;

                // 最大值等于 end
            } else if (maxNum == end) {
                maxSecondNum = maxNum - 1;
                res ++;

            } else if (maxNum >= start && maxNum <= end) {
                maxSecondNum = maxNum;
                maxNum = end;
                res++;

            } else {
                maxNum = end;
                maxSecondNum = maxNum - 1;
                res += 2;
            }
        }

        return res;
    }
}
