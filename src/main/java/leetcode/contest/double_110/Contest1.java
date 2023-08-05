package leetcode.contest.double_110;

import utils.AlgorithmUtils;

import java.util.Arrays;


/**
 * @author gusixue
 * @date 2023/7/22
 */
public class Contest1 {

    public static void main(String[] args) {
        Contest1 contest = new Contest1();

        while (true) {
            int purchaseAmount = AlgorithmUtils.systemInNumberInt();

            int res = contest.solution(purchaseAmount);
            System.out.println(res);
        }

    }

    private int solution(int purchaseAmount) {
        int purchaseAmountLow = purchaseAmount / 10 * 10;
        int purchaseAmountHigh = purchaseAmountLow + 10;
        if (Math.abs(purchaseAmount - purchaseAmountLow) < Math.abs(purchaseAmount - purchaseAmountHigh)) {
            return 100 - purchaseAmountLow;
        } else {
            return 100 - purchaseAmountHigh;
        }
    }



}
