package leetcode.contest.contest_391;

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
    public int minimumDistance(int[][] points) {
        int n = points.length;

        int[] resIndex = doMinimumDistance(points);
//        System.out.println(Arrays.toString(resIndex));

        int[][] points2 = doPoints(points, resIndex[0]);
//        Arrays.stream(points2).forEach(i -> System.out.println(Arrays.toString(i)));
        int[] resIndex2 = doMinimumDistance(points2);
//        System.out.println(Arrays.toString(resIndex2));

        int[][] points3 = doPoints(points, resIndex[1]);
//        Arrays.stream(points3).forEach(i -> System.out.println(Arrays.toString(i)));
        int[] resIndex3 = doMinimumDistance(points3);
//        System.out.println(Arrays.toString(resIndex3));

        return Math.min(Math.abs(points2[resIndex2[0]][0] - points2[resIndex2[1]][0])
                        + Math.abs(points2[resIndex2[0]][1] - points2[resIndex2[1]][1]),
                Math.abs(points3[resIndex3[0]][0] - points3[resIndex3[1]][0])
                        + Math.abs(points3[resIndex3[0]][1] - points3[resIndex3[1]][1]));
    }

    private int[][] doPoints(int[][] points, int resIndex) {
        int n = points.length;

        int[][] resPoints = new int[n - 1][2];
        for (int i = 0; i < n; i++) {
            if (i < resIndex) {
                resPoints[i] = points[i];

            } else if (i > resIndex) {
                resPoints[i - 1] = points[i];
            }
        }

        return resPoints;
    }

    private int[] doMinimumDistance(int[][] points) {
        int n = points.length;

        int maxSum = points[0][0] + points[0][1];
        int minSum = points[0][0] + points[0][1];
        int maxDiff = points[0][0] - points[0][1];
        int minDiff = points[0][0] - points[0][1];
        for (int i = 1; i < n; i++) {
            maxSum = Math.max(maxSum, points[i][0] + points[i][1]);
            minSum = Math.min(minSum, points[i][0] + points[i][1]);
            maxDiff = Math.max(maxDiff, points[i][0] - points[i][1]);
            minDiff = Math.min(minDiff, points[i][0] - points[i][1]);
        }

        List<Integer> listIndex1 = new ArrayList<>();
        List<Integer> listIndex2 = new ArrayList<>();
        List<Integer> listIndex3 = new ArrayList<>();
        List<Integer> listIndex4 = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (points[i][0] + points[i][1] == maxSum) {
                listIndex1.add(i);
            }

            if (points[i][0] + points[i][1] == minSum) {
                listIndex2.add(i);
            }

            if (points[i][0] - points[i][1] == maxDiff) {
                listIndex3.add(i);
            }

            if (points[i][0] - points[i][1] == minDiff) {
                listIndex4.add(i);
            }
        }

//        System.out.println(listIndex1);
//        System.out.println(listIndex2);
//        System.out.println(listIndex3);
//        System.out.println(listIndex4);

        Set<Integer> setIndex = new HashSet<>();
        toAddMinMaxIndex(listIndex1, setIndex, points);
        toAddMinMaxIndex(listIndex2, setIndex, points);
        toAddMinMaxIndex(listIndex3, setIndex, points);
        toAddMinMaxIndex(listIndex4, setIndex, points);
        List<Integer> listIndexFinal = new ArrayList<>(setIndex);
//        System.out.println(setIndex);

        int[] resIndex = new int[2];
        int res = -1;
        for (int i = 0; i < listIndexFinal.size(); i++) {
            for (int j = i + 1; j < listIndexFinal.size(); j++) {
                int index = listIndexFinal.get(i);
                int index2 = listIndexFinal.get(j);
                int curDis = Math.abs(points[index][0] - points[index2][0])
                        + Math.abs(points[index][1] - points[index2][1]);

                if (res < curDis) {
                    res = curDis;
                    resIndex[0] = index;
                    resIndex[1] = index2;
                }
//                System.out.println(index + " : " + index2);
            }
        }

        return resIndex;
    }

    private void toAddMinMaxIndex(List<Integer> listIndex, Set<Integer> setIndex, int[][] points) {
        listIndex.sort((i1, i2) ->
                points[i1][0] == points[i2][0] ? points[i1][1] - points[i2][1] : points[i1][0] - points[i2][0]);
        setIndex.add(listIndex.get(0));
        setIndex.add(listIndex.get(listIndex.size() - 1));

        listIndex.sort((i1, i2) ->
                points[i1][1] == points[i2][1] ? points[i1][0] - points[i2][0] : points[i1][1] - points[i2][1]);
        setIndex.add(listIndex.get(0));
        setIndex.add(listIndex.get(listIndex.size() - 1));
    }


}
