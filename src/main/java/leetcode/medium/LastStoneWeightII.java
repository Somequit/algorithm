package leetcode.medium;

import utils.AlgorithmUtils;

import java.util.Arrays;

/**
 * @author gusixue
 * @description
 * 1049. 最后一块石头的重量 II
 * 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
 *
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 *
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 100
 * @date 2023/9/18
 */
public class LastStoneWeightII {

    public static void main(String[] args) {
        LastStoneWeightII lastStoneWeightII = new LastStoneWeightII();

        while (true) {
            int[] stones = AlgorithmUtils.systemInArray();

            int res = lastStoneWeightII.solution(stones);
            System.out.println(res);
        }
    }

    /**
     * 枚举两颗石头可得类似如下表达式：stones[0]-stones[1]
     * 枚举三颗石头可得类似如下表达式：stones[0]-stones[1]+stones[2]、stones[1]-stones[0]+stones[2]、stones[2]-stones[0]+stones[1]
     * 因此可以看出每个石头前面添加 +/-，最后获取大于等于 0 的最小结果，此时可以转化为类似 01 背包，
     * 注意：由于必须都选择，而非可选可不选，因此需要开滚动数组、同时结束后清理数据
     * n 为数组 stones 的长度，sum 为 stones 所有元素之和最大值，时间复杂度：O(n*sum)，空间复杂度：O(sum)
     * @param stones
     * @return
     */
    public int solution(int[] stones) {
        // 最大容量
        int maxStock = 3000;
        int knapsackLen = maxStock * 2 + 1;

        // 可能为负数、此时需要移动到非负数，例如 0->maxStock
        boolean[][] knapsackDp = new boolean[2][knapsackLen];

        // 初始化，第一颗石头只能为 maxStock(+stones[0]/-stones[0])
        knapsackDp[0][maxStock + stones[0]] = true;
        knapsackDp[0][maxStock - stones[0]] = true;
//        for (int k = 0; k < knapsackLen; k++) {
//            if (knapsackDp[0][k]) {
//                System.out.print(k + " ");
//            }
//        }
//        System.out.println();

        for (int i = 1; i < stones.length; i++) {

            // 由于必须都选择，而非可选可不选，因此需要滚动
            for (int j = 0; j < knapsackLen; j++) {
                if (knapsackDp[(i - 1) & 1][j]) {
                    if (j >= stones[i]) {
                        knapsackDp[i & 1][j - stones[i]] = true;
                    }
                    if (j + stones[i] < knapsackLen) {
                        knapsackDp[i & 1][j + stones[i]] = true;
                    }
                }
            }
//            for (int k = 0; k < knapsackLen; k++) {
//                if (knapsackDp[i & 1][k]) {
//                    System.out.print(k + " ");
//                }
//            }
//            System.out.println();

            // 结束后清理当前数据
            Arrays.fill(knapsackDp[(i - 1) & 1], false);
        }

        int res = -1;
        for (int i = maxStock; i < knapsackLen; i++) {
            if (knapsackDp[(stones.length - 1) & 1][i]) {
                res = i - maxStock;
                break;
            }
        }

        return res;
    }
}
