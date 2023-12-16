package leetcode.brushQuestions.hard;

import javafx.util.Pair;

import java.util.*;

/**
 * @author gusixue
 * @description 2276. 统计区间中的整数数目
 * @date 2023/12/16
 */
public class CountIntervals {

    private int COUNT;
    // key=left、value=right
    private TreeMap<Integer, Integer> intervalTreeMap;

    /**
     * TreeMap 存储互不相交的区间：IntervalTreeMap
     * 总个数存储 count 中，实时维护
     */
    public CountIntervals() {
        this.COUNT = 0;
        intervalTreeMap = new TreeMap<>();
    }

    /**
     * Java + TreeMap 区间合并：
     *
     * 第 1 步：
     * 如果为空，直接加入 IntervalTreeMap，更新 count
     * 如果非空，则判断出传入 [left, right] 与原有不相交区间的重叠，删除被覆盖、部分重叠区间，最后将 [left, right] 插入，注意每次必须更新 count
     *
     * 第 2 步：
     * 为方便区间合并，将区间设计成 [left, right+1)，此时区间合并更方便、如：[1，3) + [3，5) = [1，5)
     * 注意以下均按照如此设计
     *
     * 第 3 步：
     * 如果 left 大于最大的区间右端点，或者 right 小于最小区间的左端点，则没有重叠区间，直接添加区间、更新 count
     *
     * 第 4 步：
     * 思考部分重叠时如何处理，即：
     *     left[0] < left[1] <= right[0]，此时可以将 left[1] 更新成 left[0]，这样直接删除 [left[0]、 right[0]) 即可，
     *     left[0] <= right[1] < right[0]，将 right[1] 更新成 right[0]，也直接删除 [left[0]、 right[0]) 即可，
     * 被覆盖则直接删除被覆盖的区间、不需要改变原有 left[1]-right[1]，即：left[1] <= left[0] < right[0] <= right[1]
     *
     * 第 5 步：
     * 直接从已有区间遍历，部分重叠更新 left、right，完全重叠删除区间，最后直接添加区间、更新 count
     * 具体方法：
     *     * 从小于等于 left 的最大 getKey 开始遍历、如无则从第一个开始遍历，直到结束或者 right < getKey
     *     * getKey < left <= getValue，left = getKey，同时删除区间、更新 count
     *     * getKey <= right < getValue，right = getValue，同时删除区间、更新 count
     *     * left <= getKey 且 getValue <= right 直接删除区间、更新 count
     *     * 否则跳过，注意只可能遍历的第一个区间
     * 时间复杂度：O（n * logn）每个区间最多增/删一次，空间复杂度：O（n）
     *
     */
    public void add(int left, int right) {
        // 为方便区间合并，将区间设计成 [left, right+1)
        right++;

        // 为空，直接加入 IntervalTreeMap，更新 count
        if(intervalTreeMap.size() == 0) {
            updateInterval(left, right);
            return;
        }

        // 如果 left 大于最大的区间右端点，或者 right 小于最小区间的左端点，则没有重叠区间，直接添加区间、更新 count
        if (left > intervalTreeMap.lastEntry().getValue() || right < intervalTreeMap.firstKey()) {
            updateInterval(left, right);
            return;
        }

        /**
         * 直接从已有区间遍历，部分重叠更新 left、right，完全重叠删除区间，最后直接添加区间、更新 count
         * 具体方法：
         *     * 从小于等于 left 的最大 getKey 开始遍历、如无则从第一个开始遍历，直到结束或者 right < getKey
         *     * getKey < left <= getValue，left = getKey，同时删除区间、更新 count
         *     * getKey <= right < getValue，right = getValue，同时删除区间、更新 count
         *     * left <= getKey 且 getValue <= right 直接删除区间、更新 count
         *     * 否则跳过，注意只可能遍历的第一个区间
         */
        Map.Entry<Integer, Integer> entry = intervalTreeMap.floorEntry(left);
        if (entry == null) {
            entry = intervalTreeMap.firstEntry();
        }
        for (; entry != null && right >= entry.getKey();
                entry = intervalTreeMap.higherEntry(entry.getKey())) {
            if (entry.getKey() < left && left <= entry.getValue()) {
                left = entry.getKey();
                deleteInterval(entry.getKey(), entry.getValue());
            }

            if (entry.getKey() <= right && right < entry.getValue()) {
                right = entry.getValue();
                deleteInterval(entry.getKey(), entry.getValue());
            }

            if (left <= entry.getKey() && entry.getValue() <= right) {
                deleteInterval(entry.getKey(), entry.getValue());
            }

        }

        updateInterval(left, right);

//        System.out.println(intervalTreeMap);
//        System.out.println(this.COUNT);
    }

    /**
     * 需要避免多次删除区间，会无法删除
     */
    private void deleteInterval(int left, int right) {
        if (intervalTreeMap.remove(left) != null) {
            this.COUNT -= right - left;
        }
    }

    private void updateInterval(int left, int right) {
        intervalTreeMap.put(left, right);
        this.COUNT += right - left;
    }

    public int count() {
        return this.COUNT;
    }

}
