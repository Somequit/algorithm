package leetcode.contest.contest_485;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2026/1/18 10:26 上午
 */
public class Contest_485_2 {
    public int maxCapacity(int[] costs, int[] capacity, int budget) {
        List<int[]> listCostMaxCap = new ArrayList<>();
        int res = 0;
        for (int i = 0; i < costs.length; i++) {
            if (costs[i] < budget) {
                listCostMaxCap.add(new int[]{costs[i], capacity[i], 0});
                res = Math.max(res, capacity[i]);
            }
        }
        if (listCostMaxCap.size() == 0) {
            return 0;
        }

        listCostMaxCap.sort(Comparator.comparingInt(l -> l[0]));

        int maxCap = listCostMaxCap.get(0)[1];
        for (int i = 0; i < listCostMaxCap.size(); i++) {
            maxCap = Math.max(maxCap, listCostMaxCap.get(i)[1]);
            listCostMaxCap.get(i)[2] = maxCap;
        }

//        listCostMaxCap.forEach(list -> System.out.print(list[0] + " : " + list[1] + " : " + list[2] + "\t"));
//        System.out.println();


        for (int i = listCostMaxCap.size() - 1; i >= 1; i--) {

            int left = 0;
            int right = i - 1;
            int curIdx = -1;
            while (left <= right) {
                int mid = (right - left) / 2 + left;
                if (listCostMaxCap.get(i)[0] + listCostMaxCap.get(mid)[0] < budget) {
                    curIdx = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            if (curIdx != -1) {
                res = Math.max(res, listCostMaxCap.get(curIdx)[2] + listCostMaxCap.get(i)[1]);
            }
        }

        return res;
    }
}
