package leetcode.brushQuestions.medium;

/**
 * @author gusixue
 * @description 162. 寻找峰值
 * @date 2023/12/18
 */
public class FindPeakElement {

    /**
     * Java + 二分：
     *
     * 第 1 步：
     * 首先时间复杂度为 O（logn）可以联想到 二分/二叉树 类似的做法，
     * 如果是二分，则需要具备单调性，
     *
     * 第 2 步：
     * 我们考虑数组可能出现的情况，抽象出来分为四种：递增、递减、先减后增、先增后减
     * 其他情况都是这四种的组合
     *
     * 第 3 步：
     * 分析四种情况：
     *     * 递增则最后一个元素一定满足，因为大于前一个元素、且大于 nums[n] = -∞
     *     * 递减则第一个元素一定满足，因为大于后一个元素、且大于 nums[0] = -∞
     *     * 先减后增则第一个或者最后一个元素均满足，如上
     *     * 先增后减则增的那段最后一个元素（也是减的那段第一个元素）满足
     * 可以总结下，递增那段最后一个元素，递减那段第一个元素
     *
     * 第 4 步：
     * 因此模拟二分，
     *     * 如果 nums[mid] > nums[mid+1] 先判断 mid，不满足则区间左侧满足，
     *     * 如果 nums[mid] < nums[mid+1] 先判断 mid + 1，不满足则区间右侧满足，
     * 时间复杂度：O（logn），空间复杂度：O（1）
     *
     */
    public int findPeakElement(int[] nums) {

        int left = 0;
        int right = nums.length - 1;
        int res = left;
        while (left < right) {
            int mid = ((right - left) >> 1) + left;
            if (nums[mid] > nums[mid + 1]) {
                right = mid - 1;

                if (mid == 0 || nums[mid] > nums[mid - 1]) {
                    res = mid;
                }

            } else {
                left = mid + 1;

                if (mid + 1 == nums.length - 1 || nums[mid + 1] > nums[mid + 2]) {
                    res = mid + 1;
                }
            }
        }

        return res;
    }
}
