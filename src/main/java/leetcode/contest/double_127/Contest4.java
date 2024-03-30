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
     * @return
     */
    public int sumOfPowers(int[] nums, int k) {
        Arrays.sort(nums);

        long res = 0L;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (k == 2) {
                    res += nums[j] - nums[i];
                    res %= MOD;
                    continue;
                }

                // 总 - 右边小的 = 剩下
                for (int l = j + 1; l < n; l++) {
                    if (nums[l] - nums[j] >= nums[j] - nums[i]) {
                        System.out.println(i + " : " + j + " : " + l);
                        res += (nums[j] - nums[i]) * doCombinationRight(l, n, k - 2, nums[j] - nums[i], nums);
                        res %= MOD;
                        break;
                    }
                }


                // 剩下 - 左边小于等于的
                for (int l = i - 1; l >= 0; l--) {
                    if (nums[i] - nums[l] >= nums[j] - nums[i]) {
                        System.out.println(i + " : " + j + " : " + l);
                        res = res + (nums[j] - nums[i])
                                * (MOD - doCombinationLeft(0, l + 1, k - 2, nums[j] - nums[i], nums));
                        res %= MOD;
                        break;
                    }
                }

            }
        }

        return (int) (res % MOD);
    }

    private long doCombinationLeft(int begin, int end, int k, int minVal, int[] nums) {
        if (end - begin < k) {
            return 0L;
        }

        if (k == 1) {
            return end - begin;
        }

        long res = 0;

        for (int i = begin; i < end; i++) {
            for (int j = i + 1; j < end; j++) {
                if (nums[j] - nums[i] <= minVal && end - j - 1 >= k - 2) {
                    res = res + COMB[end - j - 1][k - 2];
                    System.out.println((end - j - 1) + " : " + (k - 2) + " : " + COMB[end - j - 1][k - 2]);
                    res %= MOD;

                } else {
                    break;
                }
            }
        }

        return res;
    }

    private long doCombinationRight(int begin, int end, int k, int minVal, int[] nums) {
        if (end - begin < k) {
            return 0L;
        }

        if (k == 1) {
            return end - begin;
        }

        int n = end - begin;
        long res = COMB[n][k];
        System.out.println(n + " : " + k + " : " + COMB[n][k]);

        for (int i = begin; i < end; i++) {
            for (int j = i + 1; j < end; j++) {
                if (nums[j] - nums[i] < minVal && end - j - 1 >= k - 2) {
                    res = res + MOD - COMB[end - j - 1][k - 2];
                    System.out.println((end - j - 1) + " : " + (k - 2) + " : " + COMB[end - j - 1][k - 2]);
                    res %= MOD;

                } else {
                    break;
                }
            }
        }

        return res;
    }

    private static final long COMB[][];
    private static final int MOD = (int) (1e9 + 7);

    static {
        COMB = new long[51][51];
        for (int i = 0; i < 51; i++) {
            for (int j = 0; j <= i; j++) {
                COMB[i][j] = comb(i, j);
            }
        }
//        Arrays.stream(COMB).forEach(longArr -> System.out.println(Arrays.toString(longArr)));
    }

    private static long comb(int n, int m) {
        if (m == 0 || m == n) {
            return 1;
        }
        if (COMB[n][m] != 0) {
            return COMB[n][m];
        }
        // 赋值给res[n][m]并返回
        return COMB[n][m] = comb(n - 1, m) + comb(n - 1, m - 1) % MOD;
    }

}
