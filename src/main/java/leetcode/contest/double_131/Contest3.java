package leetcode.contest.double_131;

import java.util.*;
/**
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

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
    public int[] queryResults(int limit, int[][] queries) {
        Map<Integer, Integer> mapBallColor = new HashMap<>();
        Map<Integer, Integer> mapColorCount = new HashMap<>();

        int n = queries.length;
        int[] res = new int[n];

        mapBallColor.put(queries[0][0], queries[0][1]);
        mapColorCount.put(queries[0][1], 1);
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            int ball = queries[i][0];
            int color = queries[i][1];

            if (mapBallColor.containsKey(ball)) {
                mapColorCount.merge(mapBallColor.get(ball), -1, Integer::sum);
                res[i] = res[i - 1] - (mapColorCount.get(mapBallColor.get(ball)) == 0 ? 1 : 0);

                mapBallColor.put(ball, color);
                mapColorCount.merge(color, 1, Integer::sum);
                res[i] += (mapColorCount.get(color) == 1 ? 1 : 0);

            } else {
                mapBallColor.put(ball, color);
                mapColorCount.merge(color, 1, Integer::sum);
                res[i] = res[i - 1] + (mapColorCount.get(color) == 1 ? 1 : 0);
            }
        }

        return res;
    }


}
