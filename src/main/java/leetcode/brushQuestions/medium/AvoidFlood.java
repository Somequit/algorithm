package leetcode.brushQuestions.medium;


import java.util.*;

/**
 * @author gusixue
 * @description 1488. 避免洪水泛滥
 * @date 2025/10/7 2:54 上午
 */
public class AvoidFlood {

    /**
     * 问题转化为某个 rains[i]>0 的值如果是第 k 次出现，且 k>1，那么在 k-1次 与 k次 之间就至少要有一个未使用的 rains[j]=0 来抽水，
     * 将 rains[i]>0 使用 HashMap 存储 (rains[i]，i) 代表某个湖最后一次出现的下标，再将 rains[i]=0 的 i 存入 TreeSet 中代表未使用过，
     * 如果 rains[i]>0 存在 HashMap 中,则代表之前出现过，就将 value 放入 TreeSet 找到第一个大于其的值，找到则删除然后在 res 结果中抽干，没有则会发生洪水
     * PS：注意当 rains[j]=0 有剩余就随便添加值而非填写 -1
     * 时间复杂度：O（nlog(n)）,空间复杂度：O（n）
     */
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);

        Map<Integer, Integer> floodIndexMap = new HashMap<>();
        TreeSet<Integer> avoidIndexSet = new TreeSet<>();

        for (int i = 0; i < n; i++) {

            if (rains[i] == 0) {
                avoidIndexSet.add(i);
                res[i] = 1;

            } else {
                if (!floodIndexMap.containsKey(rains[i])) {
                    floodIndexMap.put(rains[i], i);

                } else {
                    int floodIndex = floodIndexMap.get(rains[i]);
                    Integer avoidIndex = avoidIndexSet.ceiling(floodIndex);
                    if (avoidIndex == null) {
                        return new int[0];
                    }
                    res[avoidIndex] = rains[i];
                    avoidIndexSet.remove(avoidIndex);

                    floodIndexMap.put(rains[i], i);
                }
            }
        }

        return res;
    }
}
