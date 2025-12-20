package leetcode.contest.double_172;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/12/20 10:27 下午
 */
public class Double_172_1 {
    public int minOperations(int[] nums) {
        Map<Integer, Integer> mapCnt = new HashMap<>();
        int kind = 0;
        for (int num : nums) {
            mapCnt.merge(num, 1, Integer::sum);

            if (mapCnt.get(num) == 1) {
                kind++;
            }
        }

        int res = 0;
        int i = 0;
        while (kind != nums.length - i) {
            for (int j = 0; j < 3 && i < nums.length; j++, i++) {
                if (mapCnt.get(nums[i]) == 1) {
                    kind--;
                }

                mapCnt.merge(nums[i], -1, Integer::sum);
            }
            res++;
        }
        return res;
    }
}
