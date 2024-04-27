package leetcode.contest.double_129;

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
    public boolean canMakeSquare(char[][] grid) {
        boolean res = false;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                int bCount = 0;
                if (grid[i][j] == 'B') {
                    bCount++;
                }

                if (grid[i + 1][j] == 'B') {
                    bCount++;
                }

                if (grid[i][j + 1] == 'B') {
                    bCount++;
                }

                if (grid[i + 1][j + 1] == 'B') {
                    bCount++;
                }

                if (bCount >= 3 || bCount <= 1) {
                    res = true;
                    break;
                }
            }
        }

        return res;
    }


}
