package leetcode.medium;

import utils.AlgorithmUtils;

/**
 * @author gusixue
 * @description
 * 287. 寻找重复数
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
 * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 * 1 <= n <= 10 ^ 5
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
 * @date 2023/9/20
 */
public class FindDuplicate {

    public static void main(String[] args) {
        FindDuplicate findDuplicate = new FindDuplicate();
        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();

            int res = findDuplicate.solution(nums);
            System.out.println(res);
        }
    }

    /**
     * 首先 n + 1 个整数的数组 nums，在 [1, n] 除一个整数外其他均只出现一次，那么那个整数最少次数=(n+1)-(n-1)=2，即此整数出现的次数为 [2,n+1]
     * 可以顺着从 index 下标 0 开始到 nums[index]、然后到 nums[nums[index]]，这时一定会出现一个循环，而环的入口 nums 就是重复出现的元素，
     * 因为：其他数最多只出现一次，此时相当于入度最多为 1，只有重复的数入度大于 1，因此就是环的入口数
     * 解法：模拟链表找环入口，先从 0 开始的快慢指针找到相遇，再将慢指针与 0 开始的指针走到相遇即是环入口，也就是答案
     */
    private int solution(int[] nums) {
        // 特殊条件判断
        if (nums.length == 2) {
            return nums[0];
        }

        // 快慢指针
        int slow = 0;
        int fast = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
//        System.out.println(slow + " : " + fast);

        // 新指针与慢指针
        int newPointer = 0;
        slow = nums[slow];
        while (slow != newPointer) {
            slow = nums[slow];
            newPointer = nums[newPointer];
        }

        return newPointer;
    }
}
