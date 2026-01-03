package leetcode.contest.double_173;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2026/1/3 10:29 下午
 */
public class Double_173_2 {
    public int minLength(int[] nums, int k) {
        long total = nums[0];
        Map<Integer, Integer> mapCnt = new HashMap<>();
        mapCnt.put(nums[0], 1);
        int res = Integer.MAX_VALUE;
        for (int l = 0, r = 1; l < nums.length; l++) {
            while (r < nums.length && total < k) {
                mapCnt.merge(nums[r], 1, Integer::sum);

                if (mapCnt.get(nums[r]) == 1) {
                    total += nums[r];
                }
                r++;
            }

            if (total >= k) {
                res = Math.min(res, r - l);
            }

            mapCnt.merge(nums[l], -1, Integer::sum);
            if (mapCnt.get(nums[l]) == 0) {
                total -= nums[l];
            }
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
