package leetcode.brushQuestions.medium;

import java.util.TreeMap;

/**
 * @author gusixue
 * @description 3578. 统计极差最大为 K 的分割方式数
 * @date 2025/12/6 10:02 下午
 */
public class CountPartitions {

    public int countPartitions(int[] nums, int k) {
        int mod = (int) 1e9 + 7;
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        int n = nums.length;
        long segTotal = 1;
        treeMap.put(nums[0], 1);
        long[] dp = new long[n];
        dp[0] = 1;
        long prevDp = 1;
        for (int left = -1, right = 1; right < nums.length; right++) {
            treeMap.merge(nums[right], 1, Integer::sum);

            while (Math.abs(treeMap.firstKey() - nums[right]) > k || Math.abs(treeMap.lastKey() - nums[right]) > k) {
                left++;
                treeMap.merge(nums[left], -1, Integer::sum);
                if (treeMap.get(nums[left]) == 0) {
                    treeMap.remove(nums[left]);
                }
                segTotal -= dp[left];
                segTotal = (segTotal + mod) % mod;
                prevDp = dp[left];
            }

            dp[right] = (segTotal + prevDp) % mod;
            segTotal = (segTotal + dp[right]) % mod;

//            System.out.println(dp[right] + " : " + segTotal);
        }

        return (int) dp[n - 1];
    }

}
