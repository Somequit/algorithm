package leetcode.contest.double_175;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2026/1/31 10:29 下午
 */
public class Double_175_2 {

    public int minimumK(int[] nums) {
        int left = 1;
        int right = 100_000;
        int res = 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;

            if (doChecK(nums, mid)) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return res;
    }

    private boolean doChecK(int[] nums, int k) {
        long doubleK = (long) k * k;
        long total = 0;
        for (int num : nums) {
            total += (num + k - 1) / k;
        }

        return total <= doubleK;
    }

}
