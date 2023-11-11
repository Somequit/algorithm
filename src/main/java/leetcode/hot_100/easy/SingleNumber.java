package leetcode.hot_100.easy;

import utils.AlgorithmUtils;
import utils.TreeNode;

/**
 * @author gusixue
 * @description
 * 136. 只出现一次的数字
 * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
 * 1 <= nums.length <= 3 * 10 ^ 4
 * -3 * 10 ^ 4 <= nums[i] <= 3 * 10 ^ 4
 * 除了某个元素只出现一次以外，其余每个元素均出现两次。
 * @date 2023/11/11
 */
public class SingleNumber {

    public static void main(String[] args) {
        SingleNumber singleNumber = new SingleNumber();
        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();

            int res = singleNumber.solution(nums);
            System.out.println(res);
        }
    }

    /**
     * 异或的性质：0 异或所有值，结果就是答案，因为 x^x=0，0^x=x
     * @param nums
     * @return
     */
    private int solution(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }
}
