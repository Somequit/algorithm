package leetcode.contest.contest_382;

import java.util.*;
/**
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

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
    public int minOrAfterOperations(int[] nums, int k) {
        int n = nums.length;
        int[] curBitNum = new int[n];
        int[] totalBitNum = new int[n];

        int res = 0;
        // 从高位到低位
        for (int i = 29; i >= 0; i--) {
            int bitVal = (1 << i);

            int bitTotal = 0;
            for (int j = 0; j < n; j++) {
                curBitNum[j] = (bitVal & nums[j]) > 0 ? 1 : 0;
                bitTotal += curBitNum[j];
            }
//            System.out.println(Arrays.toString(curBitNum));

            if (bitTotal == 0) {
                continue;

            } else if (bitTotal == n) {
                res |= bitVal;
                continue;
            }

            int[] totalBitNumTemp = new int[n];
            for (int j = 0; j < n - 1; j++) {
                if (totalBitNum[j] == 0 && totalBitNum[j + 1] == 0) {
                    if (curBitNum[j] == 0 && curBitNum[j + 1] == 0) {

                    } else if (curBitNum[j] == 0 && curBitNum[j + 1] == 1) {
                        totalBitNumTemp[j + 1] = 1;
                    } else if (curBitNum[j] == 1 && curBitNum[j + 1] == 0) {
                        totalBitNumTemp[j] = 1;
                    } else{
                        totalBitNumTemp[j] = 1;
                        totalBitNumTemp[j + 1] = 1;
                    }

                } else if (totalBitNum[j] == 0 && totalBitNum[j + 1] == 1) {
                    if (curBitNum[j] == 0 && curBitNum[j + 1] == 0) {
                        totalBitNumTemp[j + 1] = 1;
                    } else if (curBitNum[j] == 0 && curBitNum[j + 1] == 1) {
                        totalBitNumTemp[j + 1] = 1;
                    } else if (curBitNum[j] == 1 && curBitNum[j + 1] == 0) {
                        totalBitNumTemp[j] = 1;
                        totalBitNumTemp[j + 1] = 1;
                    } else{
                        totalBitNumTemp[j] = 1;
                        totalBitNumTemp[j + 1] = 1;
                    }

                } else if (totalBitNum[j] == 1 && totalBitNum[j + 1] == 0) {
                    if (curBitNum[j] == 0 && curBitNum[j + 1] == 0) {
                        totalBitNumTemp[j] = 1;
                    } else if (curBitNum[j] == 0 && curBitNum[j + 1] == 1) {
                        totalBitNumTemp[j] = 1;
                    } else if (curBitNum[j] == 1 && curBitNum[j + 1] == 0) {
                        totalBitNumTemp[j] = 1;
                    } else{
                        totalBitNumTemp[j] = 1;
                        totalBitNumTemp[j + 1] = 1;
                    }

                } else {
                    totalBitNumTemp[j] = 1;
                    totalBitNumTemp[j + 1] = 1;
                }
            }

            bitTotal = 0;
            for (int j = 0; j < n; j++) {
                bitTotal += totalBitNumTemp[j];
            }
            if (bitTotal > k) {
                res |= bitVal;

            } else {
                totalBitNum = totalBitNumTemp;
            }

        }

        return res;
    }


}
