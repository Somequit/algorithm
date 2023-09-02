package leetcode.contest.double_112;

import utils.AlgorithmUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * @author gusixue
 * @date 2023/7/22
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
            String s = AlgorithmUtils.systemInString();
            int k = AlgorithmUtils.systemInNumberInt();

            int res = contest.solution(s, k);
            System.out.println(res);
        }

    }

    private int solution(String s, int k) {
        int mod = 1_000_000_007;

        int[] strCount = new int[26];
        int kindCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (strCount[s.charAt(i) - 'a'] == 0) {
                kindCount++;
            }
            strCount[s.charAt(i) - 'a']++;
        }
//        System.out.println(kindCount);

        if (k > kindCount) {
            return 0;
        }

        Arrays.sort(strCount);
//        System.out.println(Arrays.toString(strCount));

        long res = 1L;
        int sameNum = strCount[26 - k];
        int sameCountTotal = 0;
        int sameCount = 0;
        for (int i = 25; i >= 0; i--) {
            if (strCount[i] > sameNum) {
                res = res * strCount[i] % mod;

            } else if (strCount[i] == sameNum) {
                sameCountTotal++;
                if (i >= 26 - k) {
                    sameCount++;
                }

            } else {
                break;
            }
        }
//        System.out.println(sameNum + " : " + sameCountTotal + " : " + sameCount);

        for (int i = 0; i < sameCount; i++) {
            res = res * sameNum % mod;
        }

        if (sameCountTotal < sameCount * 2) {
            sameCount = sameCountTotal - sameCount;
        }
        long a = 1;
        for (int i = sameCountTotal, j = 0; j < sameCount; i--, j++) {
            a *= i;
        }
        long b = 1;
        for (int i = 1; i <= sameCount; i++) {
            b *= i;
        }
//        System.out.println(a + " : " + b);
        res = res * (a / b) % mod;

        return (int)res;
    }



}
