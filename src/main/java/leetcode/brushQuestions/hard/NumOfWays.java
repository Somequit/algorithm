package leetcode.brushQuestions.hard;

import java.util.Arrays;

/**
 * @author gusixue
 * @description 1411. 给 N x 3 网格图涂色的方案数
 * @date 2026/1/3 7:34 下午
 */
public class NumOfWays {

//    public static void main(String[] args) {
//        new NumOfWays().numOfWays(1);
//    }

//    public int numOfWays(int n) {
//        int mod = (int) (1e9 + 7);
//
//        int[][] dp = new int[8][4];
//        for (int i = 1; i < 8; i++) {
//            for (int j = 1; j < 4; j++) {
//                int[][] color = new int[i + 1][j + 1];
//                for (int ii = 0; ii <= i; ii++) {
//                    Arrays.fill(color[ii], -1);
//                }
//
//                System.out.print(i+" : " + j + " : " + dfs(1,1,i,j, color)/3 + "\t");
//            }
//            System.out.println();
//        }
//
//        return 0;
//    }
//
//    private int dfs(int curi, int curj, int endi, int endj, int[][] color) {
//        if (curj > endj) {
//            curi++;
//            curj = 1;
//        }
//        if (curi > endi) {
//            return 1;
//        }
//
//        int res = 0;
//        for (int c = 0; c < 3; c++) {
//            if (color[curi - 1][curj] != c && color[curi][curj - 1] != c) {
//                color[curi][curj] = c;
//                res += dfs(curi, curj + 1, endi, endj, color);
//            }
//        }
//
//        return res;
//    }


    /**
     * 每行实际仅有：aba、abc 两种情况，开始各占 6 个
     * aba 的下一行有：bab、bcb、cac（看做下一个 aba） 和 bac、cab（看做下一个 abc）
     * abc 的下一行有：bab、bcb（看做下一个 aba） 和 bac、cab（看做下一个 abc）
     * 因此依次往下遍历:
     * aba下一行 = 3 * aba + 2 * abc 且 abc下一行 = 2 * aba + 2 * abc
     */
    public int numOfWays(int n) {
        long aba = 6;
        long abc = 6;
        long mod = (long) (1e9 + 7);

        long abaTmp = 0;
        long abcTmp = 0;
        for (int i = 2; i <= n; i++) {
            abaTmp = 3 * aba + 2 * abc;
            abcTmp = 2 * aba + 2 * abc;

            aba = abaTmp % mod;
            abc = abcTmp % mod;
        }

        return (int) ((aba + abc) % mod);
    }
}
