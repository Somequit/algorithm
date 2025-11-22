package leetcode.contest.double_170;

import leetcode.contest.double_169.Contest2;
import utils.AlgorithmUtils;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/11/22 10:58 下午
 */
public class Double_170_4 {

    public static void main(String[] args) {
        Double_170_4 contest = new Double_170_4();

        while (true) {
            long nums1 = AlgorithmUtils.systemInNumber();
            long nums2 = AlgorithmUtils.systemInNumber();

            System.out.println(contest.totalWaviness(nums1, nums2));
        }

    }


    public long totalWaviness(long num1, long num2) {
        return doTotalWaviness(num2) - doTotalWaviness(num1 - 1);
    }

    private long doTotalWaviness(long num) {
        List<Integer> peak = new ArrayList<>();
        List<Integer> valley = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    int numPV = i * 100 + j * 10 + k;
                    if (j < i && j < k) {
                        valley.add(numPV);
                    }
                    if (j > i && j > k) {
                        peak.add(numPV);
                    }
                }
            }
        }
//        System.out.println(peak.size());
//        System.out.println(valley.size());

        StringBuilder s = new StringBuilder(num + "");
        int n = s.length();
        long res = 0;
        for (int i = 2; i < n; i++) {
            int curNum = (s.charAt(i - 2) - '0') * 100 + (s.charAt(i - 1) - '0') * 10 + (s.charAt(i) - '0');
//            System.out.println(curNum);

            for (int peakNum : peak) {
                res += doTotalWaviness2(i - 2, curNum, peakNum, s);
//                System.out.println(peakNum + " :" + res);
            }

            for (int valleyNum : valley) {
                res += doTotalWaviness2(i - 2, curNum, valleyNum, s);
//                System.out.println(valleyNum + " :" + res);
            }
        }

        return res;
    }

    private long doTotalWaviness2(int start, int curNum, int vpNum, StringBuilder s) {
        long res = 0;
        if (start == 0) {
            if (vpNum < 100) {
                return 0;
            }

            if (vpNum < curNum) {
                res = (long) Math.pow(10, s.length() - 3);

            } else if (vpNum == curNum) {
                if (3 == s.length()) {
                    res = 1;
                } else {
                    res = Long.parseLong(s.substring(3)) + 1;
                }

            }

        } else {
            if (vpNum < 100) {
                if (vpNum < curNum) {
                    res = (Long.parseLong(s.substring(0, start))) * ((long) Math.pow(10, s.length() - start - 3));

                } else if (vpNum == curNum) {
                    res = (Long.parseLong(s.substring(0, start)) - 1) * ((long) Math.pow(10, s.length() - start - 3));

                    if (start + 3 == s.length()) {
                        res++;
                    } else {
                        res += Long.parseLong(s.substring(start + 3)) + 1;
                    }

                } else {
                    res = (Long.parseLong(s.substring(0, start)) - 1) * ((long) Math.pow(10, s.length() - start - 3));
                }

            } else {
                if (vpNum < curNum) {
                    res = (Long.parseLong(s.substring(0, start)) + 1) * ((long) Math.pow(10, s.length() - start - 3));

                } else if (vpNum == curNum) {
                    res = (Long.parseLong(s.substring(0, start))) * ((long) Math.pow(10, s.length() - start - 3));

                    if (start + 3 == s.length()) {
                        res++;
                    } else {
                        res += Long.parseLong(s.substring(start + 3)) + 1;
                    }

                } else {
                    res = (Long.parseLong(s.substring(0, start))) * ((long) Math.pow(10, s.length() - start - 3));
                }
            }
        }

        return res;
    }
}
