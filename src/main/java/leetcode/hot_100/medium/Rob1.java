package leetcode.hot_100.medium;

import utils.AlgorithmUtils;

/**
 * @author gusixue
 * @description
 * 198. 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 * @date 2023/11/12
 */
public class Rob1 {

    public static void main(String[] args) {
        Rob1 rob1 = new Rob1();

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();

            int res = rob1.solution(nums);
            System.out.println(res);
        }

    }

    /**
     * 线性 dp：状态 dp[i] 代表偷前 i 间的最高金额，初始化 dp[0]=nums[0],dp[1]=max(nums[0], nums[1])，
     * 转移方程：dp[i]=max(dp[i-2]+nums[i], dp[i-1])
     * @param nums
     * @return
     */
    private int solution(int[] nums) {
        // 特判
        if (nums == null) {
            return 0;

        } else if (nums.length == 1) {
            return nums[0];
        }

        int dp0 = nums[0];
        int dp1 = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            int temp = Math.max(dp0 + nums[i], dp1);
            dp0 = dp1;
            dp1 = temp;
        }

        return dp1;
    }

}
