package leetcode.contest.contest_372;


import utils.AlgorithmUtils;

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
    private int[] solution(int[] heights, int[][] queries) {
        int hLen = heights.length;
        int qLen = queries.length;

        // index-right-maxHeight
        int[][] queriesNew = new int[qLen][3];
        for (int i = 0; i < qLen; i++) {
            int q0 = queries[i][0];
            int q1 = queries[i][1];
            queries[i][0] = Math.min(q0, q1);
            queries[i][1] = Math.max(q0, q1);

            queriesNew[i][0] = i;
            queriesNew[i][1] = Math.max(queries[i][0], queries[i][1]);
            queriesNew[i][2] = Math.max(heights[queries[i][0]], heights[queries[i][1]]);
        }
        Arrays.sort(queriesNew, (o1, o2) -> {
            return o2[1] - o1[1];
        });
        for (int i = 0; i < qLen; i++) {
            System.out.println(Arrays.toString(queriesNew[i]));
        }

        int[] res = new int[qLen];
        Arrays.fill(res, -1);
        // height-index
        TreeMap<Integer, Integer> heigthIndexMap = new TreeMap<>();
        for (int i = hLen - 1, j = 0; i >= 0 && j < qLen; i--) {
            int h = heights[i];
            while (!heigthIndexMap.isEmpty() && heigthIndexMap.firstKey() <= h) {
                heigthIndexMap.remove(heigthIndexMap.firstKey());
            }
            heigthIndexMap.put(h, i);
            System.out.println(heights[i] + " : " + heigthIndexMap);

            while (j < qLen && i == queriesNew[j][1]) {
                int index = queriesNew[j][0];
                int qHeight = queriesNew[j][2];


                if (queries[index][0] == queries[index][1]) {
                    res[index] = queries[index][0];

                } else if (heights[queries[index][0]] < heights[queries[index][1]]) {
                    res[index] = queries[index][1];

                } else {
                    Map.Entry<Integer, Integer> entry = heigthIndexMap.higherEntry(qHeight);
                    if (entry != null) {
                        res[index] = entry.getValue();
                    }
                }

                j++;
            }
        }

        return res;
    }


}
