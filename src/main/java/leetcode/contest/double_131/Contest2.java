package leetcode.contest.double_131;

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
    public int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
        List<Integer> listNumIndex = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == x) {
                listNumIndex.add(i);
            }
        }

        int qLen = queries.length;
        int[] res = new int[qLen];
        for (int i = 0; i < qLen; i++) {
            if (queries[i] > listNumIndex.size()) {
                res[i] = -1;

            } else {
                res[i] = listNumIndex.get(queries[i] - 1);
            }
        }
        return res;
    }


}
