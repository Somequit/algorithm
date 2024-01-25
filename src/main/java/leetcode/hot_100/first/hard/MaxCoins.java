package leetcode.hot_100.first.hard;

import utils.AlgorithmUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author gusixue
 * @description
 * 312. 戳气球
 * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。
 * 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或
 * i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
 * 求所能获得硬币的最大数量。
 * n == nums.length
 * 1 <= n <= 300
 * 0 <= nums[i] <= 100
 * @date 2023/6/4
 */
public class MaxCoins {

    public static void main(String[] args) {
        MaxCoins maxCoins = new MaxCoins();
        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
            int res = maxCoins.solution(nums);
            System.out.println(res);
        }
    }

    /**
     * 动态规划：设 val[i+1]=nums[i] 同时 val[0]=val[n+1]=1 头尾加 1 方便计算，然后定义 dp[i][j] 为戳破（i，j）开区间所有气球获得最多金币数量，
     * n 个气球所能获得的最大金币就是 dp[0][n+1] 的值，接下来求转移方程式
     * 转移方程式：我们假设最后戳破的是第 k 个气球、同时 i<k<j，可得转移方程式：dp[i][j] = Max(dp[i][k] + val[i]*val[k]*val[j] + dp[k][j])，
     * 代表（i，k）与（k，j）已经被戳破了、再加上第 k 个气球的金币
     * 使用记忆化搜索的方式递归获取结果，出口条件是：当 i>=j-1 时 dp[i][j]=0
     * 时间复杂度：O（n^3）dp[0][n+1] 区间为 n^2、然后迭代 k 为 n 次，空间复杂度：O（n^2）
     */
    private int solution(int[] nums) {
        // 判空
        if (nums == null || nums.length <= 0) {
            return 0;
        }

        // 设 val[i+1]=nums[i] 同时 val[0]=val[n+1]=1 头尾加 1 方便计算
        int[] val = getValByNums(nums);
        // valLen = n + 2
        int valLen = val.length;
        // System.out.println(Arrays.toString(val));

        // 记忆化搜索获取
        int[][] dp = new int[valLen][valLen];
        for (int i = 0; i < valLen; i++) {
            Arrays.fill(dp[i], -1);
        }
        recursionSearchDP(0, valLen-1, dp, val);

        return dp[0][valLen - 1];
    }

    /**
     * 记忆化搜索获取（left，right）获得的最大金币数
     */
    private int recursionSearchDP(int left, int right, int[][] dp, int[] val) {
        if (left >= right - 1) {
            return 0;
        }
        if (dp[left][right] >= 0) {
            return dp[left][right];
        }

        // 循环最后一个戳破的气球
        for (int k = left + 1; k < right; k++) {
            dp[left][right] = Math.max(dp[left][right], recursionSearchDP(left, k, dp, val)
                    + recursionSearchDP(k, right, dp, val) + val[left]*val[k]*val[right]);
        }

        return dp[left][right];
    }

    /**
     * 获取 val 数组，设 val[i+1]=nums[i] 同时 val[0]=val[n+1]=1 头尾加 1 方便计算
     */
    private int[] getValByNums(int[] nums) {
        int numLen = nums.length;
        int[] val = new int[numLen + 2];

        val[0] = 1;
        for (int i = 0; i < numLen; i++) {
            val[i+1] = nums[i];
        }
        val[val.length - 1] = 1;

        return val;
    }

    /**
     * 打表找规律，根据全排列获取每种可能性
     */
    private void printBlock(int[] nums) {
        List<Integer> numList = new ArrayList<>(nums.length + 2);
        numList.add(1);
        for (int i = 0; i < nums.length; i++) {
            numList.add(nums[i]);
        }
        numList.add(1);
        System.out.println(Arrays.toString(nums) + ":" + numList);

        List<String> blockList = new ArrayList<>();
        recursionPermutation(numList, blockList, "");

        for (String block : blockList) {
            System.out.println(block);
        }
    }

    private void recursionPermutation(List<Integer> numList, List<String> blockList, String block) {
        if (numList.size() <= 2) {
            blockList.add(block.substring(0, block.length()-1));
            return;
        }

        int len = numList.size();
        for (int i = 1; i < len - 1; i++) {
            String blockNew = block + numList.get(i - 1) + "*" + numList.get(i) + "*" + numList.get(i + 1) + " ";
            Integer numTemp = numList.get(i);
            numList.remove(i);
            recursionPermutation(numList, blockList, blockNew);
            numList.add(i, numTemp);
        }
    }
}
