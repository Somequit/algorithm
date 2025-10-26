package leetcode.contest.contest_473;

import java.util.*;

/**
 *
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

//            int res = contest.solution(n, nums, list);
//            System.out.println(res);
        }

    }

    /**
     * @return
     */
    public long countStableSubarrays(int[] capacity) {
        Map<Integer, List<Integer>> discretizationIndexMap = new HashMap<>();
        for (int i = 0; i < capacity.length; i++) {
            if (!discretizationIndexMap.containsKey(capacity[i])) {
                discretizationIndexMap.put(capacity[i], new ArrayList<>());
            }
            discretizationIndexMap.get(capacity[i]).add(i);
        }
//        System.out.println(discretizationIndexMap);

        int n = capacity.length;
        long[] prefix = new long[n];
        prefix[0] = capacity[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + capacity[i];
        }

        long res = 0;
        for (Map.Entry<Integer, List<Integer>> entry : discretizationIndexMap.entrySet()) {
            Map<Long, Integer> prefixCountMap = new HashMap<>();
            int curNum = entry.getKey();
//            System.out.println(curNum);
            for (int curIndex : entry.getValue()) {
                res += prefixCountMap.getOrDefault(prefix[curIndex] - 2L * curNum, 0);
                if (curNum == 0 && curIndex > 0 && capacity[curIndex - 1] == 0) {
                    res--;
                }
                prefixCountMap.merge(prefix[curIndex], 1, Integer::sum);
//                System.out.println(res + " : " + prefixCountMap);
            }
        }

        return res;
    }


}
