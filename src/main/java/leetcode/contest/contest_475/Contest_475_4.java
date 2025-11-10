package leetcode.contest.contest_475;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/11/8 11:45 下午
 */
public class Contest_475_4 {

    /**
     * 设 Max = max(nums[i])，Min = min(nums[i])，Max或Min 一定再某个子数组中被选择，
     * 保证 Max或Min 在区间边缘一定不会差于最优，假设选择 Max 在区间边缘，包含 Max 的子数组中：
     *     最小值如果在左边，那么 Max 右边元素可以划分给下一个子数组，本子数组不会改变，其他子数组不会总和变小
     *     最小值如果在右边，那么 Max 左边元素可以划分给上一个子数组，本子数组不会改变，其他子数组不会总和变小
     * 因此，我们可以在 任意Max 前后断环，即（i，i+1） 或（i-1,i） 处断
     *
     * 其次问题可以转化为：选择不大于 k 个、如 k` 的值，分成 k` 个区间、选择其中最大值减去最小值，再求和
     * 再转化为：选择 k` 个值的和 减去 k` 个值的和，最终结果求最大，因为"保证总和最大"就一定是区间内最大值减最小值才行，
     * 由于子数组只有 1 个值时结果为 0，子数组超过 1 个值结果 >= 0，因此子数组内最优为全部选择不同下标的值，次数子数组 k 个数不会超过 n/2 下取整
     *
     * 使用三个 dp，dp[i][j] 代表前 i 个元素选择 j 对值相减的最大值，dpAdd[i][j] 代表前 i 个元素选择 j 对值相减且加比减多一个的最大值，dpSub 是减比加多一个
     * 初始化 dpSub 转化为 负无穷，因为 dpSub 是可能为负数，其他使用默认值 0
     * 顺序遍历 nums，当下标为 i 时，nums[i] 有三种选择方式：不选/+nums[i]/-nums[i]，根据三种方式获得 dp 的通项公式
     * dp[i][j] = max(dp[i-1][j], dpAdd[i-1][j]-nums[i], dpSub[i-1][j]+nums[i]) ，1<=j<=min((i+1)/2, k)，j 是选择对数，因此为 i 的 2 倍
     * dpAdd[i][j] = max(dpAdd[i-1][j], dp[i-1][j-1]+nums[i])，1<=j<=min((i+2)/2, k)，j 是选择对数少 1 个
     * dpSub[i][j] = max(dpSub[i-1][j], dp[i-1][j-1]-nums[i])，1<=j<=min((i+2)/2, k)，j 是选择对数少 1 个
     * 第一维可以使用滚动数组优化
     */
    public long maximumScore(int[] nums, int k) {
        int n = nums.length;
        int maxIndex = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        return Math.max(doMaximumScore(nums, maxIndex, Math.min(k, n / 2)), doMaximumScore(nums, (maxIndex + 1) % n, Math.min(k, n / 2)));
    }

    private long doMaximumScore(int[] nums, int start, int k) {
        int n = nums.length;
        long INF = (long) 1e18;

        long[][] dp = new long[2][k + 1];
        long[][] dpAdd = new long[2][k + 1];
        long[][] dpSub = new long[2][k + 1];
        Arrays.fill(dpSub[0], -INF);
        Arrays.fill(dpSub[1], -INF);

        long res = 0;
        int i = start;
        int l = 0;
        do {
//            System.out.println(i + " : " + nums[i]);
            for (int j = 1; j <= Math.min((l + 1) / 2, k); j++) {
                dp[l & 1][j] = Math.max(Math.max(dpAdd[(l + 1) & 1][j] - nums[i], dpSub[(l + 1) & 1][j] + nums[i]), dp[(l+ 1) & 1][j]);
                res = Math.max(dp[l & 1][j], res);
            }

            for (int j = 1; j <= Math.min((l + 2) / 2, k); j++) {
                dpAdd[l & 1][j] = Math.max(dp[(l + 1) & 1][j - 1] + nums[i], dpAdd[(l + 1) & 1][j]);
                dpSub[l & 1][j] = Math.max(dp[(l + 1) & 1][j - 1] - nums[i], dpSub[(l + 1) & 1][j]);
            }
            i = (i + 1) % n;
            l++;

//            System.out.println(Arrays.toString(dp[l & 1]));
//            System.out.println(Arrays.toString(dpAdd[l & 1]));
//            System.out.println(Arrays.toString(dpSub[l & 1]));
        } while (i != start);

        return res;
    }
}
