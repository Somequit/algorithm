package leetcode.brushQuestions.medium;

import javafx.util.Pair;

import java.util.*;

/**
 * @author gusixue
 * @description 1146. 快照数组
 * @date 2024/4/26
 */
public class SnapshotArray {

    // index - (snap_id, val)
    private final List<Pair<Integer, Integer>>[] listIndexSnapVal;

    private int lastSnapId;


    public SnapshotArray(int length) {
        listIndexSnapVal = new ArrayList[length];
        Arrays.setAll(listIndexSnapVal, i -> new ArrayList<>());

        lastSnapId = 0;
    }

    public void set(int index, int val) {
        int curIndexLen = listIndexSnapVal[index].size();
        // 覆盖最后一个快照设置值
        if (curIndexLen > 0 && listIndexSnapVal[index].get(curIndexLen - 1).getKey() == lastSnapId) {
            listIndexSnapVal[index].set(curIndexLen - 1, new Pair<>(lastSnapId, val));

            // 新增最后一个快照值
        } else {
            listIndexSnapVal[index].add(new Pair<>(lastSnapId, val));
        }
    }

    public int snap() {
        lastSnapId++;

        return lastSnapId - 1;
    }

    /**
     * 二分获取小于等于 snap_id 的最大存在的 List-snapId
     */
    public int get(int index, int snap_id) {
        // 该 snap_id 没有存过值
        if (listIndexSnapVal[index].size() == 0 || listIndexSnapVal[index].get(0).getKey() > snap_id) {
            return 0;
        }

        List<Pair<Integer, Integer>> listSnapVal = listIndexSnapVal[index];
        int left = 0;
        int right = listSnapVal.size() - 1;
        int res = 0;
        while (left <= right) {
            int mid = (right - left) / 2 + left;

            int curSnap = listSnapVal.get(mid).getKey();
            if (curSnap > snap_id) {
                right = mid - 1;

            } else if (curSnap < snap_id) {
                left = mid + 1;
                res = mid;

            } else {
                res = mid;
                break;
            }
        }

        return listSnapVal.get(res).getValue();
    }
}
