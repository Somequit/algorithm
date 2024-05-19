package leetcode.contest.contest_398;

import java.util.*;
/**
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

//            int res = contest.solution(n, nums, list);
//            System.out.println(res);
        }

    }

    /**
     * @return
     */
    public long sumDigitDifferences(int[] nums) {
        int len = (nums[0] + "").length();
        int n = nums.length;
        int[][] digitCount = new int[len][10];

        long res = 0;
        for (int i = 0; i < n; i++) {
            int tmp = nums[i];
            for (int j = 0; tmp > 0; j++) {
                res += digitCount[j][tmp % 10];
                tmp /= 10;
            }

            tmp = nums[i];
            for (int j = 0; tmp > 0; j++) {
                digitCount[j][tmp % 10]++;
                tmp /= 10;
            }
        }

        return (long) len * n * (n - 1) / 2 - res;
    }


}
