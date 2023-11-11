package leetcode.hot_100.easy;

import utils.AlgorithmUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 448. 找到所有数组中消失的数字
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 * 你能在不使用额外空间且时间复杂度为 O(n) 的情况下解决这个问题吗? 你可以假定返回的数组不算在额外空间内。
 * n == nums.length
 * 1 <= n <= 10^5
 * 1 <= nums[i] <= n
 * @author gusixue
 * @date 2023/2/4
 */
public class FindDisappearedNumbers {

    public static void main(String[] args) {

        FindDisappearedNumbers findDisappearedNumbers = new FindDisappearedNumbers();

        assert findDisappearedNumbers.solution(new int[]{1, 2, 0}).equals(Arrays.asList(3));
        assert findDisappearedNumbers.solution(new int[]{1, 1}).equals(Arrays.asList(2));
        assert findDisappearedNumbers.solution(new int[]{1, 1, 1}).equals(Arrays.asList(2, 3));
        assert findDisappearedNumbers.solution(new int[]{0, 0, 0}).equals(Arrays.asList(1, 2, 3));
        assert findDisappearedNumbers.solution(new int[]{3, 4, 5}).equals(Arrays.asList(1, 2));
        assert findDisappearedNumbers.solution(new int[]{1, 2, 3}).equals(Arrays.asList());
        assert findDisappearedNumbers.solution(new int[]{4,3,2,7,8,2,3,1}).equals(Arrays.asList(5, 6));

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();

            List<Integer> res = findDisappearedNumbers.solution(nums);

            System.out.println(res);
        }
    }

    /**
     * 解法：必须保证所有值均为非负数，将所有数字移动到对应位置，然后取反标记已经移动成功，最后遍历查询非负数
     *
     * 数组下标 i 从 0 开始循环，如果 nums[i] 正数且不大于数组长度，则将 nums[nums[i] - 1] 置为负数
     * 最后遍历一遍数组，所有非负数的位置即是结果
     */
    private List<Integer> solution(int[] nums) {

        List<Integer> res = new ArrayList<>();
        int len = nums.length;

        // 判空
        if (nums == null || nums.length == 0) {
            return res;
        }

        for (int i = 0; i < len; i++) {
            if (Math.abs(nums[i]) >= 1 && Math.abs(nums[i]) <= len) {
                nums[Math.abs(nums[i]) - 1] = -Math.abs(nums[Math.abs(nums[i]) - 1]);
            }
        }
        System.out.println(Arrays.toString(nums));

        for (int i = 0; i < len; i++) {
            if (nums[i] >= 0) {
                res.add(i + 1);
            }
        }

        return res;
    }
}
