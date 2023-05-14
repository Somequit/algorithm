package leetcode.contest.contest_345;

import utils.AlgorithmUtils;

import java.util.Arrays;


/**
 * @author gusixue
 * @date 2023/5/14
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
            int[] derived = AlgorithmUtils.systemInArray();

            boolean res = contest.doesValidArrayExist(derived);
            System.out.println(res);
        }

    }

    private boolean doesValidArrayExist(int[] derived) {
        int len = derived.length;
        int xorRes = 0;
        for (int i = 0; i < len; i++) {
            xorRes ^= derived[i];
        }

        return xorRes == 0;
    }


}
