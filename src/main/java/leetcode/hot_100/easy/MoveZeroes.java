package leetcode.hot_100.easy;

import utils.AlgorithmUtils;

import java.util.Arrays;

/**
 * 283. 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * @author gusixue
 * @date 2023/1/26
 */
public class MoveZeroes {

    public static void main(String[] args) {

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();

            MoveZeroes moveZeroes = new MoveZeroes();
            moveZeroes.solution(nums);

            System.out.println(Arrays.toString(nums));
        }
    }

    /**
     * 双指针：头指针永远指向第一个 0，且它的位置前面数据是正确的结果
     * 首先头尾指针移动到第一个 0
     * 尾指针从下一位开始遍历，当尾指针找到非 0
     * 将其与头指针数字进行交换
     * 接着头指针后移一位
     * 循环第二步，直到遍历完成
     */
    private void solution(int[] nums) {
        if (nums == null || nums.length == 0) {
            return ;
        }

        int head = -1;
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                head = i;
                break;
            }
        }
        if (head == -1 || head == len - 1) {
            return;
        }

        // i 即是尾指针
        for (int i = head + 1; i < len; i++) {
            if (nums[i] != 0) {
                swap(nums, i, head);
                head++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
