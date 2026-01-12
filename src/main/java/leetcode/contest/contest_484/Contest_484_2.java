package leetcode.contest.contest_484;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2026/1/11 10:13 上午
 */
public class Contest_484_2 {
    public int centeredSubarrays(int[] nums) {
        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            Set<Integer> set = new HashSet<>();
            int total = 0;
            for (int j = i + 1; j < nums.length + 1; j++) {
                set.add(nums[j - 1]);
                total += nums[j - 1];

                if (set.contains(total)) {
                    res++;
                }
            }
        }

        return res;
    }
}
