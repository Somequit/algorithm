package leetcode.contest.contest_362;


import utils.AlgorithmUtils;

import java.math.BigInteger;
import java.util.Arrays;

/**
 *
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

//        long[][] res1 = contest.matrixMultiply(new long[][]{{1,2},{3,4},{5,6}}, new long[][]{{7,8,9},{10, 11, 12}}, 1_000_000_007);
//        AlgorithmUtils.systemOutArray(res1);
//        long[] res2 = contest.matrixMultiply(new long[]{1, 2, 3}, new long[][]{{4, 5, 6}, {7, 8, 9}, {10, 11, 12}}, 1_000_000_007);
//        System.out.println(Arrays.toString(res2));

        while (true) {
            String s = AlgorithmUtils.systemInString();
            String t = AlgorithmUtils.systemInString();
            long k = AlgorithmUtils.systemInNumber();

            int res = contest.solution(s, t, k);
            System.out.println(res);
        }

    }

    public int solution(String s, String t, long k) {
        int mod = 1_000_000_007;

        int len = s.length();

        // kmp 匹配
        int[] next = kmpNext(s);
        int kmpLen = KmpMatch(t+t, s, next);
        if (kmpLen == -1) {
            return 0;
        }
//        System.out.println(kmpLen);

        // 矩阵快速幂优化 k
        long[] matrixBase = new long[]{0, 1};
        if (k > 1) {
            long[][] matrixPow = new long[][]{{0, len - 1}, {1, len - 2}};
            matrixBase = quickMatrix(matrixBase, matrixPow, k - 1, mod);
        }
//        System.out.println(Arrays.toString(matrixBase));

        // next 求最小循环节
        int minCycleSection = len;
        if (len % (len - next[len - 1]) == 0) {
            minCycleSection = len - next[len - 1];
        }
//        System.out.println(minCycleSection);

        long res = 0L;
        // s 与 t+t 匹配到 len 即成功，意思是 s==t
        if (kmpLen == len) {
            res = matrixBase[0];
            res = res + (matrixBase[1] * (len / minCycleSection - 1)) % mod;
            res %= mod;
        } else {
            res = matrixBase[1] * (len / minCycleSection) % mod;
        }

        return (int)res;
    }

    private long[] quickMatrix(long[] matrixBase, long[][] matrixPow, long k, int mod) {
        while (k > 0) {
            if ((k & 1) == 1) {
                matrixBase = matrixMultiply(matrixBase, matrixPow, mod);
            }

            matrixPow = matrixMultiply(matrixPow, matrixPow, mod);
            k >>= 1;
        }

        return matrixBase;
    }

    private long[][] matrixMultiply(long[][] matrixFirst, long[][] matrixSecond, int mod) {
        int n = matrixFirst.length;
        int m = matrixSecond[0].length;
        int nn = matrixFirst[0].length;
        long[][] res = new long[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < nn; k++) {
//                    System.out.println(i + " : " + j + " : " + k + " : " + matrixFirst[i][k] + " : " + matrixSecond[k][j]);
                    res[i][j] = (res[i][j] + matrixFirst[i][k] * matrixSecond[k][j] % mod) % mod;
                }
            }
        }

        return res;
    }

    private long[] matrixMultiply(long[] matrixFirst, long[][] matrixSecond, int mod) {
        int n = matrixFirst.length;
        int m = matrixSecond[0].length;
        long[] res = new long[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
//                System.out.println(i + " : " + j + " : " + matrixFirst[j] + " : " + matrixSecond[i][j]);
                res[i] = (res[i] + matrixFirst[j] * matrixSecond[i][j] % mod) % mod;
            }
        }

        return res;
    }

    // 获取到一个字符串的 next 数组
    public int[] kmpNext(String dest) {
        int len = dest.length();
        int[] next = new int[len];

        for (int i = 1, j = 0; i < dest.length(); i++) {

            while (j > 0 && dest.charAt(j) != dest.charAt(i)) {
                j = next[j - 1];
            }

            if (dest.charAt(j) == dest.charAt(i)) {
                j++;
            }

            next[i] = j;
        }
        return next;
    }


    // KMP 搜索算法
    public int KmpMatch(String str1, String str2, int[] next) {

        for (int i = 0, j = 0; i < str1.length(); i++) {
            if (j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j - 1];
            }


            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }

            // 匹配成功
            if (j == str2.length()) {
                return i + 1;
            }
        }
        // 匹配失败
        return -1;
    }



}
