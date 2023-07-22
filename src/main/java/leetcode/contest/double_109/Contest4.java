package leetcode.contest.double_109;

import utils.AlgorithmUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author gusixue
 * @date 2023/7/22
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
            int n = AlgorithmUtils.systemInNumberInt();
            int x = AlgorithmUtils.systemInNumberInt();

            int res = contest.solution(n, x);
            System.out.println(res);
        }

    }

    private int solution(int n, int x) {
        int mod = 1_000_000_007;


        if (x > 1) {
            List<Integer> nums = new ArrayList<>();
            for (int i = 1; i < 20; i++) {
                int temp = (int) Math.pow(i, x);
                if (temp > n) {
                    break;
                } else {
                    nums.add(temp);
                }
            }
//            System.out.println(nums);
            return dfs(0, n, 0, nums.size(), nums);
        } else {
            int[][] dp = new int[n + 1][n + 1];

            Arrays.fill(dp[0], 1);
            dp[1][1] = 1;

            for (int i = 2; i < n + 1; i++) {
                for (int j = 1; j <= i; j++) {
                    for (int k = j; k <= i; k++) {

                        dp[i][k] += dp[i - j][Math.min(j - 1, i - j)];
                        dp[i][k] %= mod;
                    }
                }
                // System.out.println(Arrays.toString(dp[i]));
            }


            return dp[n][n];
        }
    }

    private int dfs(int curNum, int n, int curI, int size, List<Integer> nums) {
        if (curNum == n) {
            return 1;
        }
        if (curNum > n || curI >= size) {
            return 0;
        }

        // 不选
        return dfs(curNum, n, curI + 1, size, nums) + dfs(curNum + nums.get(curI), n, curI + 1, size, nums);
    }


}
