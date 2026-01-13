package leetcode.brushQuestions.medium;

/**
 * @author gusixue
 * @description 3453. 分割正方形 I
 * @date 2026/1/13 3:09 下午
 */
public class SeparateSquares {
//    public static void main(String[] args) {
//        System.out.println(1e-5 + 0.1);
//    }
    /**
     * 二分答案
     */
    public double separateSquares_OLD(int[][] squares) {
        double left = 0;
        double right = 0;
        for (int[] square : squares) {
            right = Math.max(right, square[1] + square[2]);
        }
//        System.out.println(right);

        while (doubleCalculate(right, left, 1e-6) != 0) {
            double mid = (right - left) / 2.0 + left;
//            System.out.println(left + " : " + right);

            int compareRes = doSeparateSquares_OLD(squares, mid);
            if(compareRes == 0) {
                right = mid;

                // 上面的更大
            } else if (compareRes > 0) {
                left = mid;

            } else {
                right = mid;
            }

        }

        return left;
    }

    private int doSeparateSquares_OLD(int[][] squares, double mid) {
        double up = 0;
        double down = 0;
        for (int[] square : squares) {
            if (doubleCalculate(square[1], mid, 1e-6) >= 0) {
                up += (double) square[2] * square[2];

            } else if (doubleCalculate(mid, square[1] + square[2], 1e-6) >= 0) {
                down += (double) square[2] * square[2];

            } else {
                up += (double) square[2] * (square[1] + square[2] - mid);
                down += (double) square[2] * (mid - square[1]);
            }
        }
//        System.out.println(mid + " : " + up + " : " + down);

        return doubleCalculate(up, down, 1e-6);
    }

    private static int doubleCalculate(double g, double h, double diff) {
        if (Math.abs(g - h) < diff) {
            return 0;
        } else {
            return g - h >= diff ? 1 : -1;
        }
    }

    /**
     * 二分答案，可以将 y轴*10^5 避免精度问题，此时总面积最大为：10^12*10^5
     * 仅需要求下面的面积，然后根据不变的总面积判定结果
     */
    public double separateSquares(int[][] squares) {
        long leftMulti = 0;
        long rightMulti = 0;
        long M = 100_000;
        long totalArea = 0;
        for (int[] square : squares) {
            totalArea += M * square[2] * square[2];
            rightMulti = Math.max(rightMulti, M * (square[1] + square[2]));
        }

        while (leftMulti < rightMulti) {
            long midMulti = (rightMulti - leftMulti) / 2 + leftMulti;

            // 下面的面积 大于等于 上面的面积
            if (doSeparateSquares(squares, midMulti, M, totalArea)) {
                rightMulti = midMulti;

            } else {
                leftMulti = midMulti + 1;
            }
        }

        // 中心更好
        return (double) leftMulti / M;
    }

    private boolean doSeparateSquares(int[][] squares, long midMulti, long M, long totalArea) {
        long downArea = 0;
        for (int[] square : squares) {
            if (square[1] * M < midMulti) {
                downArea += (long) square[2] * Math.min(square[2] * M, midMulti - square[1] * M);
            }
        }

        return downArea * 2 >= totalArea;
    }

}
