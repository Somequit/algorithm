package leetcode.hot_100.medium;

import utils.AlgorithmUtils;

import java.util.Arrays;

/**
 * 31. 下一个排列
 * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
 *
 * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。
 * 如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 *
 * 给你一个整数数组 nums ，找出 nums 的下一个排列。
 *
 * 必须 原地 修改，只允许使用额外常数空间。
 * @author gusixue
 * @date 2023/2/20
 */
public class NextPermutation {

    public static void main(String[] args) {
        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
            NextPermutation nextPermutation = new NextPermutation();

            nextPermutation.solution(nums);
            System.out.println(Arrays.toString(nums));
        }
    }

    /**
     * 首先排除特殊情况，空数组、少于两个数、全相同以及最后一个排列（元素降序）
     * 正常情况，模拟 dfs 全排列算法，即一个升序数组标记每一位只能使用一次，递归结果的每一位能使用的数字，注意不要重复即可
     * 模拟方式是，找到第 n 个元素到最后均为降序，第 n-1 个元素非降序，接着将 n 到结尾中大于第 n-1 位元素的最小值放到 n-1 位，
     * 然后 n 位到结尾（一定是降序）改为升序即可
     * @param nums
     * @return
     */
    private void solution(int[] nums) {
        // 判空，少于两个元素，全相同
        if (nums == null || nums.length < 2 || isEqual(nums)) {
            return;
        }
        // 查询第 n 个元素到最后均为降序，第 n-1 个元素非降序，如果没有 n-1 位则直接全改为升序
        int n = getDescCountdown(nums);
//        System.out.println("n = " + n);
        if (n == 0) {
            reverse(nums, 0, nums.length);
            return;
        }

        // 查询第 x 位元素，n 到结尾元素中，它是大于第 n-1 位元素的最小值
        int x = getBigger(nums, n);
//        System.out.println("x = " + x);

        // 替换 x 位与 n-1 位
        swap(nums, x, n - 1);

        // n 位到结尾从降序改为升序
        reverse(nums, n, nums.length);
    }

    private boolean isEqual(int[] nums) {
        boolean res = true;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                res = false;
                break;
            }
        }
        return res;
    }

    /**
     * 查询第 n 个元素到最后均为降序，第 n-1 个元素非降序
     * @param nums
     * @return
     */
    private int getDescCountdown(int[] nums) {
        int res = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                res = i + 1;
                break;
            }
        }
        return res;
    }

    /**
     * 降序改为升序，折半替换
     * @param nums
     * @param begin 开头元素位置（含）
     * @param end 结束元素位置（不含）
     */
    private void reverse(int[] nums, int begin, int end) {
        while (begin < end - 1) {
            swap(nums, begin, end - 1);
            begin++;
            end--;
        }
    }

    /**
     * 查询第 x 位元素，n 到结尾元素中，它是大于第 n-1 位元素的最小值
     * @param nums n 到结尾元素为降序
     * @param n
     * @return
     */
    private int getBigger(int[] nums, int n) {
        int res = 0;
        for (int i  = nums.length - 1; i >= n; i--) {
            if (nums[i] > nums[n - 1]) {
                res = i;
                break;
            }
        }
        return res;
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
