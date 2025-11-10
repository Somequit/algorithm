package leetcode.contest.contest_475;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/11/8 11:45 下午
 */
public class Contest_475_2 {

    public int minimumDistance(int[] nums) {
        Map<Integer, List<Integer>> mapNumIndexs = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (!mapNumIndexs.containsKey(nums[i])) {
                mapNumIndexs.put(nums[i], new ArrayList<>());
            }

            mapNumIndexs.get(nums[i]).add(i);
        }

        int res = Integer.MAX_VALUE;
        for (List<Integer> indexs : mapNumIndexs.values()) {
            for (int i = 2; i < indexs.size(); i++) {
                int k1 = indexs.get(i - 2);
                int k2 = indexs.get(i - 1);
                int k3 = indexs.get(i);
                res = Math.min(res, Math.abs(k1 - k2) + Math.abs(k2 - k3) + Math.abs(k3 - k1));
            }
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }

}
