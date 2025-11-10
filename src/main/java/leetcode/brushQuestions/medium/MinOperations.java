package leetcode.brushQuestions.medium;

import java.util.*;

/**
 * @author gusixue
 * @description 3542. 将所有元素变为 0 的最少操作次数
 * @date 2025/11/10 9:59 上午
 */
public class MinOperations {

    /**
     * 单调栈
     * 题目转化为：多个相同元素 nums[i] 凑成子数组，如果 nums[i] 比子数组内均大，则一次操作变成 0，否则只能分开，注意 0 不需要操作
     * 思考：
     *     从 0 考虑到 n-1，如果当前为 i，如果 nums[i] 等于 0 则不操作，否则倒序查询 j -> [0,i)
     *         如果 nums[i] 比 nums[j] 小，则继续搜索；
     *         如果 nums[i] 比 nums[j] 大，则退出，且操作 nums[i] 一次；
     *         如果 nums[i] 与 nums[j] 相同，则退出，不需要操作，因为可以和 nums[j] 一起操作；
     *     如果 nums[i] 比所有 nums[j] 均小，则第一次操作 nums[i]
     * 优化：
     *     使用递增单调栈，开始将 0 放入栈顶，可以处理 0 不操作与避免空栈
     *     从 0 考虑到 n-1，如当前下标为 i，依次与栈顶元素比较，
     *         如果 nums[i] 比栈顶元素小，则出栈，继续比较；
     *         如果 nums[i] 比栈顶元素大，则退出，且操作 nums[i] 一次；
     *         如果 nums[i] 等于栈顶元素，则退出，不需要操作；
     */
    public int minOperations(int[] nums) {
        int n = nums.length;
        Deque<Integer> stackAsc = new ArrayDeque<>();

        int res = 0;
        stackAsc.push(0);
        for (int i = 0; i < n; i++) {
            while (nums[i] < stackAsc.peekFirst()) {
                stackAsc.pollFirst();
            }

            if (nums[i] > stackAsc.peekFirst()) {
                stackAsc.push(nums[i]);

                res++;
            }
        }

        return res;
    }
}
