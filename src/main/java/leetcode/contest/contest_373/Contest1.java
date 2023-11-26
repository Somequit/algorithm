package leetcode.contest.contest_373;

import java.util.*;

/**
 */
public class Contest1 {

    public static void main(String[] args) {
        Contest1 contest = new Contest1();

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
    private boolean solution(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        k %= n;

        if (k == 0) {
            return true;
        }

        int gcdKN = gcd(k, n);

        for (int i = 0; i < m; i++) {
            for (int j = gcdKN; j < n; j++) {
                if (mat[i][j] != mat[i][j % gcdKN]) {
                    return false;
                }
            }
        }
        return true;

    }
    private int gcd(int a,int b){
        return (a == 0 ? b : gcd(b % a, a));
    }


}
