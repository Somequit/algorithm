package leetcode.contest.contest_476;

import utils.AlgorithmUtils;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/11/15 10:37 下午
 */
public class Contest_476_3 {

    public static void main(String[] args) {
        Contest_476_3 contest = new Contest_476_3();


        while (true) {
            long n = AlgorithmUtils.systemInNumber();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

            System.out.println(contest.countDistinct(n));
//            System.out.println(res);
        }

    }

    public long countDistinct(long n) {
        char[] s = Long.toString(n).toCharArray();
        int m = s.length;

        long res = 0;
        // 从低到高枚举一定有几位
        for (int i = 0; i < m - 1; i++) {
            res += Math.pow(9, i + 1);
        }

        // 必须有 m 位
        for (int i = 0; i < m; i++) {
            if (s[i] == '0') {
                break;
            }

            // 前面等于 n，此位为 [1,s[i]-1]
            res += (long) Math.pow(9, m - i - 1) * (s[i] - '1');

            // 前面等于 n，此位为 s[i]，看下一位
            if (i == m - 1) {
                res++;
            }

        }

        return res;
    }

}
