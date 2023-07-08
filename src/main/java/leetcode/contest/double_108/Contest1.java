package leetcode.contest.double_108;

import utils.AlgorithmUtils;


/**
 * @author gusixue
 * 6913. 最长交替子序列
 * 给你一个下标从 0 开始的整数数组 nums 。如果 nums 中长度为 m 的子数组 s 满足以下条件，我们称它是一个交替子序列：
 *     m 大于 1 。
 *     s1 = s0 + 1 。
 *     下标从 0 开始的子数组 s 与数组 [s0, s1, s0, s1,...,s(m-1) % 2] 一样。也就是说，s1 - s0 = 1 ，s2 - s1 = -1 ，s3 - s2 = 1 ，
 *     s4 - s3 = -1，以此类推，直到 s[m - 1] - s[m - 2] = (-1) ^ m 。
 * 请你返回 nums 中所有 交替 子数组中，最长的长度，如果不存在交替子数组，请你返回 -1 。
 * 子数组是一个数组中一段连续 非空 的元素序列。
 * 2 <= nums.length <= 100
 * 1 <= nums[i] <= 10 ^ 4
 * @date 2023/5/14
 */
public class Contest1 {

    public static void main(String[] args) {
        Contest1 contest = new Contest1();

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();

            int res = contest.solution(nums);
            System.out.println(res);
        }

    }

    /**
     * 双指针：左指针为 i，右指针为 j，初始化 i=0 j=1，j 循环直到数组结束，每次判断 [i,j] 是否满足交替，满足则更新最长长度，不满足则 i 后移到 j 或 j-1，
     * 假设符合要求的一段为：x、x+1、x、x+1...，当出现不符合的条件时可以分成几种情况（下标为 [i,j]）：
     *     x、非x+1：[j-1,j] 不符合要求，i 移动到 j（下一位） 继续
     *     x、x+1、x、非x+1：[j-1,j] 不符合要求，联合上面可知无论中间添加多少个（x+1、x），i 移动到 j 前面最迟均会在 j 失效，因此 i 只需要移动到 j 继续
     *
     *     x、x+1、x+2：[j-1,j] 符合要求，i 可以移动到 j-1（下一位） 继续
     *     x、x+1、x、x+1、x+2：[j-1,j] 符合要求，联合上面可知无论中间添加多少个（x、x+1），i 移动到 j-1 前面均会在 j 失效，因此 i 只需要移动到 j-1 继续
     *
     *     x、x+1、非x+2非x：[j-1,j] 不符合要求，所以 i 不用移动到下一位，直接移动到 j 继续
     *     x、x+1、x、x+1、非x+2非x：[j-1,j] 不符合要求，联合上面可知无论中间添加多少个（x、x+1），i 移动到 j 前面最迟均会在 j 失效，因此 i 只需要移动到 j 继续
     * 联合上述可得：[j-1,j] 不符合要求 i 移动到 j，[j-1,j] 符合要求 i 移动到 j-1
     * 时间复杂度：O（n），空间复杂度：O（1）
     */
    private int solution(int[] nums) {
        int res = -1;
        for (int i = 0, j = 1; j < nums.length; j++) {
            // 看做：x x+1 x x+1 ...当前不符合要求
            if (nums[j] != nums[i] + ((j - i) & 1)) {

                // j 与 [j-1,j] 均不符合要求，则 i 直接移动到 j 继续
                if (nums[j] != nums[j - 1] + 1) {
                    i = j;

                    // j 不符合要求但 [j-1,j] 符合要求，则 i 移动到最后符合要求的 j-1 继续
                } else {
                    i = j - 1;
                }

            } else {
                res = Math.max(res, j - i + 1);
            }
//            System.out.println(res + " : " + i);
        }
        return res;
    }



}
