package leetcode.contest.contest_381;

import utils.AlgorithmUtils;

import java.util.*;
/**
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
            int n = AlgorithmUtils.systemInNumberInt();
            int x = AlgorithmUtils.systemInNumberInt();
            int y = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

            long[] res = contest.countOfPairs(n, x, y);
            System.out.println(Arrays.toString(res));
        }

    }

    /**
     * @return
     */
    public long[] countOfPairs(int n, int x, int y) {
        long[] res = new long[n];

        for (int i = 0; i < n; i++) {
            res[i] = 2 * (n - i - 1);
        }
        if (Math.abs(x - y) <= 1) {
            return res;
        }
//        System.out.println(Arrays.toString(res));

        int min = Math.min(x, y);
        int max = Math.max(x, y);
        int leftCount = min;
        int rightCount = n - max + 1;

        long[] prefix = new long[n + 1];
        for (int i = 0; i < leftCount; i++) {
            prefix[max - i - 2] -= 2;
            prefix[n - i - 1] += 2;

            prefix[max - i - 2 - (max - min - 1)] += 2;
            prefix[n - i - 1 - (max - min - 1)] -= 2;
        }
        for (int i = 1; i <= n; i++) {
            prefix[i] += prefix[i - 1];
            res[i - 1] += prefix[i - 1];
        }
        if (Math.abs(x - y) <= 3) {
            return res;
        }
//        System.out.println(leftCount + " : " + rightCount + " : " + Arrays.toString(res));


        Arrays.fill(prefix, 0);
        for (int i = 0; i < (max - min) / 2 - 1; i++) {
            prefix[max - min - i - 2] -= 2;
            prefix[max - min - i - 1 + leftCount - 1] += 2;
            prefix[2 + i - 1] += 2;
            prefix[2 + i + leftCount - 1] -= 2;


            prefix[max - min - i - 2] -= 2;
            prefix[max - min - i - 1 + rightCount - 1] += 2;
            prefix[2 + i - 1] += 2;
            prefix[2 + i + rightCount - 1] -= 2;
        }
        for (int i = 1; i <= n; i++) {
            prefix[i] += prefix[i - 1];
            res[i - 1] += prefix[i - 1];
        }


        if (Math.abs(x - y) <= 5) {
            return res;
        }
//        System.out.println(leftCount + " : " + rightCount + " : " + Arrays.toString(res));


        Arrays.fill(prefix, 0);
        for (int i = 0; i < (max - min) / 2 - 2; i++) {
            prefix[max - min - i - 3] -= 2 * (i + 1);
            prefix[max - min - i - 2] += 2 * (i + 1);
            prefix[3 + i - 1] += 2 * (i + 1);
            prefix[3 + i] -= 2 * (i + 1);
        }
        for (int i = 1; i <= n; i++) {
            prefix[i] += prefix[i - 1];
            res[i - 1] += prefix[i - 1];
        }

        return res;
    }


}
