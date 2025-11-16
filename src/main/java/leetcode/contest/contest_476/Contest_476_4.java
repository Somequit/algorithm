package leetcode.contest.contest_476;

import utils.AlgorithmUtils;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/11/15 10:37 下午
 */
public class Contest_476_4 {

    public static void main(String[] args) {
        Contest_476_4 contest = new Contest_476_4();

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
            int[][] queries = AlgorithmUtils.systemInTwoArray();

            long[] res = contest.countStableSubarrays(nums, queries);
            AlgorithmUtils.systemOutArray(res);
        }

    }

    /**
     * 预处理前缀非递减的最大个数 prefix，预处理后缀非递增的最大个数 suffix，预处理 [0,i]闭区间 的稳定子数组个数 prefixRes，
     * 使用区间减法，即可 O(1) 求得 [i,j]闭区间 的稳定子数组个数，
     * 注意区间减法后需要减去 nums[i] 前后形成的稳定子数组，
     * 注意 nums[i] 后面非递减个数是否足够
     * 时间复杂度：O（n+q），空间复杂度：O（n）
     */
    public long[] countStableSubarrays(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] prefix = new int[n];
        prefix[0] = 1;
        int cnt = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] >= nums[i - 1]) {
                cnt++;

            } else {
                cnt = 1;
            }

            prefix[i] = cnt;
        }
//        System.out.print("prefix:");
//        System.out.println(Arrays.toString(prefix));

        int[] suffix = new int[n];
        suffix[n - 1] = 1;
        cnt = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i+1] >= nums[i]) {
                cnt++;

            } else {
                cnt = 1;
            }
            suffix[i] = cnt;
        }
//        System.out.print("suffix:");
//        System.out.println(Arrays.toString(suffix));

        long[] prefixRes = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixRes[i] += prefixRes[i - 1] + prefix[i - 1];
        }

        int m = queries.length;
        long[] res = new long[m];
        for (int i = 0; i < m; i++) {
            res[i] = prefixRes[queries[i][1] + 1] - prefixRes[queries[i][0]] - ((long) Math.min(suffix[queries[i][0]], queries[i][1] - queries[i][0] + 1) * (prefix[queries[i][0]] - 1));
        }

        return res;
    }


}
