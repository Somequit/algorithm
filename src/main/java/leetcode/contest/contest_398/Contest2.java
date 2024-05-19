package leetcode.contest.contest_398;

import java.util.*;
/**
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

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
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int len = nums.length;
        int[] prevFar = new int[len];

        int prevFarTmp = 0;
        for (int i = 1; i < len; i++) {
            if (nums[i] % 2 == nums[i - 1] % 2) {
                prevFarTmp = i;
            }

            prevFar[i] = prevFarTmp;
        }

        int resLen = queries.length;
        boolean[] res = new boolean[resLen];
        for (int i = 0; i < resLen; i++) {
            if (prevFar[queries[i][1]] <= queries[i][0]) {
                res[i] = true;
            }
        }
        return res;
    }


}
