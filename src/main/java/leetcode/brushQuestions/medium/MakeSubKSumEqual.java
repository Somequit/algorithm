package leetcode.brushQuestions.medium;

import utils.AlgorithmUtils;

import java.util.Arrays;

/**
 * @author gusixue
 * @description
 * 2607. 使子数组元素和相等
 * 给你一个下标从 0 开始的整数数组 arr 和一个整数 k 。数组 arr 是一个循环数组。换句话说，数组中的最后一个元素的下一个元素是数组中的第一个元素，数组中第一个元素的前一个元素是数组中的最后一个元素。
 * 你可以执行下述运算任意次：
 * 选中 arr 中任意一个元素，并使其值加上 1 或减去 1 。
 * 执行运算使每个长度为 k 的 子数组 的元素总和都相等，返回所需要的最少运算次数。
 * 子数组 是数组的一个连续部分。
 *
 * 1 <= k <= arr.length <= 10^5
 * 1 <= arr[i] <= 10^9
 * @date 2023/10/8
 */
public class MakeSubKSumEqual {

    public static void main(String[] args) {
        MakeSubKSumEqual makeSubKSumEqual = new MakeSubKSumEqual();
        while (true) {
            int[] arr = AlgorithmUtils.systemInArray();
            int k = AlgorithmUtils.systemInNumberInt();

            long res = makeSubKSumEqual.solution(arr, k);
            System.out.println(res);
        }
    }

    /**
     * 求出 arr.length 与 k 的最大公约数，可以取的结果就是"公约数极其质因数"为长度形成的循环节，然后循环节对应位置置为相同就是结果，
     * 然后贪心可得循环节越长，循环次数越少，结果可能就越少，因此就取公约数即可，转化为循环节对应位置形成多个数组，每个数组修改为相同的元素，
     * 当其修改为中位数（偶数的话中间俩数均可），修改的次数最少，证明：
     * 可以将元素排序、然后看出坐标系上的 y 轴对应的点，然后与 x 轴平行划一条线（修改到的值），此时每个点到此线的距离之和就是结果，
     * 线在 最小值/最大值 之外一定大于 最小值/最大值，从最小值一路到最大值过程中，会 加上小于移动后线的个数*移动的距离、减去大于移动前线的个数*移动的距离，
     * 直到中位数总和最小
     */
    public long solution(int[] arr, int k) {
        int n = arr.length;
        int cycleLen = gcd(n, k);
//        System.out.println(cycleLen);

        long res = 0L;
        int[] cycleArr = new int[n / cycleLen];
        for (int i = 0; i < cycleLen; i++) {
            for (int j = i, l = 0; j < n; j += cycleLen, l++) {
                cycleArr[l] = arr[j];
            }

            Arrays.sort(cycleArr);
//            System.out.println(Arrays.toString(cycleArr));
            for (int j = 0; j < cycleArr.length; j++) {
                res += Math.abs(cycleArr[j] - cycleArr[cycleArr.length >> 1]);
            }
        }
        return res;
    }

    private int gcd(int a, int b) {
        return (a == 0 ? b : gcd(b % a, a));
    }
}
