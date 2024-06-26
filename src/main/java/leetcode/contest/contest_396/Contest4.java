package leetcode.contest.contest_396;

import utils.AlgorithmUtils;

import java.util.*;
/**
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
            int[] nums = AlgorithmUtils.systemInArray();
            int cost1 = AlgorithmUtils.systemInNumberInt();
            int cost2 = AlgorithmUtils.systemInNumberInt();

            int res = contest.minCostToEqualizeArray(nums, cost1, cost2);
            System.out.println(res);
        }

    }

    /**
     * @return
     */
    public int minCostToEqualizeArray1(int[] nums, int cost1, int cost2) {
        int mod = (int) (1e9 + 7);

        Arrays.sort(nums);
        int n = nums.length;

        long res1 = 0;
        for (int i = 0; i < n - 1; i++) {
            res1 += (long) (nums[n - 1] - nums[i]) * cost1;
        }

        if (cost1 * 2 <= cost2 || n <= 2 || res1 == 0) {
            return (int) (res1 % mod);
        }

        long res2 = 0;
        long firstVal = nums[n - 1] - nums[0];
        long otherVal = 0;
        for (int i = 1; i < n; i++) {
            otherVal += nums[n - 1] - nums[i];
        }
//        System.out.println(firstVal + " : " + otherVal);

        if (otherVal <= firstVal) {
            res2 = (long) cost2 * otherVal;
            firstVal -= otherVal;

        } else {
            res2 = (long) cost2 * ((firstVal + otherVal) / 2);
            firstVal = (firstVal + otherVal) % 2;
        }

//        System.out.println(firstVal);
//        System.out.println(res1 + " : " + res2);

        if (res2 >= res1) {
            return (int) (res1 % mod);
        }

        if (firstVal == 0) {
            return (int) (res2 % mod);
        }

        long res3 = res2 + (long) cost1 * firstVal;
        if (res3 < res1) {
            res1 = res3;
        }

        for (int i = 0; i < firstVal; i++) {
            res3 = res2 + (long) cost1 * i;
            if (res3 >= res1) {
                break;
            }

            long firstValTemp = firstVal - i;

//            System.out.println(res3);
            long countCost2 = doCountMinCostToEqualizeArray(firstValTemp, n);
            if (countCost2 == -1) {
                continue;
            }
            res3 += (long) cost2 * countCost2;

            res1 = Math.min(res1, res3);
        }


        return (int) (res1 % mod);
    }

    private long doCountMinCostToEqualizeArray(long firstVal, int n) {
        long res = 0;
        long firstValTemp = firstVal;
        if (n % 2 == 0) {
            if (firstValTemp % 2 == 1) {
                return -1;
            }

            res += (long) (n - 1) * (firstValTemp / (n - 2));
            firstValTemp %= n - 2;
            if (firstValTemp > 0) {
                res += firstValTemp + 1 + (n - firstValTemp - 2) / 2;
            }

        } else {
            res += (long) (n - 1) * (firstValTemp / (n - 2));
            firstValTemp %= n - 2;
            if (firstValTemp > 0) {
                if (firstValTemp % 2 == 1) {
                    res += firstValTemp + 1 + (n - firstValTemp - 2) / 2;

                } else {
                    res += firstValTemp + (n - firstValTemp - 1) / 2;
                    firstValTemp = 1;
                    res += firstValTemp + 1 + (n - firstValTemp - 2) / 2;
                }
            }

        }

//        System.out.println(firstVal + " : " + n + " : " + res);

        return res;
    }

    public int minCostToEqualizeArray(int[] nums, int cost1, int cost2) {
        int mod = (int) (1e9 + 7);
        int n = nums.length;

        Arrays.sort(nums);
        long res = 0;
        long suffixSum = -nums[0];
        for (int i = 0; i < n; i++) {
            res += (long) cost1 * (nums[n - 1] - nums[i]);
            suffixSum += nums[i];
        }

        if (cost1 * 2 <= cost2 || n <= 2 || res == 0) {
            return (int) (res % mod);
        }

        // 全部转化为 i+nums[n-1]（即最大为这个数）
        for (int i = 0; i < nums[n - 1] + 2; i++) {
            long first = i + nums[n - 1] - nums[0];
            long other = ((long) (i + nums[n - 1]) * (n - 1)) - suffixSum;

            long resTemp = 0;
            // 第一个相差比后面的少，则全使用 cost2 一定只剩下 0/1 个
            if (first <= other) {
                resTemp = (long) cost2 * ((first + other) / 2);
                if ((first + other) % 2 == 1) {
                    resTemp += cost1;
                }

                // 第一个用不完，全使用 cost1 填补
            } else {
                resTemp = (long) cost2 * other + (long) cost1 * (first - other);
            }

            res = Math.min(res, resTemp);
        }

        return (int) (res % mod);
    }


}
