package leetcode.contest.double_123;

import java.util.*;
/**
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

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
    class Solution {
        public int numberOfPairs(int[][] points) {
            Arrays.sort(points, (o1, o2) -> {
                if (o1[0] == o2[0]) {
                    return o2[1] - o1[1];
                } else {
                    return o1[0] - o2[0];
                }
            });

            int n = points.length;

            int res = 0;
            for (int i = 1; i < n; i++) {
                int maxPoint = Integer.MAX_VALUE;
                for (int j = i - 1; j >= 0; j--) {
                    if (points[j][1] < points[i][1]) {
                        continue;
                    }

                    if (points[j][1] < maxPoint) {
                        res++;
                        maxPoint = points[j][1];
                    }
                }
            }

            return res;
        }
    }


}
