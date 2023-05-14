package leetcode.contest.contest_345;

import utils.AlgorithmUtils;

import java.util.Arrays;


/**
 * @author gusixue
 * @date 2023/5/14
 */
public class Contest1 {

    public static void main(String[] args) {
        Contest1 contest = new Contest1();

        while (true) {
            int n = AlgorithmUtils.systemInNumberInt();
            int k = AlgorithmUtils.systemInNumberInt();

            int[] res = contest.circularGameLosers(n, k);
            System.out.println(Arrays.toString(res));
        }

    }

    private int[] circularGameLosers(int n, int k) {
        int[] gamer = new int[n];

        int pos = 0;
        int round = 1;
        while (gamer[pos] == 0) {
            gamer[pos] = 1;
            pos = (pos + k * round) % n;
            round++;
        }

        int[] res = new int[n - round + 1];
        for (int i = 0,j = 0; i < n; i++) {
            if (gamer[i] == 0) {
                res[j] = i + 1;
                j++;
            }
        }

        return res;
    }


}
