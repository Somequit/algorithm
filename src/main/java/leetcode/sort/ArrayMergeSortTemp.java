package leetcode.sort;

import utils.AlgorithmUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 数组版归并排序
 * @author gusixue
 * @date 2022/8/25
 */
public class ArrayMergeSortTemp {

    public static void main(String[] args) {
        ArrayMergeSortTemp arrayMergeSort = new ArrayMergeSortTemp();
        while(true) {
            int[] nums = AlgorithmUtils.systemInArray();
            int[] results = arrayMergeSort.solution(nums);
            System.out.println(Arrays.stream(results).boxed().collect(Collectors.toList()));
        }
    }

    public int[] solution(int[] nums) {
        // 数组校验
        if (nums == null || nums.length <= 0) {
            return nums;
        }

        // 归并排序
        return mergeSort(nums, 0, nums.length);

    }

    /**
     * 归并排序，入参数组会被改变
     * @param left 左边缘，包含
     * @param right 右边缘，不包含
     * @return 返回排序好的数组
     */
    private int[] mergeSort(int[] nums, int left, int right) {
        // 递归出口
        if (right - left <= 1) {
            return nums;
        }

        // 递归划分
        int mid = (left + right >> 1);
        mergeSort(nums, left, mid);
        mergeSort(nums, mid, right);

        // 合并数组
        mergeArray(nums, left, mid, right);

        return nums;
    }

    /**
     * 合并俩相邻有序数组
     * @param left 第一个数组左边缘，包含
     * @param mid 第一个数组右边缘，不包含 同时 第二个数组左边缘，包含
     * @param right 第二个数组右边缘，不包含
     */
    private void mergeArray(int[] nums, int left, int mid, int right) {
        int lefti = left;
        int righti = mid;
        System.out.println("left:" + left + " mid:" + mid + " right:" + right);

        // 新建数组存结果
        int[] tmp = new int[right - left];

        int i = 0;
        // 双指针合并俩相邻有序数组
        while (lefti < mid || righti < right) {
            if (lefti >= mid) {
                tmp[i] = nums[righti];
                righti++;
            } else if (righti >= right) {
                tmp[i] = nums[lefti];
                lefti++;
            } else if (nums[lefti] <= nums[righti]) {
                System.out.println("lefti:" + lefti);
                tmp[i] = nums[lefti];
                lefti++;
            } else {
                tmp[i] = nums[righti];
                righti++;
            }
            i++;
        }

        // 新建数组赋值回原数组
        System.arraycopy(tmp, 0, nums, left, tmp.length);
        System.out.println(Arrays.stream(tmp).boxed().collect(Collectors.toList()));
        System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
    }
}
