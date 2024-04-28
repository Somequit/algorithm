package leetcode.contest.contest_395;

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
    public long minEnd2(int n, int x) {
        int[] bitCount = new int[63];

        int i = 0;
        while (x > 0) {
            if (x % 2 == 1) {
                bitCount[i] = 1;

            }
            i++;
            x >>= 1;
        }
//        System.out.println(Arrays.toString(bitCount));

        n--;
        i = 0;
        while (n > 0) {

            while (i < bitCount.length && bitCount[i] == 1) {
                i++;
            }

            if (n % 2 == 1) {
                bitCount[i] = 1;
            }

            i++;
            n >>= 1;
        }
//        System.out.println(Arrays.toString(bitCount));

        long res = 0;
        for (i = 0; i < bitCount.length; i++) {
            if (bitCount[i] == 1) {
                res |= (1L << i);
            }
        }

        return res;
    }


    public long minEnd(int n, int x) {
        long res = x;

        n--;
        long val = 1;
        while (n > 0) {
            while ((x & val) != 0) {
                val *= 2;
            }

            if (n % 2 == 1) {
                res |= val;
            }

            val *= 2;
            n >>= 1;
        }

        return res;
    }

}
