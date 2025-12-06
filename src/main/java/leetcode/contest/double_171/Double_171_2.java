package leetcode.contest.double_171;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/12/6 10:24 下午
 */
public class Double_171_2 {

    public static void main(String[] args) {
        Double_171_2 contest = new Double_171_2();

        contest.minOperations(new int[]{1});
    }

    public int[] minOperations(int[] nums) {
        TreeSet<Integer> treeSetPal = new TreeSet<>();
        for (int i = 1; i < 1000000001; i++) {
            StringBuilder binStr = new StringBuilder(Integer.toBinaryString(i));
            StringBuilder binStrReverse = new StringBuilder(binStr).reverse();
//            System.out.println(binStr + " : " + binStrReverse);
            if ((binStr.toString()).equals(binStrReverse.toString())) {
                treeSetPal.add(i);
            }
        }
//        System.out.println(treeSetPal.size());

        int n = nums.length;
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            res[i] = Math.min(Math.abs(treeSetPal.ceiling(nums[i]) - nums[i]), Math.abs(treeSetPal.floor(nums[i]) - nums[i]));
        }

        return res;
    }

    public int[] minOperationsOptimization(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < nums[i]; j++) {
                if (check(nums[i] + j) || check(nums[i] - j)) {
                    res[i] = j;
                    break;
                }
            }
        }

        return res;
    }

    private boolean check(int num) {
        StringBuilder str = new StringBuilder(Integer.toBinaryString(num));
        StringBuilder strRev = new StringBuilder(Integer.toBinaryString(num)).reverse();
//        System.out.println(str + " : " + strRev);
        return (str.toString()).equals(strRev.toString());
    }

}
