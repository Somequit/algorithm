package leetcode.contest.double_127;

import java.util.*;
/**
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

//            int res = contest.solution(n, nums, list);
//            System.out.println(res);
        }

    }

    /**
     * 枚举最小值，计算此时子序列的个数，才能做到不重不漏，
     * 由于排序不会影响子序列，因此固定 i 前后相邻元素差一定不比跨元素差大，
     * 子序列可以使用动态规划，dp[i][j][l] 前 i 个元素(第一维可以删除)选 j 个元素，最后一个元素为 l，这样下一个选择的元素只需要判断与 l 的差就是其最小值（保证大于等于枚举的最小值），
     * 未解决多个最小值重复计算问题，仅查第一次出现的最小值，因此前面的最小值大于其，后面的最小值大于等于其
     */
    public int sumOfPowers(int[] nums, int k) {
        int mod = (int) (1e9 + 7);
        Arrays.sort(nums);

        long res = 0L;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (k == 2) {
                    res += nums[j] - nums[i];
                    res %= mod;
                    continue;
                }

                int minDiff = nums[j] - nums[i];

                long[][] dpLeft = new long[k - 1][i];
                for (int leftI = i - 1; leftI >= 0; leftI--) {
                    // 前面的最小值大于其
                    if (nums[i] - nums[leftI] > minDiff) {
                        doPowersDp(dpLeft, 0, leftI + 1, minDiff, Math.min(k - 2, leftI + 1), nums, mod);
                        break;
                    }
                }

                long[][] dpRight = new long[k - 1][n - j - 1];
                for (int rightI = j + 1; rightI < n; rightI++) {
                    // 后面的最小值大于等于其
                    if (nums[rightI] - nums[j] >= minDiff) {
                        doPowersDp(dpRight, rightI, n, minDiff - 1, Math.min(k - 2, n - rightI), nums, mod);
                        break;
                    }
                }

                // 左右选择个数 * 最小值
                res = doPowersCount(res, dpLeft, dpRight, k, minDiff, mod);
//                System.out.println(i + " : " + j + " : " + res + " : ");
            }
        }

        return (int) (res % mod);
    }

    private long doPowersCount(long res, long[][] dpLeft, long[][] dpRight, int k, int minDiff, int mod) {
        // 左边选 j 个，右边选 k-2-j 个
        for (int j = 0; j <= k - 2; j++) {
            long leftCount = 0L;
            for (int l = 0; l < dpLeft[j].length; l++) {
                leftCount += dpLeft[j][l];
                leftCount %= mod;
            }

            long rightCount = 0L;
            for (int l = 0; l < dpRight[k - 2 - j].length; l++) {
                rightCount += dpRight[k - 2 - j][l];
                rightCount %= mod;
            }

            // 仅选左边与仅选右边，则另一边 count 一定为 0
            if (j == 0) {
                leftCount = 1;

            } else if (j == k - 2) {
                rightCount = 1;
            }

//            System.out.println(j + " : " + leftCount + " : " + rightCount);
            res += minDiff * (leftCount * rightCount % mod);
            res %= mod;
        }

        return res;
    }

    /**
     * 计算此时子序列的个数
     */
    private void doPowersDp(long[][] dp, int begin, int end, int minDiff, int count, int[] nums, int mod) {
        for (int i = 0; i < end - begin; i++) {
            dp[1][i] = 1;
        }

        for (int i = 1; i < end - begin; i++) {
            for (int j = 2; j <= count; j++) {
                for (int l = j - 2; l < i; l++) {
                    // 可选
                    if (nums[i + begin] - nums[l + begin] > minDiff) {
                        dp[j][i] += dp[j - 1][l];
                        dp[j][i] %= mod;
                    }
                }
            }
        }

//        System.out.println("begin:" + begin + " : " + end + " : " + minDiff);
//        Arrays.stream(dp).forEach(i -> System.out.println(Arrays.toString(i)));

    }

}
