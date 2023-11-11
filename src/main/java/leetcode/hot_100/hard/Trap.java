package leetcode.hot_100.hard;

import utils.AlgorithmUtils;

import java.util.Arrays;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * @author gusixue
 * @date 2023/2/24
 */
public class Trap {

    public static void main(String[] args) {
        Trap trap = new Trap();

        assertTest(trap);

        while (true) {
            int[] height = AlgorithmUtils.systemInArray();

            int res = trap.solution(height);
            System.out.println(res);

            int res2 = trap.solutionOptimization(height);
            System.out.println(res2);
        }
    }

    private static void assertTest(Trap trap) {

        // VM options 加上 -ea
        assert trap.solution(null) == 0;
        assert trap.solution(new int[]{}) == 0;
        assert trap.solution(new int[]{1}) == 0;
        assert trap.solution(new int[]{2, 1, 2}) == 1;
        assert trap.solution(new int[]{0, 2, 1, 3, 1, 2, 2}) == 2;

        assert trap.solutionOptimization(null) == 0;
        assert trap.solutionOptimization(new int[]{}) == 0;
        assert trap.solutionOptimization(new int[]{1}) == 0;
        assert trap.solutionOptimization(new int[]{2, 1, 2}) == 1;
        assert trap.solutionOptimization(new int[]{0, 2, 1, 3, 1, 2, 2}) == 2;
    }

    /**
     * 从左往右找到多个蓄水池，每个池子左边缘（大于 0）小于等于右边缘，同时右边缘又是新池子的左边缘，
     * 新建一个长度相同的数组、代表蓄满水后池子的新高度，维护这个数组用于从右往左以相同方式（用新数组）扫一遍即可，避免重复计算蓄水
     * 时间复杂度 O（n），空间复杂度 O(n)
     * @param height
     * @return
     */
    private int solution(int[] height) {
        int res = 0;
        // 判空
        if (height == null || height.length <= 2) {
            return res;
        }

        // 从左向右找到所有的蓄水池
        int[] newHeight = new int[height.length];
        int left = height[0];
        int temp = 0;
        int tempi = 0;
        for (int i = 1; i < height.length; i++) {
            if (left > 0) {
                if (height[i] < left) {
                    temp += left - height[i];
                } else {
                    res += temp;
                    temp = 0;
                    Arrays.fill(newHeight, tempi, i, left);
                    newHeight[i] = height[i];
                    left = height[i];
                    tempi = i;
                }
            } else {
                left = height[i];
                tempi = i;
            }
        }
        for (int i = tempi; i < height.length; i++) {
            newHeight[i] = height[i];
        }
//        System.out.println(Arrays.toString(newHeight));

        // 从右向左找到所有蓄水池
        int right = newHeight[height.length - 1];
        temp = 0;
        for (int i = newHeight.length - 2; i >= 0; i--) {
            if (right > 0) {
                if (newHeight[i] < right) {
                    temp += right - newHeight[i];
                } else {
                    res += temp;
                    temp = 0;
                    right = newHeight[i];
                }
            } else {
                right = newHeight[i];
            }
        }

        return res;
    }

    /**
     * i 位置的"接水最大高度"：min（leftMaxArr[i]（从左到 i（含）的最大值），rightMaxArr[i]（从右到 i（含）的最大值）） - height[i]，
     * 直接计算需存储两个数组，空间复杂度为 O（n），如我们不存储历史值，仅在计算时用 O（1）时间复杂度获得接水最大高度就好了，
     * 考虑双指针，left 从 0 增加，leftMax 代表到 left （含）最大值，right 从 length-1 减少，rightMax 代表到 right（含）最大值，
     * 如果 height[left] > height[right]，代表 leftMax > rightMax（如果 left 位前面有值大于 right 位的值也是 leftMax > rightMax，
     * 否则 left 前面的大值那位就不会加一走到 left 了），则 right 位的接水最大高度就是 rightMax，接水加到总水池，right 减一
     * 如果 height[left] <= height[right]，代表 leftMax <= rightMax，则 left 位的接水最大高度 leftMax，接水加到总水池，left 减一
     * 直到 left 与 right 相交且计算后，总水池的水即是结果
     * 时间复杂度 O（n），空间复杂度 O（1）
     * @param height
     * @return
     */
    private int solutionOptimization(int[] height) {
        int res = 0;
        // 判空
        if (height == null || height.length <= 2) {
            return res;
        }

        int left = 0;
        int right = height.length - 1;
        int leftMax = height[left];
        int rightMax = height[right];
        while (left <= right) {
            if (height[left] > height[right]) {
                res += rightMax - height[right];
                right --;
                if (left <= right) {
                    rightMax = Math.max(rightMax, height[right]);
                }
            } else {
                res += leftMax - height[left];
                left++;
                if (left <= right) {
                    leftMax = Math.max(leftMax, height[left]);
                }
            }
        }

        return res;
    }
}
