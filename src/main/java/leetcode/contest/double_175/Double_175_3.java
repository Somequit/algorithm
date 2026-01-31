package leetcode.contest.double_175;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2026/1/31 10:29 下午
 */
public class Double_175_3 {

    public int longestSubsequence(int[] nums) {
        int res = 0;
        for (int i = 0; i < 31; i++) {
            List<Integer> bitNums = new ArrayList<>();
            for (int num : nums) {
                if ((num & (1 << i)) > 0) {
                    bitNums.add(num);
                }
            }

            if (bitNums.size() <= res) {
                continue;
            }

            res = Math.max(res, lengthOfLIS(bitNums));

            if (res == nums.length) {
                break;
            }
        }

        return res;
    }

    public int lengthOfLIS(List<Integer> nums) {
        List<Integer> listLIS = new ArrayList<>();
        for (int x : nums) {
            int j = lowerBound(listLIS, x);
            if (j == listLIS.size()) {
                listLIS.add(x);
            } else {
                listLIS.set(j, x);
            }
        }
        return listLIS.size();
    }

    // 开区间写法
    private int lowerBound(List<Integer> listLIS, int target) {
        int left = -1;
        int right = listLIS.size();

        while (left + 1 < right) {
            int mid = (right - left) / 2 + left;

            if (listLIS.get(mid) < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
