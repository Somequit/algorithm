package leetcode.contest.contest_482;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/12/28 10:19 上午
 */
public class Contest_482_1 {

    public long maximumScore(int[] nums) {
        int n = nums.length;
        long sum = 0;
        for (int i = 0; i < n - 1; i++) {
            sum += nums[i];
        }

        int sufMin = nums[n - 1];
        long res = sum - sufMin;

        for (int i = n - 2; i > 0; i--) {
            sum -= nums[i];
            sufMin = Math.min(sufMin, nums[i]);
            res = Math.max(res, sum - sufMin);
        }
        return res;
    }

}
