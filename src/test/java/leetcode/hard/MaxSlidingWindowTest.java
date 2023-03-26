package leetcode.hard;


import org.junit.Test;

import java.util.Arrays;

/**
 * @author gusixue
 * @date 2023/3/24
 */
// 指定运行器纯属个人习惯，使用默认的也可
public class MaxSlidingWindowTest {

    @Test
   public void test() {
        MaxSlidingWindow maxSlidingWindow = new MaxSlidingWindow();

//        assert "[1, 2, 3]".equals(Arrays.toString(maxSlidingWindow.maxSlidingWindow(
//                new int[]{1, 2, 3}, 1)));
//        assert "[2, 3, 4]".equals(Arrays.toString(maxSlidingWindow.maxSlidingWindow(
//                new int[]{1, 2, 3, 4}, 2)));
//        assert "[-1, 0, 4]".equals(Arrays.toString(maxSlidingWindow.maxSlidingWindow(
//                new int[]{-1, -2, 0, 4}, 2)));
//        assert "[3, 3]".equals(Arrays.toString(maxSlidingWindow.maxSlidingWindow(
//                new int[]{3, 3, 2}, 2)));

//        assert "[1, 2, 3]".equals(Arrays.toString(maxSlidingWindow.maxSlidingWindowOptimize(
//                new int[]{1, 2, 3}, 1)));
//        System.out.println();
//
//        assert "[3, 2, 1]".equals(Arrays.toString(maxSlidingWindow.maxSlidingWindowOptimize(
//                new int[]{3, 2, 1}, 1)));
//        System.out.println();
//
//        assert "[2, 3, 4]".equals(Arrays.toString(maxSlidingWindow.maxSlidingWindowOptimize(
//                new int[]{1, 2, 3, 4}, 2)));
//        System.out.println();
//
//        assert "[-1, 0, 4]".equals(Arrays.toString(maxSlidingWindow.maxSlidingWindowOptimize(
//                new int[]{-1, -2, 0, 4}, 2)));
//        System.out.println();
//
//        assert "[3, 3]".equals(Arrays.toString(maxSlidingWindow.maxSlidingWindowOptimize(
//                new int[]{3, 3, 2}, 2)));
//        System.out.println();



        assert "[1, 2, 3]".equals(Arrays.toString(maxSlidingWindow.maxSlidingWindowOptimizeSecond(
                new int[]{1, 2, 3}, 1)));
        System.out.println();

        assert "[3, 2, 1]".equals(Arrays.toString(maxSlidingWindow.maxSlidingWindowOptimizeSecond(
                new int[]{3, 2, 1}, 1)));
        System.out.println();

        assert "[2, 3, 4]".equals(Arrays.toString(maxSlidingWindow.maxSlidingWindowOptimizeSecond(
                new int[]{1, 2, 3, 4}, 2)));
        System.out.println();

        assert "[-1, 0, 4]".equals(Arrays.toString(maxSlidingWindow.maxSlidingWindowOptimizeSecond(
                new int[]{-1, -2, 0, 4}, 2)));
        System.out.println();

        assert "[3, 3]".equals(Arrays.toString(maxSlidingWindow.maxSlidingWindowOptimizeSecond(
                new int[]{3, 3, 2}, 2)));
        System.out.println();

        assert "[5, 5, 4, 3, 2, 2]".equals(Arrays.toString(maxSlidingWindow.maxSlidingWindowOptimizeSecond(
                new int[]{5, 5, 4, 3, 2, 2, 2, 2, 1}, 4)));
        System.out.println();

        assert 1 == 2;

    }

}
