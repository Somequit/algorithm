package leetcode.contest.double_170;

import utils.AlgorithmUtils;

/**
 * @author gusixue
 * @description
 * @date 2025/11/22 10:58 下午
 */
public class Double_170_2 {
    public static void main(String[] args) {
        Double_170_2 contest = new Double_170_2();

        while (true) {
            int nums1 = AlgorithmUtils.systemInNumberInt();
            int nums2 = AlgorithmUtils.systemInNumberInt();

            System.out.println(contest.totalWaviness(nums1, nums2));
        }

    }

    public int totalWaviness(int num1, int num2) {
        int res = 0;
        for (int i = num1; i <= num2; i++) {
            String str = i + "";
            for (int j = 1; j < str.length() - 1; j++) {
                if (str.charAt(j) > str.charAt(j - 1) && str.charAt(j) > str.charAt(j + 1)) {
                    res++;
                } else if (str.charAt(j) < str.charAt(j - 1) && str.charAt(j) < str.charAt(j + 1)) {
                    res++;
                }
            }
        }

        return res;
    }
}
