package leetcode.contest338;

import utils.AlgorithmUtils;

import java.util.Arrays;


/**
 * @author gusixue
 * @date 2023/3/19
 */
public class Contest1 {

    public static void main(String[] args) {
        Contest1 contest = new Contest1();

        while (true) {
            int numOnes = AlgorithmUtils.systemInNumberInt();
            int numZeros = AlgorithmUtils.systemInNumberInt();
            int numNegOnes = AlgorithmUtils.systemInNumberInt();
            int k = AlgorithmUtils.systemInNumberInt();

            int res = contest.kItemsWithMaximumSum(numOnes, numZeros, numNegOnes, k);
            System.out.println(res);
        }

    }

    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        int res = 0;
        if (numOnes >= k) {
            res = k;
        } else if (numZeros >= k - numOnes) {
            res = numOnes;
        } else {
            res = numOnes - (k - numOnes - numZeros);
        }
        return res;
    }

}
