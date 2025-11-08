package leetcode.contest.double_169;

import java.awt.*;
import java.util.*;

/**
 * Q3. 替换至多一个元素后最长非递减子数组
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

    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int[] suffixCnt = new int[n];
        suffixCnt[n - 1] = 1;
        int res = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] <= nums[i + 1]) {
                suffixCnt[i] = suffixCnt[i + 1] + 1;
                res = Math.max(res, suffixCnt[i]);

            } else {
                suffixCnt[i] = 1;
            }
        }
//        System.out.println(Arrays.toString(suffixCnt));

        int prefixCnt = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] >= nums[i - 1]) {
                prefixCnt++;

            } else {
                if (i == 1) {
                    res = Math.max(res, suffixCnt[i] + 1);

                } else if (i == n - 1) {
                    res = Math.max(res, prefixCnt + 1);

                } else {
                    if (nums[i - 2] <= nums[i] || nums[i + 1] >= nums[i - 1]) {
                        res = Math.max(res, prefixCnt + suffixCnt[i]);

                    } else {
                        res = Math.max(res, Math.max(suffixCnt[i] + 1, prefixCnt + 1));
                    }
                }

                prefixCnt = 1;
            }
        }

        return res;
    }

}
