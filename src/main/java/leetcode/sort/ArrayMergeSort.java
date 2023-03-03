package leetcode.sort;

import utils.AlgorithmUtils;

import java.util.Arrays;

/**
 * 数组版归并排序（小数组使用直接插入排序）
 * @author gusixue
 * @date 2023/3/2
 */
public class ArrayMergeSort {

    public static void main(String[] args) {

        ArrayMergeSort arrayMergeSort = new ArrayMergeSort();
        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();

            int[] res = arrayMergeSort.solution(nums);
            System.out.println(Arrays.toString(res));
        }
    }

    /**
     * 插入排序的门槛（个数小于此数使用）
     */
    private static final int INSERTIONSORT_THRESHOLD = 7;

    /**
     * 归并排序（升序）
     * 数组版本，稳定排序
     * @param nums
     * @return
     */
    private int[] solution(int[] nums) {
        // 判空
        if (nums == null || nums.length <= 1) {
            return nums;
        }

        // 归并排序核心算法（升序）
        int[] temp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1, temp);
        return nums;
    }

    /**
     * 归并排序核心算法（升序）
     * 分治法：先将数组分割成左右俩相等小数组，直到插入排序门槛，
     * 插入排序：保证前 i 个元素有序，将第 i+1 个元素插入前 i 个元素中正确位置
     * 接着回溯时，就是将俩有序数组合并成一个有序数组，开一个暂存数组，使用双指针依次将最小的元素放入暂存数组中
     * 注意：排序俩有序数组以及直接插入时保证稳定性，俩有序数组注意边界
     * 时间复杂度：O（nlogn）（可看做递归成一课平衡二叉树，从叶子节点开始层层排序，然后回溯到父节点排序，每次排序时间为 O（n），树高为 logn），
     * 空间复杂度：O（n）（由于要开额外暂存数组为 n，以及递归栈的 logn）
     *
     * @param nums 待排序数组（在原数组上处理）
     * @param left 左边界（含）
     * @param right 右边界（含）
     * @param temp 合并两个有序数组的暂存数组，全局使用一份，避免多次创建和销毁
     * @return 排序好的数组
     */
    private void mergeSort(int[] nums, int left, int right, int[] temp) {
        // 插入排序的门槛（个数小于此数使用，JDK 中 Arrays.sort）
        if (right - left + 1 < INSERTIONSORT_THRESHOLD) {
            // 直接插入排序小数组
            insertionSort(nums, left, right);
            return;
        }

        // 即是 left + right 溢出仍然正确
        int mid = left + (right - left) / 2;
        // int mid = ((left + right) >> 1);
        // 递归左右区间
        mergeSort(nums, left, mid, temp);
        mergeSort(nums, mid + 1, right, temp);

        // 如果本身有序则无需合并
        if (nums[mid] <= nums[mid + 1]) {
            return;
        }

        // 双指针：将各自有序的左右区间，合并成全部有序
        mergeOfTwoSortedArray(nums, left, mid, right, temp);
    }

    /**
     * 直接插入排序小数组
     * 保证前 i 个元素有序，将第 i+1 个元素插入前 i 个元素中正确位置
     */
    private void insertionSort(int[] nums, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            for (int j = i; j > left; j--) {
                // 保证稳定性
                if (nums[j - 1] > nums[j]) {
                    swap(nums, j - 1, j);
                }
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 双指针：将各自有序的左右区间，合并成全部有序，时间复杂度为 O（n）
     */
    private void mergeOfTwoSortedArray(int[] nums, int left, int mid, int right, int[] temp) {
        int l = left;
        int r = mid + 1;
        int len = right - left + 1;

        for (int i = 0; i < len; i++) {
            // 保证稳定性，左边等于右边时仍然取左边
            if (r > right || (l <= mid && nums[l] <= nums[r])) {
                temp[i] = nums[l];
                l++;
            } else {
                temp[i] = nums[r];
                r++;
            }
        }

        for (int i = 0; i < len; i++) {
            nums[i + left] = temp[i];
        }
    }

}
