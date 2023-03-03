package leetcode.sort;

import utils.AlgorithmUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * 数组版快排
 * @author gusixue
 * @date 2022/9/5
 */
public class ArrayQuickSort {

    public static void main(String[] args) {

        ArrayQuickSort arrayQuickSort = new ArrayQuickSort();
        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();

            int[] res = arrayQuickSort.solution(nums);
            System.out.println(Arrays.toString(res));
        }
    }
    /**
     * 快速排序（升序）
     * 数组版本，不稳定排序
     * 时间复杂度：O（nlogn），空间复杂度：O（logn）
     */
    private int[] solution(int[] nums) {
        int[] res = Arrays.copyOf(nums, nums.length);

        // 随机打乱数组顺序，避免出现最坏情况
        shuffle(res);
        // System.out.println(Arrays.toString(nums));

        // 快排核心算法（升序）
        quickSort(res, 0, res.length - 1);
        // System.out.println(Arrays.toString(nums));

        return res;
    }

    /**
     * 洗牌算法，参考 {@link Collections#shuffle(java.util.List)}
     */
    private void shuffle(int[] nums) {
        Random rnd = new Random();
        for (int i = nums.length; i > 1; i--) {
            swap(nums, i - 1, rnd.nextInt(i));
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 快排核心（升序），
     * 分治法：首先分割成左右两个区间，将第一个元素放到中间，分割出左区间小于等于它，右区间大于等于它，此时这个元素位置就是最终结果
     * 然后递归将左右区间、分别分割再递归，直到小于等于一个元素，快排是在"分"的时候做好排序，然后递归直接结束，没有"合"的过程
     * 此方法可看做将数组拆分成二叉树，而每次分割时间复杂度为 O（n），因此总时间复杂度为 O（n * h），h 为树的高度，
     * 如果每次分割平均，则二叉树高度为 logn，而随机打乱后，期望时间复杂度为 O（n*logn）
     * 相等元素会放在两边降低树高
     * @param nums 待排序数组
     * @param left 左边界（含）
     * @param right 右边界（含）
     */
    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        // 用 O（n）时间复杂度，将第一个元素放到 pivot 下标中，并保证[left, pivot - 1]不大于它，[pivot + 1,right]大于它
        int pivot = partition(nums, left, right);
        // System.out.println(pivot + ":" + Arrays.toString(nums));

        quickSort(nums, left, pivot - 1);
        quickSort(nums, pivot + 1, right);
    }

    /**
     * 分割出中心点，左区间小于等于中心值，右区间大于等于中心值
     * 双指针：先将第一个元素作为中心点，左指针从第二个元素开始往右，右指针从最后一个元素开始往左，
     * 左指针找到第一个大于等于中心值的位置，右指针找到第一个小于等于中心值的位置，交换两个值（相等元素会放在两边降低树高），
     * 然后指针分别往中间走，直到左指针大于等于右指针，此时将中心点（第一个元素）与右指针（一定小于等于中心点最大的下标）交换，完成
     * @param nums 待排序数组
     * @param left 左边界（含）
     * @param right 右边界（含）
     * @return 中心点下标
     */
    private int partition(int[] nums, int left, int right) {
        int pivot = left;
        left ++;
        while (left <= right) {
            while (left <= right && nums[left] < nums[pivot]) {
                left++;
            }

            while (left <= right && nums[right] > nums[pivot]) {
                right--;
            }

            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }

        swap(nums, pivot, right);
        return right;
    }
}
