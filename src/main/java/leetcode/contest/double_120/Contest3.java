package leetcode.contest.double_120;

import java.util.*;
/**
 * 10033. 统计移除递增子数组的数目 II
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

    /**
     * Java + 双指针 + 前缀和:
     *
     * 第 1 步：
     * 分析题意可得，如果移除区间 [x,y] 后剩下为递增数组，那么移除 [x-1,y]、[x-2,y]... 后也剩下递增数组，
     * 因此我们想到枚举每个 y，找到最大的 x，然后 [0,x] 均可作为移除的左区间
     *
     * 第 2 步：
     * 从左往右枚举 y 时，x 满足条件需要
     *     1. [y+1, n-1] 为递增数组
     *     2. [0,x-1] 也为自增数组
     *     3. nums[x-1] < nums[y+1]
     * 根据上述可以发现 y 增大时，最大的 x 要么不变、要么增加，使用双指针的方式求解
     *
     * 第 3 步：
     * 具体做法：
     * 预处理前缀与后缀是否满足严格递增，
     * 枚举 y 时，如果前缀与后缀均递增，找到最大的 x，
     * 可以删除 [0,y] ... [x,y]，因此答案加上 x+1
     *
     * 时间复杂度：O（n），空间复杂度：O（n）
     *
     */
    public long incremovableSubarrayCount(int[] nums) {
        int n = nums.length;
        // 前缀 [0,i] 是否为递增数组
        boolean[] prefix = new boolean[n];
        prefix[0] = true;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                prefix[i] = true;

            } else {
                break;
            }
        }

        // 后缀 [i,n-1] 是否为递增数组，为方便处理多加一个 n
        boolean[] suffix = new boolean[n + 1];
        suffix[n] = true;
        suffix[n - 1] = true;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                suffix[i] = true;

            } else {
                break;
            }
        }

        long res = 0L;
        // 枚举移除的右端点时，最大的左端点值
        int maxLeft = 0;
        for (int right = 0; right < n; right++) {
            // 左端点小于右端点，左端点递增，右端点后面递增，右端点后面均大于左端点
            while (right > maxLeft && prefix[maxLeft] && suffix[right + 1]
                    && (right == n - 1 || nums[right + 1] > nums[maxLeft])) {
                maxLeft++;
            }

            // maxLeft++ 时已经保证了左端点递增，因此只需要看右端点后面递增即可
            if (suffix[right + 1]) {
                res += maxLeft + 1;
            }

        }

        return res;
    }


}
