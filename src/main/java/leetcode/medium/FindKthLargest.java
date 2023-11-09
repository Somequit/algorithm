package leetcode.medium;

import template.Algorithm;
import utils.AlgorithmUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * @author gusixue
 * @description
 * 215. 数组中的第K个最大元素
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * 1 <= k <= nums.length <= 10 ^ 5
 * -10 ^ 4 <= nums[i] <= 10 ^ 4
 * @date 2023/11/9
 */
public class FindKthLargest {

    public static void main(String[] args) {
        FindKthLargest findKthLargest = new FindKthLargest();
        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
            int k = AlgorithmUtils.systemInNumberInt();

            int res = findKthLargest.solution(nums, k);
            System.out.println(res);
        }
    }

    /**
     * 模拟快排的 partition 算法，将第一个元素排到对应位置、保证左边不小于它、右边小于它，此时它排的下标 pivot 代表第 pivot+1 大，
     * 接着如果 k = pivot+1，则结果就出来了，k > pivot+1 则仅遍历右侧元素，如果 k < pivot+1 则仅遍历左侧元素即可，
     * 此算法的期望时间复杂度为 O(n)（T(n)=T(n/2)*n），如果精心设计的数据会导致退化到 O（n ^ 2）(例如非降序数组求最大值时)，因此可以先用洗牌算法（O(n)）打乱所有元素
     * 优化：pivot 存储了相同元素的最后一位，可以将相同元素移到一起统一找第 k 大/删除，如果 partition 后结果需要遍历左侧元素，
     * 则先在左侧中找所有与 nums[pivot] 相同的元素，排在最后判断第 k 大是否落其中，是则直接返回结果，否则将相同元素全部删除
     * 时间复杂度：O(n)，空间复杂度：O（logn）栈深度
     */
    private int solution(int[] nums, int k) {
        // 判空
        if (nums == null || nums.length < k) {
            return 0;
        }

//        System.out.println("start:" + Arrays.toString(nums));
        // 洗牌算法
        shuffle(nums);
//        System.out.println("shuffle:" + Arrays.toString(nums));

        // Partition 算法找到第 k 大
        return doPartitionKLargest(nums, 0, nums.length, k);
    }

    /**
     * Partition 算法找到第 k 大
     * @param nums 数组
     * @param left 左端点（含）
     * @param right 右端点（不含）
     * @param k 第 k 大
     */
    private int doPartitionKLargest(int[] nums, int left, int right, int k) {
        // 判断异常
        if (left >= right) {
            return 0;
        }

        // 第一个元素排到对应位置，保证左边不小于它、右边小于它，返回下标
        int pivot = partitionDescend(nums, left, right);

        // 如果 k = pivot+1，则结果就出来了，k > pivot+1 则仅遍历右侧元素，如果 k < pivot+1 则仅遍历左侧元素即可，
        int res;
        if (k == pivot + 1) {
            res = nums[pivot];

        } else if (k > pivot + 1) {
            res = doPartitionKLargest(nums, pivot + 1, right, k);

        } else {
//            System.out.println(Arrays.toString(nums) + " : " + nums[pivot] + " : " + pivot);
            // 将相同元素移到一起统一找第 k 大/删除
            int leftPivot = doRepeatPivot(nums, left, pivot, nums[pivot]);
//            System.out.println(Arrays.toString(nums) + " : " + nums[pivot] + " : " + leftPivot);

            // 先在左侧中找所有与 nums[pivot] 相同的元素，排在最后判断第 k 大是否落其中，是则直接返回结果，否则将相同元素全部删除
            if (k >= leftPivot + 1) {
                return nums[pivot];
            }

            res = doPartitionKLargest(nums, left, leftPivot, k);
        }

        return res;
    }

    /**
     * 将与 repeat 相同元素移到一起统一找第 k 大/删除，返回相同元素最左边的下标
     * @param nums 数组
     * @param left 左端点（含）
     * @param right 右端点（不含）
     * @param repeat nums 数组中与其相同的元素
     * @return
     */
    private int doRepeatPivot(int[] nums, int left, int right, int repeat) {
        int leftPivot = right;
        // 注意交换元素后，右端点会改变
        for (int i = left; i < leftPivot; i++) {
            if (nums[i] == repeat) {
                leftPivot--;
                swap(nums, i, leftPivot);
            }
        }
        return leftPivot;
    }

    /**
     * 第一个元素排到对应位置，保证左边不小于它、右边小于它，返回下标
     */
    private int partitionDescend(int[] nums, int left, int right) {
        int pivot = left;

        while (left < right) {
            while (left < right && nums[right - 1] < nums[pivot]) {
                right--;
            }
            if (left < right) {
                swap(nums, right - 1, pivot);
                pivot = right - 1;
                right--;
            }

            while (left < right && nums[left] >= nums[pivot]) {
                left++;
            }
            if (left < right) {
                swap(nums, left, pivot);
                pivot = left;
                left++;
            }

        }

//        System.out.println(nums + " : " + left + right + pivot);
        return pivot;
    }

    /**
     * 洗牌算法，遍历一次每个元素随机交换另一个元素
     * 参考 {@link Collections#shuffle(java.util.List)}
     */
    private void shuffle(int[] nums) {
        Random random = new Random();
        for (int i = nums.length; i > 1; i--) {
            swap(nums, i - 1, random.nextInt(i));
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
