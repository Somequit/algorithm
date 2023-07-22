package leetcode.contest.double_109;

import utils.AlgorithmUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author gusixue
 * 6922. 将一个数字表示成幂的和的方案数
 * 给你两个 正 整数 n 和 x 。
 * 请你返回将 n 表示成一些 互不相同 正整数的 x 次幂之和的方案数。换句话说，你需要返回互不相同整数 [n1, n2, ..., nk] 的集合数目，
 * 满足 n = n1 ^ x + n2 ^ x + ... + nk ^ x 。
 * 由于答案可能非常大，请你将它对 10 ^ 9 + 7 取余后返回。
 * 比方说，n = 160 且 x = 3 ，一个表示 n 的方法是 n = 23 + 33 + 53 。
 * 1 <= n <= 300
 * 1 <= x <= 5
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

    /**
     * dfs + dp：如果 x 大于 1，最多也就 17 个数（即 300,2），因此找出 自然数 中所有可以小于等于 n 的 x 的次方，直接使用 dfs 即可，
     * 当 x 等于 1 时，求的是 [1,n] 中每个数用一次，组成 n 的方案数，使用动态规划
     * 定义状态：dp[i][j] 代表组成 i 且所有元素小于等于 j 的方案数
     * 定义转移方程：第一个数为 j（j 范围 [1,i]）、剩下组成 i-j 且所有元素小于等于 j-1 的方案数的 总和 就能不重不漏的加到 dp[i][k] 中（k 范围 [j,i]），
     *     dp[i][i] += dp[i - j][Math.min(j - 1, i - j)]; 1 <= j <= i  dp[i][i-1] += dp[i - j][Math.min(j - 1, i - j)]; 1 <= j <= i-1 ...
     *     Math.min(j - 1, i - j) 原因：因为 j-1=b > a=i-j、dp[a][a] = dp[a][b]，但是 dp[a][b] 不需要赋值、因此为了方便直接用 dp[a][a]
     * 初始化：因为任意数 n 可以由 n+0 组成，因此可以设 dp[0] 均为 1，dp[1][1] 代表组成 1 仅用 1、方案数为 1
     * 举例理解上述：n=9 时，9+0（第一个为 9、剩下 0，仅 1 中方案）、8+1（第一个为 8、剩下 1，仅 1 中方案）、7+2（第一个为 7、剩下 2，仅 1 中方案）、
     *     6+3|6+2+1（第一个为 6、剩下 3 可以划分 2 种方案）、5+4|5+3+1（第一个为 5、剩下 4 可以划分 2 种方案）、
     *     4+3+2（第一个为 4、剩下 5 但是剩下的数字不能大于等于 4，可以划分 1 种方案）、3无、2无、1无，8 种情况
     * 优化时间：j=1 时需要加到 [1,i] 中，j=2 时需要加到 [2,i] 中...可以使用前缀和：j=1 时加到 1、j=2 时将（1+2）加到 2、j=3 时将（1+2+3）加到 3...
     * 时间复杂度：O（min(n*n,2 ^ 17)），空间复杂度：O（n*n）
     */
    private int solution(int n, int x) {
        int mod = 1_000_000_007;

        // dfs 直接暴力
        if (x > 1) {

            // 找出 自然数 中所有可以小于等于 n 的 x 的次方
            List<Integer> nums = new ArrayList<>();
            for (int i = 1; i < 20; i++) {
                int temp = (int) Math.pow(i, x);
                if (temp > n) {
                    break;
                } else {
                    nums.add(temp);
                }
            }

            // 每个数 选择/不选择 直接暴力
            return dfs(0, n, 0, nums.size(), nums);

        // [1,n] 中每个数用一次，组成 n 的方案数
        } else {
            // dp[i][j] 代表组成 i 且所有元素小于等于 j 的方案数
            int[][] dp = new int[n + 1][n + 1];

            // 因为任意数 n 可以由 n+0 组成，因此可以设 dp[0] 均为 1，dp[1][1] 代表组成 1 仅用 1、方案数为 1
            Arrays.fill(dp[0], 1);
            dp[1][1] = 1;

            for (int i = 2; i < n + 1; i++) {
                int prefix = 0;
                for (int j = 1; j <= i; j++) {
//                    for (int k = j; k <= i; k++) {
//
//                        dp[i][k] += dp[i - j][Math.min(j - 1, i - j)];
//                        dp[i][k] %= mod;
//                    }

                    // 第一个数为 j（j 范围 [1,i]）、剩下组成 i-j 且所有元素小于等于 j-1 的方案数的 总和 就能不重不漏的加到 dp[i][k] 中（k 范围 [j,i]）
                    dp[i][j] += (prefix + dp[i - j][Math.min(j - 1, i - j)]) % mod;
                    dp[i][j] %= mod;

                    // 使用前缀和：j=1 时加到 1、j=2 时将（1+2）加到 2、j=3 时将（1+2+3）加到 3...
                    prefix += dp[i - j][Math.min(j - 1, i - j)];
                    prefix %= mod;

                }
            }

            return dp[n][n];
        }
    }

    /**
     * 每个数 选择/不选择 直接暴力
     */
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
