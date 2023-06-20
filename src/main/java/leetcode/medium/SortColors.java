package leetcode.medium;

import utils.AlgorithmUtils;

/**
 * @author gusixue
 * @description
 * 75. 颜色分类
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 * @date 2023/6/20
 */
public class SortColors {

    public static void main(String[] args) {
        SortColors sortColors = new SortColors();
        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
            sortColors.solution(nums);
            System.out.println(nums);
        }
    }

    /**
     * 三指针：建立三个指针：头指针、中指针与尾指针，中指针指向头指针前面最小远 1 的下标（如有）；头指针与中指针从 0 开始往后移动，
     * 尾指针从最后一个元素开始往前移动，从头指针开始分类讨论移动指针，直到头尾指针穿过，过程如下：
     *     如果头指针元素为 0
     *         如果头中指针指向相同元素，代表头指针前全是 0，则头中指针后移
     *         如果头指针大于中指针，代表头指针（不含）到中指针全是 1，则交换头中指针，头中指针后移
     *         不会单独移动中指针，所以中指针不大于头指针
     *     如果头指针元素为 1，仅头指针后移，使头指针（不含）到中指针全是 1
     *     如果头指针元素为 2，可能将其与尾指针交换、因此需要判断尾指针元素
     *         如果尾指针元素为 0，交换头尾指针，尾指针前移，注意此处头指针不后移、因为可能头中指针间还有 1
     *         如果尾指针元素为 1，交换头尾指针，尾指针前移，此处可以结束进行下一轮循环，但是由于头指针此时一定为 1，因此还可以直接头指针后移
     *         如果尾指针元素为 2，因为尾指针后续全是2，因此仅尾指针前移
     * 移动过程中，保证头指针不小于中指针，头指针前仅有 0、1，中指针前仅有 0，头指针（不含）到中指针全是 1，尾指针后全是 2，头尾指针之间未知
     * @param nums
     */
    private void solution(int[] nums) {
        // 判空与单个元素的特殊情况
        if (nums == null || nums.length <= 1) {
            return;
        }
        // 头指针、中指针与尾指针，中指针指向头指针前面最小远 1 的下标（如有）
        int headPoint = 0;
        int middlePoint = 0;
        int tailPoint = nums.length - 1;

        // 三指针循环数组，对每种情况分类讨论
        while (headPoint <= tailPoint) {
            if (nums[headPoint] == 0) {

                // middlePoint 不会主动后移，因此不会比 headPoint 大
                if (headPoint  == middlePoint) {

                    headPoint++;
                    middlePoint++;
                } else {

                    swap(nums, headPoint, middlePoint);
                    headPoint++;
                    middlePoint++;
                }
            } else if (nums[headPoint] == 1) {

                headPoint++;
            } else {

                if (nums[tailPoint] == 0) {

                    swap(nums, headPoint, tailPoint);
                    tailPoint--;
                } else if (nums[tailPoint] == 1) {

                    swap(nums, headPoint, tailPoint);
                    tailPoint--;
                    headPoint++;
                } else {

                    tailPoint--;
                }
            }
        }
        return;
    }

    private void swap(int[] nums, int pointA, int pointB) {
        int tmp = nums[pointA];
        nums[pointA] = nums[pointB];
        nums[pointB] = tmp;
    }
}
