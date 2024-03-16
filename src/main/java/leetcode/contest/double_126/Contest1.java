package leetcode.contest.double_126;

import java.util.*;
/**
 */
public class Contest1 {

    public static void main(String[] args) {
        Contest1 contest = new Contest1();

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
    public int sumOfEncryptedInt(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res += encrypt(num);
        }
        return res;
    }

    private int encrypt(int num) {
        String s = num + "";

        int maxNum = 0;
        for (char c : s.toCharArray()) {
            maxNum = Math.max(maxNum, c - '0');
        }

        int res = 0;
        for (char c : s.toCharArray()) {
            res = res * 10 + maxNum;
        }
        return res;
    }


}
