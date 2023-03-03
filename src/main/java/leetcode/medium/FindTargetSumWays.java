package leetcode.medium;

import utils.AlgorithmUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 494. 目标和
 * 给你一个整数数组 nums 和一个整数 target 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 1000
 * @author gusixue
 * @date 2023/2/26
 */
public class FindTargetSumWays {

    public static void main(String[] args) {
        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
            int target = AlgorithmUtils.systemInNumberInt();
            FindTargetSumWays findTargetSumWays = new FindTargetSumWays();

            int res = findTargetSumWays.solution(nums, target);
            System.out.println(res);
            int res2 = findTargetSumWays.solutionOptimization(nums, target);
            System.out.println(res2);
        }
    }

    /**
     * 由于 sum(nums[i]) 比较小，同时所有数字任意组合结果在 [-sum(nums[i]), sum(nums[i])]，因此可以使用 Map
     * 存储加减每一位数字后的所有值、存储出现的个数，用着些值再次加减下一位数、再次存储出现的个数，最后找 target 出现的个数就是结果
     * 时间复杂度 O（nums.length * sum(nums[i])），空间复杂度 O(sum(nums[i]))
     */
    private int solution(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Map<Integer, Integer> sumNumMap = new HashMap<>();
        sumNumMap.put(0, 1);
        for (int numA : nums) {

            Map<Integer, Integer> mapTemp = new HashMap<>(sumNumMap.size() * 3);
            for (Map.Entry<Integer, Integer> entry : sumNumMap.entrySet()) {
                int numB = entry.getKey();
                mapTemp.put(numB + numA, mapTemp.getOrDefault(numB + numA, 0) + entry.getValue());
                mapTemp.put(numB - numA, mapTemp.getOrDefault(numB - numA, 0) + entry.getValue());
            }

            sumNumMap = mapTemp;
        }

        return sumNumMap.getOrDefault(target, 0);
    }

    /**
     * sum 为所有数之和，neg 代表选择一部分用于减的数之和，posi 代表另一部分用于加的数之和，可得：posi - neg = target，posi + neg = sum
     * 因此 neg = （sum - target）/ 2，而 neg 是非负整数，因此 sum - target 大于 0 且为偶数
     * 我们考虑 dp[i][j] 代表前 i 个数选一部分之和为 j 的个数，不选第 i 位，dp[i][j] = dp[i - 1][j]，选第 i 位，dp[i][j] = dp[i - 1][j - nums[j]]
     * 可以获得递推方程式：
     * j < nums[i] 时，dp[i][j] = dp[i - 1][j]
     * j >= nums[i] 时，dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[j]]
     * 同时可以看到第一维 i 仅和 i - 1 相关，可以使用循环数组压缩空间，同时如果 j 反向循环还可以压缩成一维数组
     * 时间复杂度：O（nums.length * (sum(nums[i]）- target）） 空间复杂度：O（sum(nums[i]） - target）
     */
    private int solutionOptimization(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 必须大于 0 且为偶数
        if (sum - target < 0 || ((sum - target) & 1) == 1) {
            return 0;
        }

        int neg = (sum - target) >> 1;
        int[] dp = new int[neg + 1];
        dp[0] = 1;

        for (int i = 0; i < nums.length; i++) {
            // 反向循环即可完美覆盖
            for (int j = neg; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }

        return dp[neg];
    }
}
