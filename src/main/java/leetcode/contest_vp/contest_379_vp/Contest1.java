package leetcode.contest_vp.contest_379_vp;

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
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int res = 0;
        int maxDiagonal = 0;
        for (int i = 0; i < dimensions.length; i++) {
            int diagonal = dimensions[i][0] * dimensions[i][0] + dimensions[i][1] * dimensions[i][1];
            if (maxDiagonal < diagonal) {
                maxDiagonal = diagonal;
                res = dimensions[i][0] * dimensions[i][1];

            } else if (maxDiagonal == diagonal) {
                res = Math.max(res, dimensions[i][0] * dimensions[i][1]);
            }
        }
        return res;
    }


}
