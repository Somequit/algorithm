package leetcode.contest.contest_487;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2026/2/1 10:22 上午
 */
public class Contest_487_2 {
    public int finalElement(int[] nums) {
        return Math.max(nums[0], nums[nums.length - 1]);
    }
}
