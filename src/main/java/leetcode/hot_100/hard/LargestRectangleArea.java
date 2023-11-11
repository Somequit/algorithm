package leetcode.hot_100.hard;

import utils.AlgorithmUtils;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author gusixue
 * @description
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 1 <= heights.length <=10 ^ 5
 * 0 <= heights[i] <= 10 ^ 4
 * @date 2023/11/10
 */
public class LargestRectangleArea {

    public static void main(String[] args) {
        LargestRectangleArea largestRectangleArea = new LargestRectangleArea();
        while (true) {
            int[] heights = AlgorithmUtils.systemInArray();

            int res = largestRectangleArea.solution(heights);
            System.out.println(res);
        }
    }

    /**
     * 单调栈：找到每个点左右不小于其的最远距离，乘以当前高度就是距离，顺序与逆序各一遍递增单调栈，
     * 遇到相同元素保留最后一个出现的元素（相同元素 x 均可以加入矩形面积中，但比其小的高度不能超过最后一个 x）
     * 时间复杂度：O(n)，空间复杂度：O（n）
     */
    private int solution(int[] heights) {
        // 判空
        if (heights == null || heights.length <= 0) {
            return 0;
        }

        int n = heights.length;
        // 顺序递增单调栈找到最远左端点
        Deque<Integer> indexAscendStack = new LinkedList<>();
        indexAscendStack.offerFirst(-1);
        int[] left = new int[n];

        for (int i = 0; i < n; i++) {
            while (indexAscendStack.peekFirst() != -1 && heights[indexAscendStack.peekFirst()] >= heights[i]) {
                indexAscendStack.pollFirst();
            }

            left[i] = indexAscendStack.peekFirst();
            indexAscendStack.offerFirst(i);

        }

        indexAscendStack.clear();
        indexAscendStack.offerFirst(n);
        int res = 0;
        // 逆序递增单调栈找到最远右端点，同时更新最大面积
        for (int i = n - 1; i >= 0; i--) {
            while (indexAscendStack.peekFirst() != n && heights[indexAscendStack.peekFirst()] >= heights[i]) {
                indexAscendStack.pollFirst();
            }

            res = Math.max(heights[i] * (indexAscendStack.peekFirst() - left[i] - 1), res);
            indexAscendStack.offerFirst(i);


        }

        return  res;
    }
}
