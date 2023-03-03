package jianzhioffer;

import utils.AlgorithmUtils;

/**
 * 给定一个数组A[0,1…,n-1],求出一个数组B[0,1…,n-1],规定B数组每一个参数都等于A数组所有数值连乘、然后除B数组对应位置的A[i]
 * ，即B[i]=A[0]*…*A[i-1]*A[i+1]*…*A[n-1]，不使用除法，求出B数组
 */
public class CreateMultiplyArray {

    public static void main(String[] args) {
        while(true) {
            int[] A = AlgorithmUtils.systemInArray();
            AlgorithmUtils.systemOutArray(A);
            int[] solution = multiply(A);
            AlgorithmUtils.systemOutArray(solution);
        }
    }

    /**
     * 解法剖析：最简单方法是双重for循环，时间复杂度为O(N^2)，空间复杂度为O(N)，接着优化时间，画出图分析才可以看出
     * ：从1可以分开成为上下两个三角连乘、每个三角连乘：下一行使用上一行快速获得数据，因此时间复杂度降为O(N)，空间复杂度不变
     */
    public static int[] multiply(int[] A) {
        int ALength = A.length;
        int[] B = new int[ALength];
        if (ALength > 0) {
            B[0] = 1;
            for (int i = 1; i < ALength; i++) {// 下三角连乘
                B[i] = B[i - 1] * A[i - 1];
            }
            int temp = 1;// 减少空间消耗
            for (int i = ALength - 2; i >= 0; i--) {// 上三角连乘
                temp *= A[i + 1];// 避免超过int
                B[i] *= temp;
            }
        }
        return B;
    }

}
