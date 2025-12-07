package leetcode.contest.contest_479;

import java.util.*;


/**
 * @author gusixue
 * @description
 * @date 2025/12/7 10:28 上午
 */
public class Contest_479_1 {

    public int[] sortByReflection(int[] nums) {
        return Arrays.stream(nums).boxed().sorted(this::reverseBinCom).mapToInt(Integer::intValue).toArray();
    }

    private int reverseBinCom(Integer i1, Integer i2) {
        long bin1 = doReverse(i1);
        long bin2 = doReverse(i2);

        return bin1 == bin2 ? i1.compareTo(i2) : Long.compare(bin1, bin2);
    }

    private long doReverse(int num) {
        StringBuilder strBd = new StringBuilder(Integer.toBinaryString(num)).reverse();
        long res = 0;
        long pow2 = 1;
        for (int i = strBd.length() - 1; i >= 0; i--) {
            if (strBd.charAt(i) == '1') {
                res += pow2;
            }
            pow2 *= 2;
        }

//        System.out.println(num + " : " + res);

        return res;
    }
}
