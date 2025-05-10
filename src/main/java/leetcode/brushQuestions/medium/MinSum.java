package leetcode.brushQuestions.medium;

import java.util.Arrays;

/**
 * @author gusixue
 * @description 2918. 数组的最小相等和
 * @date 2025/5/10 2:51 下午
 */
public class MinSum {

    /**
     * 判断 nums1 与 nums2 中 0 的个数为 zeroCount1、zeroCount2，总和分别为 sum1、sum2,
     * 如果 zeroCount1 与 zeroCount2 均为 0，则两者相等就是结果否则就不能更改
     * 如果其中一个为 0，则先将 0 改为 1 求出总和，然后判断有 0 的数组总和是否小于等于无 0 的数组总和，是则大的为结果，否则不能成功
     * 如果两者均不为 0，则先将 0 改为 1 求出总和，大的为结果
     */
    public long minSum(int[] nums1, int[] nums2) {
        long zeroCount1 = Arrays.stream(nums1).filter(i -> i == 0).count();
        long zeroCount2 = Arrays.stream(nums2).filter(i -> i == 0).count();
        long sum1 = Arrays.stream(nums1).mapToLong(i -> i).sum();
        long sum2 = Arrays.stream(nums2).mapToLong(i -> i).sum();

        if (zeroCount1 == 0 && zeroCount2 == 0) {
            return sum1 == sum2 ? sum1 : -1;

        } else if (zeroCount1 == 0) {
            return sum2 + zeroCount2 <= sum1 ? sum1 : -1;

        } else if (zeroCount2 == 0) {
            return sum1 + zeroCount1 <= sum2 ? sum2 : -1;

        } else {
            return Math.max(sum1 + zeroCount1, sum2 + zeroCount2);
        }
    }
}
