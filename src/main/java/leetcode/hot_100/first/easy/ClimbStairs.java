package leetcode.hot_100.first.easy;

import utils.AlgorithmUtils;

import java.util.Arrays;

/**
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 1 <= n <= 45
 * @author gusixue
 * @date 2022/9/2
 */
public class ClimbStairs {

    public static void main(String[] args) {
        ClimbStairs climbStairs = new ClimbStairs();
        while (true) {
            int n = AlgorithmUtils.systemInNumberInt();

            int res = climbStairs.solution(n);
            System.out.println(res);
        }
    }

    private int solution(int n) {
        if (n <= 2) {
            return n;
        }

        int dp1 = 1;
        int dp2 = 2;
        int dp3 = dp1 + dp2;

        for (int i = 3; i < n; i++) {
            dp1 = dp2;
            dp2 = dp3;
            dp3 = dp1 + dp2;
        }

        return dp3;
    }
}
