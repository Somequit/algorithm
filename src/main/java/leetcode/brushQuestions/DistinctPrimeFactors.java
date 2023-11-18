package leetcode.brushQuestions;

import utils.AlgorithmUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author gusixue
 * @description
 * 2521. 数组乘积中的不同质因数数目
 * 给你一个正整数数组 nums ，对 nums 所有元素求积之后，找出并返回乘积中 不同质因数 的数目。
 * 注意：
 * 质数 是指大于 1 且仅能被 1 及自身整除的数字。
 * 如果 val2 / val1 是一个整数，则整数 val1 是另一个整数 val2 的一个因数。
 * @date 2023/11/18
 */
public class DistinctPrimeFactors {

    public static void main(String[] args) {
        DistinctPrimeFactors distinctPrimeFactors = new DistinctPrimeFactors();
        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
            int res = distinctPrimeFactors.solution(nums);
            System.out.println(res);
        }
    }

    private int solution(int[] nums) {
        Set<Integer> factorSet = new HashSet<>();

        for (int num : nums) {
            for (int i = 2; i * i <= num; i++) {
                if (num % i == 0) {
                    factorSet.add(i);
                    // i 一定是质数
                    while (num % i == 0) {
                        num /= i;
                    }
                }
            }
            if (num > 1) {
                factorSet.add(num);
            }
        }

        return factorSet.size();
    }
}
