package leetcode.contest.contest_487;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2026/2/1 10:22 上午
 */
public class Contest_487_4 {
    public int longestAlternating(int[] nums) {
        int n = nums.length;
        int[] prevNum = new int[n];
        for (int i = 1; i < n; i++) {
            // nums[i] - nums[i-1]
            prevNum[i] = Integer.compare(nums[i], nums[i - 1]);
        }
//        System.out.println(Arrays.toString(prevNum));

        int res = 1;
        int[] dpLongestNext = new int[n];
        dpLongestNext[n - 1] = prevNum[n - 1] == 0 ? 0 : 1;
        res = Math.max(res, dpLongestNext[n - 1] + 1);
        for (int i = n - 2; i > 0; i--) {
            if (prevNum[i] != 0) {
                if (-prevNum[i] == prevNum[i + 1]) {
                    dpLongestNext[i] = dpLongestNext[i + 1] + 1;

                } else {
                    dpLongestNext[i] = 1;
                }
                res = Math.max(res, dpLongestNext[i] + 1);
            }
        }
//        System.out.println(Arrays.toString(dpLongestNext));

        int dpLongestPrev = 0;
        for (int i = 1; i < n - 2; i++) {
            if (prevNum[i] != 0) {
                if (-prevNum[i] == prevNum[i - 1]) {
                    dpLongestPrev++;

                } else {
                    dpLongestPrev = 1;
                }
//                System.out.println(i + " : " + dpLongestPrev);

                if (-prevNum[i] == Integer.compare(nums[i + 2], nums[i])) {
                    res = Math.max(res, dpLongestPrev + dpLongestNext[i + 2] + 1);
                }

            } else {
                dpLongestPrev = 0;
            }
        }

        return res;
    }
}
