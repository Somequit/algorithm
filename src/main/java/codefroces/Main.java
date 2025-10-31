package codefroces;

import codefroces.contest.Pinely_Round_5.Contest41;
import codefroces.contest.Pinely_Round_5.Contest4;

import java.io.*;
import java.util.*;

/**
 * @author gusixue
 * @description 模板
 * @date 2025/10/21 10:37 上午
 */
public class Main {
    public static void main(String[] args) throws IOException {
        int n = 9;
        int[] nums = new int[n];
        dfs(0, n, nums);

    }

    private static boolean dfs(int s, int n, int[] nums) {
        if (s >= n) {
//            System.out.println(Arrays.toString(nums));
            Contest41 contest41 = new Contest41();
            int c41 = contest41.solve(nums, n);
            Contest4 contest4 = new Contest4();
            int c4 = contest4.solve(nums, n);
            if (c4 != c41) {
                System.out.println(Arrays.toString(nums));
                System.out.println(c4 + " : " + c41);
                return false;
            }

            return true;
        }

        for (int i = 1; i <= n; i++) {
            nums[s] = i;
            if (!dfs(s + 1, n, nums)) {
                return false;
            }
        }

        return true;
    }

}

