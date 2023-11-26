package leetcode.contest.contest_373;

import javafx.util.Pair;
import template.Algorithm;
import utils.AlgorithmUtils;

import java.util.*;

/**
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
            String s = AlgorithmUtils.systemInString();
            int k = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

            long res = contest.solution(s, k);
            System.out.println(res);
        }

    }

    /**
     * @return
     */
    private long solution(String s, int k) {
        int n = s.length();
        long res = 0;

        int kTemp = k;
        int kFactorMin = 1;
        for (int i = 2; i * i <= kTemp; i++) {
            int j = 0;
            while (kTemp % i == 0) {
                kTemp /= i;

                if (j % 2 == 1) {
                    kFactorMin *= i;
                }
                j++;
            }
        }
        k /= kFactorMin;
//        System.out.println(k + " : " + kFactorMin);


        int[] preSumV = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSumV[i + 1] = preSumV[i] + (isVowels(s.charAt(i)) ? 1 : 0);
        }
//        System.out.println(Arrays.toString(preSumV));

        Map<Pair<Integer, Integer> , Integer> countMap = new HashMap<>();
        countMap.put(new Pair<>(0, 0), 1);
        for (int i = 0; i < n; i++) {
            int sum = i + 1;
            int vSum = preSumV[i + 1];

            Pair<Integer, Integer> pair = new Pair<>(sum - 2 * vSum, vSum % k);
            int count = countMap.getOrDefault(pair, 0);
            countMap.put(pair, count + 1);

            res += count;

//            for (int j = i; j >= 0; j--) {
//                if (sum - j == 2 * vSum - 2 * preSumV[j] && (vSum - preSumV[j]) % k == 0) {
////                    System.out.println(i + " : " + j);
//                    res++;
//                }
//            }
        }

        return res;
    }

    private boolean isVowels(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }


}
