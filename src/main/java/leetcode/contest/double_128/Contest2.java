package leetcode.contest.double_128;

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
    public int minRectanglesToCoverPoints(int[][] points, int w) {
        int n = points.length;
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            set.add(points[i][0]);
        }

        int res = 1;
        int preX = set.first();
        for (Integer x : set) {
            if (x - preX > w) {
                preX = x;
                res++;
            }
        }

        return res;
    }


}
