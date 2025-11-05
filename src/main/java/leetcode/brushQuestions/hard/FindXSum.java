package leetcode.brushQuestions.hard;

import java.util.*;

/**
 * @author gusixue
 * @description 3321. 计算子数组的 x-sum II
 * @date 2025/11/5 7:01 上午
 */
public class FindXSum {

    /**
     * 问题转化为：长度为 k 的滑窗从头到尾移动，每次取出滑窗中排序最大的前 x 种元素求和，元素按照先个数升序、再按照元素大小升序，
     * 对顶堆：两个 TreeSet，一个 treeSetXkind 存储滑窗中最多 x 种（元素-个数），同时记录总和，另一个 treeSetOther 存储超过 x 种时剩余的（元素-个数）
     * 滑窗右滑时，左边元素出窗删除掉、右边元素入窗加入；
     * 对应 treeSetXkind 与 treeSetOther 删除（如有）俩元素以前存在（元素-个数），加上（如有）俩元素改变后的（元素-个数），最后保证 treeSetXkind 有且最多有 x 种（元素-个数）
     */
    public long[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        long[] res = new long[n - k + 1];

        // num-cnt
        Map<Integer, Integer> mapNumCnt = new HashMap<>();
        // num-cnt；存前 x 种，先按照个数升序、再按照元素升序
        long xKindTotal = 0;
        TreeSet<int[]> treeSetXkind = new TreeSet<>(this::compareNumCnt);
        // num-cnt，存前其他，先按照个数升序、再按照元素升序
        TreeSet<int[]> treeSetOther = new TreeSet<>(treeSetXkind.comparator());

        for (int i = 0; i < k; i++) {
            // 入窗加入
            xKindTotal += addNumCnt(treeSetOther, treeSetXkind, mapNumCnt, nums[i], x);
        }

        res[0] = xKindTotal;
        for (int i = k; i < n; i++) {
            int left = i - k;
            if (nums[left] == nums[i]) {
                res[i - k + 1] = xKindTotal;
                continue;
            }

            // nums[left] 出窗删除掉
            xKindTotal += delNumCnt(treeSetOther, treeSetXkind, mapNumCnt, nums[left], x);

            // nums[i] 入窗加入
            xKindTotal += addNumCnt(treeSetOther, treeSetXkind, mapNumCnt, nums[i], x);

            res[i - k + 1] = xKindTotal;
        }

        return res;
    }

    private long delNumCnt(TreeSet<int[]> treeSetOther, TreeSet<int[]> treeSetXkind, Map<Integer, Integer> mapNumCnt, int num, int x) {
        mapNumCnt.merge(num, -1, Integer::sum);
        
        int[] prevNumCnt = new int[]{num, mapNumCnt.get(num) + 1};
        long res = del(treeSetOther, treeSetXkind, prevNumCnt);


        if (mapNumCnt.get(num) > 0) {
            int[] curNumCnt = new int[]{num, mapNumCnt.get(num)};
            res += add(treeSetOther, treeSetXkind, curNumCnt);
        }

        // 更新俩 TreeSet 个数
        res += updateCnt(treeSetOther, treeSetXkind, x);

        return res;
    }

    private long addNumCnt(TreeSet<int[]> treeSetOther, TreeSet<int[]> treeSetXkind, Map<Integer, Integer> mapNumCnt, int num, int x) {
        mapNumCnt.merge(num, 1, Integer::sum);
        
        long res = 0;
        if (mapNumCnt.get(num) > 1) {
            int[] prevNumCnt = new int[]{num, mapNumCnt.get(num) - 1};
            res += del(treeSetOther, treeSetXkind, prevNumCnt);
        }

        int[] curNumCnt = new int[]{num, mapNumCnt.get(num)};
        res += add(treeSetOther, treeSetXkind, curNumCnt);

        // 更新俩 TreeSet 个数
        res += updateCnt(treeSetOther, treeSetXkind, x);

        return res;
    }

    private long updateCnt(TreeSet<int[]> treeSetOther, TreeSet<int[]> treeSetXkind, int x) {
        long res = 0;

        while (treeSetXkind.size() > x) {
            int[] numCnt = treeSetXkind.pollFirst();
            res -= (long) numCnt[0] * numCnt[1];
            treeSetOther.add(numCnt);
        }

        while (treeSetXkind.size() < x && treeSetOther.size() > 0) {
            int[] numCnt = treeSetOther.pollLast();
            res += (long) numCnt[0] * numCnt[1];
            treeSetXkind.add(numCnt);
        }

        return res;
    }

    private long del(TreeSet<int[]> treeSetOther, TreeSet<int[]> treeSetXkind, int[] numCnt) {
        if (treeSetXkind.remove(numCnt)) {
            return -(long) numCnt[0] * numCnt[1];

        } else {
            treeSetOther.remove(numCnt);
            return 0;
        }
    }

    
    private long add(TreeSet<int[]> treeSetOther, TreeSet<int[]> treeSetXkind, int[] numCnt) {
        // 如果比 treeSetXkind 最小值都大一定添加到它
        if (treeSetXkind.size() > 0 && compareNumCnt(numCnt, treeSetXkind.first()) > 0) {
            treeSetXkind.add(numCnt);
            return (long) numCnt[0] * numCnt[1];

        } else {
            treeSetOther.add(numCnt);
            return 0;
        }
    }

    // 先按照个数升序、再按照元素升序
    private int compareNumCnt(int[] numCnt1, int[] numCnt2) {
        return numCnt1[1] == numCnt2[1] ? Integer.compare(numCnt1[0], numCnt2[0]) : Integer.compare(numCnt1[1], numCnt2[1]);
    }
}
