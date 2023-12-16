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
    // left-right
    private TreeMap<Integer, Integer> lTreeMap;
    // right-left
    private TreeMap<Integer, Integer> rTreeMap;

    /**
     * 两个 TreeMap 存储互不相交的区间 ltreeMap、rTreeMap，总个数存储 count 中
     */
    public CountIntervals() {
        this.COUNT = 0;
        lTreeMap = new TreeMap<>();
        rTreeMap = new TreeMap<>();
    }

    /**
     * left 在某个区间内，则将 left 更新为区间的 lTreeMap(i)，在区间外如果 left-1 等于某个区间 rTreeMap、则更新为区间的 lTreeMap(i)，
     * 否则找到 lTreeMap(i) 大于 left 的最小值，
     * 找不到则将 i 置为 lTreeMap 最小值,
     *
     * right 在某个区间内，则将 right 更新为区间的 rTreeMap(j)，在区间外如果 right+1 等于某个区间 lTreeMap、则更新为区间的 rTreeMap(j)，
     * 否则找到 rTreeMap(j) 小于 left 的最大值，
     * 找不到则将 j 置为 rTreeMap 最大值,
     *
     * 遍历 [i,j] 删除对应的 lTreeMap-rTreeMap，同时更新 count，
     * 将 left、right 加入 lTreeMap-rTreeMap，同时更新 count，
     *
     * 可以保证不会出现相交区域
     *
     */
    public void add(int left, int right) {

        if(lTreeMap.size() == 0) {
            lTreeMap.put(left, right);
            rTreeMap.put(right, left);
            this.COUNT += right - left + 1;
            return;
        }

        int i = lTreeMap.firstKey();
        int j = rTreeMap.lastKey();

        Map.Entry<Integer, Integer> maxLeft = lTreeMap.floorEntry(left);
        if (maxLeft != null) {
            // left 在某个区间内，则将 left 更新为区间的 lTreeMap(i)，在区间外如果 left-1 等于某个区间 rTreeMap、则更新为区间的 lTreeMap(i)，
            if (maxLeft.getValue() >= left - 1) {
                left = maxLeft.getKey();
                i = maxLeft.getKey();

            // 否则找到 lTreeMap(i) 大于 left 的最小值，
            } else {
                Integer temp = lTreeMap.higherKey(left);
                if (temp != null) {
                    i = temp;

                } else {
                    lTreeMap.put(left, right);
                    rTreeMap.put(right, left);
                    this.COUNT += right - left + 1;
                    return;
                }
            }
        }

        Map.Entry<Integer, Integer> maxRight = rTreeMap.ceilingEntry(right);
        if (maxRight != null) {
            // right 在某个区间内，则将 right 更新为区间的 rTreeMap(j)，在区间外如果 right+1 等于某个区间 lTreeMap、则更新为区间的 rTreeMap(j)，
            if (maxRight.getValue() <= right + 1) {
                right = maxRight.getKey();
                j = maxRight.getKey();

            // 否则找到 rTreeMap(j) 小于 left 的最大值，
            } else {
                Integer temp = rTreeMap.lowerKey(right);
                if (temp != null) {
                    j = temp;

                } else {
                    lTreeMap.put(left, right);
                    rTreeMap.put(right, left);
                    this.COUNT += right - left + 1;
                    return;
                }
            }
        }

        List<Pair<Integer, Integer>> listTemp = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry = lTreeMap.ceilingEntry(i); entry != null && entry.getValue() <= j
                ; entry = lTreeMap.higherEntry(entry.getKey())) {
            this.COUNT -= entry.getValue() - entry.getKey() + 1;

            listTemp.add(new Pair<>(entry.getKey(), entry.getValue()));
        }

        for (Pair<Integer, Integer> pair : listTemp) {
            lTreeMap.remove(pair.getKey());
            rTreeMap.remove(pair.getValue());
        }

        lTreeMap.put(left, right);
        rTreeMap.put(right, left);
        this.COUNT += right - left + 1;

        System.out.println(lTreeMap);
        System.out.println(rTreeMap);
        System.out.println(this.COUNT);
    }

    public int count() {
        return this.COUNT;
    }

}
