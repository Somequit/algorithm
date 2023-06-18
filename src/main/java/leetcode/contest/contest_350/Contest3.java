package leetcode.contest.contest_350;

import utils.AlgorithmUtils;

import java.util.Arrays;


/**
 * @author gusixue
 * 6893. 特别的排列
 * 给你一个下标从 0 开始的整数数组 nums ，它包含 n 个 互不相同 的正整数。如果 nums 的一个排列满足以下条件，我们称它是一个特别的排列：
 * 对于 0 <= i < n - 1 的下标 i ，要么 nums[i] % nums[i+1] == 0 ，要么 nums[i+1] % nums[i] == 0 。
 * 请你返回特别排列的总数目，由于答案可能很大，请将它对 10^9 + 7 取余 后返回。
 * 2 <= nums.length <= 14
 * 1 <= nums[i] <= 10^9
 * @date 2023/5/14
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();

            int res = contest.solution(nums);
            System.out.println(res);
        }

    }

    /**
     * 状压 DP + 记忆化搜索：首先思考暴力解法，就是每一位去放置不同的数字，同时校验该位与前一位要满足正向或反向余数为 0，此时可以考虑记忆化优化，
     * 定义状态（记忆化存储的值）：dp[i] 中 i 为转化为二进制后存在哪些元素（如 5=101，代表存在下标 0、3 的元素，其他下标不存在），它的值为包含多少排列数，
     * 然后类似分支法将大区间拆分成小区间求值，如 dp[i]=dp[i-j]*dp[j]，同时还要满足前一个区间的尾与下一个区间的头余数为 0，因此 dp[i][j][k] 代表以 j 为头、k 为尾，
     * 初始化：可以将需要求解的数初始化为 -1，只有一种情况的数初始化为 1，其他数初始化为 0（相当于这种情况结果加 0），如果不小于 0 就直接返回
     *     如果 i 等于 0 代表没有任何数结果一定为 0
     *     如果 i 在二进制下仅有一个 1，则 j 与 k 相等、同时 i 对应二进制下的 1 的位置就是 j/k，此时结果为 1，其他情况为 0
     *     如果 i 在二进制下超过一个 1，则 j 与 k 不能相同、同时 i 对应二进制下的 1 包含 j 与 k，此时结果需要求出为 -1，其他情况为 0
     * 动规转移方程：dp[i][j][k] = dp[i-(1<<k)][j][l] * dp[1<<k][k][k]（nums[l] 是与 nums[k] 余数为 0 的一系列数，同时 l 必须存在 i 转化为的二进制位中）
     * 结果：当 i 在二进制下所有值都是 1 的情况下，所有的 j 与 k 的总和
     * 时间复杂度：O（2^n*n^2），空间复杂度：O（2^n*n^2）
     */
    public int solution(int[] nums) {
        int mod = 1_000_000_007;

        int len = nums.length;
        // 状压所有元素的最大值（全为 1 的值）
        int permTotal = (1 << len) - 1;

        // 定义状态并初始化 dp
        long[][][] dp = initDp(permTotal + 1, len);

        // 创建二维数组快速获取 nums[i] 与 nums[j] 是否正向/反向互除为 0（i != j）
        boolean[][] special = initSpecial(nums, len);

        // 当 i 在二进制下所有值都是 1 的情况下，所有的 j 与 k 的总和
        return doSpecialPerm(nums, len, dp, permTotal, special, mod);
    }

    /**
     * 定义状态并初始化 dp
     */
    private long[][][] initDp(int permTotal, int len) {
        long[][][] dp = new long[permTotal][len][len];

        for (int i = 1; i < permTotal; i++) {
            for (int j = 0; j < len; j++) {
                for (int k = 0; k < len; k++) {
                    // i 在二进制下仅有一个 1
                    if (i - (lowbit(i)) == 0) {
                        // i 对应二进制下的 1 的位置就是 j/k
                        if (j == k && (1 << j) == i) {
                            // 仅有这一种情况
                            dp[i][j][k] = 1;
                        }
                    // i 在二进制下超过一个 1
                    } else {
                        // i 对应二进制下的 1 包含 j 与 k
                        if (j != k && (i & (1 << j)) > 0 && (i & (1 << k)) > 0) {
                            // 这些才是超过一个 1 并且符合条件的情况（需要求解，不符合条件默认赋值为 0）
                            dp[i][j][k] = -1;
                        }
                    }
                }
            }
        }

//        System.out.println(permTotal);
//        for (int i = 1; i < permTotal; i++) {
//            for (int j = 0; j < len; j++) {
//                for (int k = 0; k < len; k++) {
//                    System.out.print(i + ":" + j + ":" + k + ":" + dp[i][j][k] + " ");
//                }
//            }
//            System.out.println();
//        }

        return dp;
    }

    /**
     * 返回 i 二进制下最后一个 1 形成的值、即将最后一个 1 前面的值删除
     */
    private int lowbit(int i) {
        return i & -i;
    }

    /**
     * 创建二维数组快速获取 nums[i] 与 nums[j] 是否正向/反向互除为 0（i != j）
     */
    private boolean[][] initSpecial(int[] nums, int len) {
        boolean[][] special = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i != j && (nums[i] % nums[j] == 0 || nums[j] % nums[i] == 0)) {
                    special[i][j] = true;
                }
            }
        }
        return special;
    }

    /**
     * 当 i 在二进制下所有值都是 1 的情况下，所有的 j 与 k 的总和
     */
    private int doSpecialPerm(int[] nums, int len, long[][][] dp, int permTotal, boolean[][] special, int mod) {
        int res = 0;
        for (int j = 0; j < len; j++) {
            for (int k = 0; k < len; k++) {
                if (j == k) {
                    continue;
                }
                // 递归 + 记忆化搜索
                dfs(dp, permTotal, j, k, len, special, mod);
//                System.out.println(j + " : " + k + " : " + dp[permTotal][j][k]);
                res += dp[permTotal][j][k];
                res %= mod;
            }
        }
        return res;
    }

    /**
     * 递归 + 记忆化搜索
     */
    private long dfs(long[][][] dp, int perm, int start, int end, int len, boolean[][] special, int mod) {
        if (dp[perm][start][end] >= 0) {
            return dp[perm][start][end];
        }

        long res = 0;
        for (int i = 0; i < len; i++) {
            // nums[i] 是与 nums[end] 余数为 0 的一系列数，同时 l 必须存在 i 转化为的二进制位中、否则返回 0
            if (special[i][end]) {
                res += dfs(dp, perm - (1 << end), start, i, len, special, mod) * dp[1 << end][end][end];
                res %= mod;
            }
        }
        // 记忆化
        dp[perm][start][end] = res;

        return dp[perm][start][end];
    }

}
