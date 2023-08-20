package leetcode.contest.contest_359;


import utils.AlgorithmUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
            int n = AlgorithmUtils.systemInNumberInt();
            List<List<Integer>> offers = Arrays.stream(AlgorithmUtils.systemInTwoArrayInteger())
                    .map(Arrays::asList).collect(Collectors.toList());

            int res = contest.solution(n, offers);
            System.out.println(res);
        }

    }

    /**
     * 首先按照 end 升序，
     * 然后循环 offers 找小于 start 的所有 end区间 （空或者连续的区间）中最大的 gold：end区间 保证 end 递增、gold 递增
     * 接着将找到的 gold （可能为空）加入当前遍历中的 gold，然后判断是否将 [end, sum(gold)] 加入 end区间
     * 答案就是 end区间 的最后一个值（gold 最大）
     * 设 offers.length 为 m，时间复杂度：O（m*logm），空间复杂度：O（m）
     */
    public int solution(int n, List<List<Integer>> offers) {
        // end 升序排序
        Collections.sort(offers, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(1) - o2.get(1);
            }
        });
//        System.out.println("offers:" + offers);

        // end区间 保证 end 递增、gold 递增
        TreeMap<Integer, Integer> endRangeTreeMap = new TreeMap<>();

        // 循环 offers 找小于 start 的所有 end区间 （空或者连续的区间）中最大的 gold
        for (int i = 0; i < offers.size(); i++) {
            List<Integer> offersCur = offers.get(i);
            int start = offersCur.get(0);
            int end = offersCur.get(1);
            int gold = offersCur.get(2);

            Map.Entry<Integer, Integer> endRangeEntry = endRangeTreeMap.lowerEntry(start);
//            System.out.println("endRangeEntry:" + endRangeEntry + " : " + start);

            // 将找到的 gold （可能为空）加入当前遍历中的 gold，然后判断是否将 [end, sum(gold)] 加入 end区间
            if (endRangeEntry != null) {
                gold += endRangeEntry.getValue();
            }

            if (endRangeTreeMap.isEmpty() || gold > endRangeTreeMap.lastEntry().getValue()) {
                endRangeTreeMap.put(end, gold);
            }
//            System.out.println("endRangeTreeMap:" + endRangeTreeMap);
        }

        // 答案就是 end区间 的最后一个值（gold 最大）
        return endRangeTreeMap.lastEntry().getValue();
    }

}
