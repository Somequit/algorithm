package leetcode.hot_100.medium;

import utils.AlgorithmUtils;

import java.util.Arrays;

/**
 * @author gusixue
 * @description
 * 238. 除自身以外数组的乘积
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 * 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * 2 <= nums.length <= 10 ^ 5
 * -30 <= nums[i] <= 30
 * 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内
 * 进阶：你可以在 O(1) 的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 * @date 2023/9/22
 */
public class ProductExceptSelf {

    public static void main(String[] args) {
        ProductExceptSelf productExceptSelf = new ProductExceptSelf();
        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();

            int[] res = productExceptSelf.solution(nums);
            System.out.println(Arrays.toString(res));
        }
    }

    /**
     * 结果数组 res 存储 nums 的后缀乘积（不含自己），顺序遍历 nums 数组，获取前缀乘积（不含自己），乘以 res 就是结果
     * 时间复杂度：O（n）；空间复杂度：O（n）
     */
    private int[] solution(int[] nums) {
        // 判空
        if (nums == null || nums.length <= 0) {
            return new int[0];
        }

        int len = nums.length;
        // 存储 nums 的后缀乘积（不含自己）
        int[] res = new int[len];
        int suffixProduct = 1;
        for (int i = len - 1; i >= 0; i--) {
            res[i] = suffixProduct;

            // 最后一个元素乘完后越界也无所谓
            suffixProduct *= nums[i];

        }

        // 顺序遍历 nums 数组，获取前缀乘积（不含自己）
        int prefixProduct = 1;
        for (int i = 0; i < len; i++) {
            res[i] *= prefixProduct;

            // 最后一个元素乘完后越界也无所谓
            prefixProduct *= nums[i];
        }

        return res;
    }
}
