package leetcode.easy;

import utils.AlgorithmUtils;

/**
 * 461. 汉明距离
 * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
 * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
 * 注意数字的二进制均是以 补码 的形式展示的，因此此处也是二进制补码的结果
 */
public class HanmingDistance {

    public static void main(String[] args) {
        while(true) {
            int x = AlgorithmUtils.systemInNumberInt();
            int y = AlgorithmUtils.systemInNumberInt();
//            int result = solution(x, y);
//            System.out.println(result);

//            int result = solution2(x, y);
//            System.out.println(result);

//            int result = solution3(x, y);
//            System.out.println(result);

            int result = solution4(x, y);
            System.out.println(result);
        }
    }

    /**
     * 每次取出最后一位二进制的 1 处理
     * 仅仅支持两数都为正，负数会导致越界
     */
    private static int solution(int x, int y) {
        int result = 0;
        while (x != 0 || y != 0) {
            int lowbitX = lowbit(x);
            int lowbitY = lowbit(y);
            System.out.println(lowbitX + ":" + lowbitY);
            if (lowbitX == 0) {
                y -= lowbitY;
                result++;
            } else if (lowbitY == 0) {
                x -= lowbitX;
                result++;
            } else if (lowbitX == lowbitY) {
                x -= lowbitX;
                y -= lowbitY;
            } else if (lowbitX > lowbitY){
                y -= lowbitY;
                result++;
            } else {
                x -= lowbitX;
                result++;
            }
        }
        return result;
    }

    private static int lowbit(int num) {
        return (num & -num);
    }

    /**
     * 直接无符号右移动
     * 支持负数
     */
    private static int solution2(int x, int y) {
        int result = 0;
        System.out.println(AlgorithmUtils.decimalToBinary(x));
        System.out.println(AlgorithmUtils.decimalToBinary(y));
        while (x != 0 || y != 0) {
            if ((x & 1) != (y & 1)) {
                result++;
            }
            x >>>= 1;
            y >>>= 1;
        }
        return result;
    }

    /**
     * 先将俩数异或、再求结果中二进制 1 的个数就好，因为异或是俩数二进制位比对、相同返回 0、不同返回 1
     * 使用过内置查询二进制位 1 个数的方法
     * 支持负数
     */
    private static int solution3(int x, int y) {
        System.out.println(AlgorithmUtils.decimalToBinary(x));
        System.out.println(AlgorithmUtils.decimalToBinary(y));
        return Integer.bitCount(x ^ y);
    }

    /**
     * 先将俩数异或，再循环运算 x & (x - 1)，每次均会删除最后一位 1
     * Brian Kernighan 算法
     */
    private static int solution4(int x, int y) {
        System.out.println(AlgorithmUtils.decimalToBinary(x));
        System.out.println(AlgorithmUtils.decimalToBinary(y));
        int z = x ^ y;
        int count = 0;
        while (z > 0) {
            z &=  z - 1;
            count++;
        }
        return count;
    }
}
