package leetcode.brushQuestions.medium;

/**
 * @author gusixue
 * @description 11. 盛最多水的容器
 * @date 2025/10/4 2:05 下午
 */
public class MaxArea {
    /**
     * 问题可转化为：求左边垂线下标 left 与右边垂线下标 right （right > left）的式子：(right - left) * min(height[left], height[right]) 的最大值
     * 参考接雨水的方法，设 d = (right - left)，d=n-1 时仅有一种情况，从它入手
     * 设 height[left] <= height[right]，则仅需 left 右移到大于 height[left] > height[right] 然后更新结果，height[right] 更大则相反，直到 left <= right
     * 原因是，如果不这么移动结果一定变小，因为过程中 (right - left) 一定变小，因此需要保证 min(height[left], height[right]) 变大，最终结果才可能变大，而 height 较小的一边不移动 min 处一定不变大
     * 时间复杂度：O（n），空间复杂度：O（1）
     */
    public int maxArea(int[] height) {
        int right = height.length - 1;
        int left = 0;

        int res = (right - left) * Math.min(height[left], height[right]);
        while (left < right) {
            if (height[left] <= height[right]) {
                left++;

            } else {
                right--;
            }

            res = Math.max(res, (right - left) * Math.min(height[left], height[right]));
        }

        return res;
    }
}
