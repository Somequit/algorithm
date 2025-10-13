package leetcode.brushQuestions.easy;

import java.util.List;

/**
 * @author gusixue
 * @description 3349. 检测相邻递增子数组 I
 * @date 2025/10/14 5:07 上午
 */
public class HasIncreasingSubarrays {

    /**
     * 1.暴力求 两次练习 k 个是否递增，时间复杂度：O（n^2），空间复杂度：O（1）
     * 2.如果 nums[i]>nums[i-1] 则存储 1 到 dp[i] 中，最后找是否存在 2k-1 的子数组，保证 前 k-1 个是 1 与后 k-1 个是 1（可用前缀和做），时间复杂度：O（n），空间复杂度：O（n）
     */
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        if (k == 1) {
            return true;
        }

        int n = nums.size();
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + ((nums.get(i) > nums.get(i - 1)) ? 1 : 0);

            if (i >= k * 2 - 1 && dp[i] - dp[i - k + 1] == k - 1 && dp[i - k] - dp[i - 2 * k + 1] == k - 1) {
                return true;
            }
        }

        return false;
    }
}
