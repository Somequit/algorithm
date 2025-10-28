package leetcode.brushQuestions.easy;

import java.util.Arrays;

/**
 * @author gusixue
 * @description 3354. 使数组元素等于零
 * @date 2025/10/28 10:05 上午
 */
public class CountValidSelections {

    /**
     * 可看做某个位置值为 0，向左反向移动前缀和次，向右反向移动后缀和次，如果次数相同则全部清空
     * 因此如果前缀和 等于 后缀和则向两边走均可，如果前缀和 比 后缀和大 1 则初始向左走，小 1 则初始向右走，
     * 注意如果全为 0 则每个位置均可
     * 优化：前缀和边遍历变求，后缀和先求总和在减去前缀和
     * 时间复杂度：O（n），空间复杂度：O（1）
     */
    public int countValidSelections(int[] nums) {
        int n = nums.length;

        int total = Arrays.stream(nums).sum();
        int res = 0;
        int prefix = 0;
        for (int i = 0; i < n; i++) {
            prefix += nums[i];

            if (nums[i] == 0) {
                if (total - prefix == prefix) {
                    res += 2;

                } else if (Math.abs(total - 2 * prefix) == 1) {
                    res++;

                }
            }
        }

        return res;
    }
}
