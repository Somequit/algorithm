package leetcode.brushQuestions.medium;

import utils.AlgorithmUtils;

/**
 * @author gusixue
 * @description
 * 1094. 拼车
 * @date 2023/12/2
 */
public class CarPooling {

    public static void main(String[] args) {
        CarPooling carPooling = new CarPooling();
        while (true) {
            int[][] trips = AlgorithmUtils.systemInTwoArray();
            int capacity = AlgorithmUtils.systemInNumberInt();

            boolean res = carPooling.carPooling(trips, capacity);
            System.out.println(res);
        }
    }

    /**
     * 差分：from 处加上 numPassengers，to 处(下车处可以直接减)减去对应 numPassengers，最后遍历全部位置是否有某个位置大于 capacity
     * 时间复杂度：O(to)，空间复杂度：O(to)
     */
    public boolean carPooling(int[][] trips, int capacity) {
        int[] passengerCount = new int[1001];

        for (int i = 0; i < trips.length; i++) {
            passengerCount[trips[i][1]] += trips[i][0];
            passengerCount[trips[i][2]] -= trips[i][0];
        }

        for (int i = 0; i < passengerCount.length; i++) {
            passengerCount[i] += i == 0 ? 0 : passengerCount[i - 1];
            if (passengerCount[i] > capacity) {
                return false;
            }
        }

        return true;
    }
}
