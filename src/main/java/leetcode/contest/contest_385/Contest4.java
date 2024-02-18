package leetcode.contest.contest_385;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
    public long countPrefixSuffixPairs(String[] words) {
        int n = words.length;
        long[] hash1 = new long[n];
        long[] hash2 = new long[n];
        long base1 = 131L;
        long base2 = 13331L;
        long mod = 1_000_000_007L;

        int maxLen = 0;
        for (int i = 0; i < words.length; i++) {
            maxLen = Math.max(maxLen, words[i].length());
            hash1[i] = getHash(words[i], base1, mod);
            hash2[i] = getHash(words[i], base2, mod);
        }
//        System.out.println(Arrays.toString(hash));

        long res = 0;
        Map<Long, Integer>[] hashCountMap = new HashMap[maxLen];
        long[] pow1 = new long[maxLen];
        long[] pow2 = new long[maxLen];
        for (int i = 0; i < maxLen; i++) {
            hashCountMap[i] = new HashMap<>();

            if (i == 0) {
                pow1[i] = 1;
                pow2[i] = 1;

            } else {
                pow1[i] = base1 * pow1[i - 1] % mod;
                pow2[i] = base2 * pow2[i - 1] % mod;
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            hashToMap(words[i + 1], hashCountMap, pow1, base1, pow2, base2, mod);

            res += hashCountMap[words[i].length() - 1].getOrDefault(hash1[i], 0);
        }

        return res;
    }

    private void hashToMap(String word, Map<Long,Integer>[] hashCountMap, long[] pow1, long base1,
                           long[] pow2, long base2, long mod) {
        long hashBegin1 = 0;
        long hashEnd1 = 0;
        long hashBegin2 = 0;
        long hashEnd2 = 0;

        for (int i = 0; i < word.length(); i++) {
            hashBegin1 = base1 * hashBegin1 + (word.charAt(i) - 'a' + 1);
            hashBegin1 %= mod;
            hashEnd1 += pow1[i] * (word.charAt(word.length() - i - 1) - 'a' + 1);
            hashEnd1 %= mod;

            hashBegin2 = base2 * hashBegin2 + (word.charAt(i) - 'a' + 1);
            hashBegin2 %= mod;
            hashEnd2 += pow2[i] * (word.charAt(word.length() - i - 1) - 'a' + 1);
            hashEnd2 %= mod;

//            System.out.println(word + " : " + i + " : " + hashBegin
//                    + " : " + hashEnd + " : " + hashCountMap[i].get(hashBegin));
            if (hashBegin1 == hashEnd1 && hashBegin2 == hashEnd2) {
                hashCountMap[i].merge(hashBegin1,1, Integer::sum);

            }
        }
    }

    private long getHash(String word, long base, long mod) {
        long hash = 0;
        for (int i = 0; i < word.length(); i++) {
            hash = base * hash + (word.charAt(i) - 'a' + 1);
            hash %= mod;
        }
        return hash % mod;
    }


}
