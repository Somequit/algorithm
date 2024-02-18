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

    public long countPrefixSuffixPairs2(String[] words) {
        long res = 0;
        Map<Long, Integer> hashCountMap = new HashMap<>();

        for (int i = words.length - 1; i >= 0; i--) {
            StringHash stringHash = new StringHash(words[i]);

            if (i < words.length - 1) {
                long hash = stringHash.query(0, words[i].length() - 1);
                res += hashCountMap.getOrDefault(hash, 0);
            }

            for (int j = 0; j < words[i].length(); j++) {
                long hashBegin = stringHash.query(0, j);
                long hashEnd = stringHash.query(words[i].length() - j - 1, words[i].length() - 1);
                if (hashBegin == hashEnd) {
                    hashCountMap.merge(hashBegin, 1, Integer::sum);
                }
            }

        }

        return res;
    }

    static class StringHash {
        char[] str;
        long seed = 31;
        long mod = 1_000_000_007;

        int n;
        // hash前缀和
        long[] pre;
        // p的幂次
        long[] pow;

        StringHash(String s, int seed, long mod) {
            this.str = s.toCharArray();
            this.seed = seed;
            this.mod = mod;

            preHash();
        }

        private void preHash() {
            this.n = this.str.length;
            pre = new long[n + 1];
            pow = new long[n + 1];

            pow[0] = 1;
            for (int i = 1; i <= n; i++) {
                pow[i] = pow[i - 1] * seed % mod;
            }
            for (int i = 0; i < str.length; i++) {
                pre[i + 1] = (pre[i] * seed % mod + str[i]) % mod;
            }
        }

        StringHash(String s) {
            this.str = s.toCharArray();

            preHash();
        }

        /**
         * 左右均为闭区间
         */
        public long query(int l, int r) {
            long res = pre[r + 1] - pre[l] * pow[r - l + 1] % mod;
            return (res % mod + mod) % mod;
        }

        public long rotate(int l) {
            if (l < 0 || l >= str.length - 1) {
                return query(0, str.length - 1);
            } else {
                long h1 = query(0, l);
                long h2 = query(l + 1, str.length - 1);
                return (h2 * pow[l + 1] % mod + h1) % mod;
            }
        }

        public long evaluate(String s) {
            long h = 0;
            for (char c: s.toCharArray()) {
                h = (h * this.seed % this.mod + c) % mod;
            }
            return h;
        }
    }


}
