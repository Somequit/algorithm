package leetcode.brushQuestions.medium;

import java.util.*;

/**
 * @author gusixue
 * @description 2007. 从双倍数组中还原原数组
 * @date 2024/4/18
 */
public class FindOriginalArray {

    /**
     * 元素个数一定是偶数个，数组最小的元素一定在原始数组，同时有二倍的值存在，两个数删除后继续处理，直到完成或者不满足元素出现
     * 注意 0 需要处理
     */
    public int[] findOriginalArrayFirst(int[] changed) {
        int n = changed.length;
        if (n % 2 == 1) {
            return new int[0];
        }

        int[] res = new int[n / 2];

        Map<Integer, Integer> mapChangedCount = new TreeMap<>();
        for (int changedVal : changed) {
            mapChangedCount.merge(changedVal, 1, Integer::sum);
        }

        int index = 0;
        for (Map.Entry<Integer, Integer> entry : mapChangedCount.entrySet()) {
            if (entry.getValue() == 0) {
                continue;
            }

            int originalVal = entry.getKey();
            int changedVal = originalVal * 2;
            if (mapChangedCount.getOrDefault(changedVal, 0) < entry.getValue()) {
                res = new int[0];
                break;
            }

            // 不会导致元素个数修改，因此不会抛出 ConcurrentModificationException
            // 如果有 0 则不会加入，但是数组默认结果都是 0
            mapChangedCount.merge(changedVal, -entry.getValue(), Integer::sum);
            for (int i = 0; i < entry.getValue(); i++, index++) {
                res[index] = originalVal;
            }

        }

        return res;
    }


    /**
     * 元素个数一定是偶数个，数组最小的元素一定在原始数组，同时有二倍的值存在，两个数删除后继续处理，直到完成或者不满足元素出现
     * 由于数据范围比较小，因此只需要开对应 count 数组记录元素以及个数即可
     * 注意 0 需要处理
     */
    public int[] findOriginalArray(int[] changed) {
        int n = changed.length;
        if (n % 2 == 1) {
            return new int[0];
        }

        int[] res = new int[n / 2];
        int[] count = new int[100_001];
        for (int changedVal : changed) {
            count[changedVal]++;
        }

        for (int i = 0, index = 0; i < count.length; i++) {
            if (count[i] == 0) {
                continue;
            }

            if (i * 2 >= count.length || count[i * 2] < count[i]) {
                res = new int[0];
                break;
            }

            // // 如果有 0 则不会加入，但是数组默认结果都是 0
            count[i * 2] -= count[i];
            for (int j = 0; j < count[i]; j++, index++) {
                res[index] = i;
            }
            if (index == res.length) {
                break;
            }
        }

        return res;
    }
}
