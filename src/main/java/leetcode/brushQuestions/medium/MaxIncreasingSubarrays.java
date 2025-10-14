package leetcode.brushQuestions.medium;

import java.util.List;

/**
 * @author gusixue
 * @description 3350. 检测相邻递增子数组 II
 * @date 2025/10/15 4:03 上午
 */
public class MaxIncreasingSubarrays {

    /**
     * 设置一个是否递增数组 dp[n]，如果 nums[i]>nums[i-1] 则设置 dp[i]=1 否则 dp[i]=0，转化为：求存在长度为 2k-1 的子数组使得 前k-1 与 后k-1 个子数组均为1，
     * 然后 dp 数组原地改为前缀和数组，此时某个 nums子数组 (i,j) (j-i==2*k) 是否位俩连续递增子数组就可以转化为：dp[j]-dp[j-k+1] 与 dp[j-k]-dp[j-k*2+1] 均等于 k-1 即可，
     * 最后顺序遍历 dp 数组，依次求当前是否满足从 1 开始递增的 k，满足则 k++，不满足则判断后一位是否满足，显而易见 dp数组 中如果 (i,j) 满足 k，则代表 (i+1,j-1) 满足 k-1
     * 时间复杂度：O（n），空间复杂度：O（n）
     */
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
        int[] numsArr = nums.stream().mapToInt(Integer::intValue).toArray();
        int[] dp = new int[n];

        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + ((numsArr[i] > numsArr[i - 1]) ? 1 : 0);
        }

        int res = 1;
        for (int i = 1; i < n; i++) {
            if (i - 2 * res + 1 >= 0 && dp[i] - dp[i - res + 1] == res - 1 && dp[i - res] - dp[i - 2 * res + 1] == res - 1) {
                res++;
            }
        }

        return res - 1;
    }
}
