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
    public int minimumDistanceOld2(int[][] points) {
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


    /**
     * abs(xi-xj)+abs(yi-yj)，可以转化四种情况、总结起来就是：max(max(x+y)-min(x+y), max(x-y)-min(x-y))
     * 找到 x+y、x-y 分别的最大与最小值四个点，然后分别删除每个点求到最小的结果
     * 注意删掉某个点后，可能同时影响 x+y 与 x-y 的最值
     * 为了方便编写，可以枚举删除每个点
     */
    public int minimumDistanceOld1(int[][] points) {
        int n = points.length;
        List<Integer> sumValIndex = new ArrayList<>();
        List<Integer> diffValIndex = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            sumValIndex.add(i);
            diffValIndex.add(i);
        }

        sumValIndex.sort(Comparator.comparingInt(i -> (points[i][0] + points[i][1])));
        diffValIndex.sort(Comparator.comparingInt(i -> (points[i][0] - points[i][1])));
        Integer maxSumIndex = sumValIndex.get(n - 1);
        Integer minSumIndex = sumValIndex.get(0);
        Integer maxDiffIndex = diffValIndex.get(n - 1);
        Integer minDiffIndex = diffValIndex.get(0);

        int res = Integer.MAX_VALUE;

        // 删除 max(x+y)
        diffValIndex.remove(maxSumIndex);
        sumValIndex.remove(maxSumIndex);
        res = Math.min(res, getMinimumDistance(points, sumValIndex, diffValIndex, n - 1));
        diffValIndex.add(maxSumIndex);
        sumValIndex.add(maxSumIndex);

        // 删除 min(x+y)
        diffValIndex.remove(minSumIndex);
        sumValIndex.remove(minSumIndex);
        res = Math.min(res, getMinimumDistance(points, sumValIndex, diffValIndex, n - 1));
        diffValIndex.add(minSumIndex);
        sumValIndex.add(minSumIndex);

        // 删除 max(x-y)
        diffValIndex.remove(maxDiffIndex);
        sumValIndex.remove(maxDiffIndex);
        res = Math.min(res, getMinimumDistance(points, sumValIndex, diffValIndex, n - 1));
        diffValIndex.add(maxDiffIndex);
        sumValIndex.add(maxDiffIndex);

        // 删除 min(x-y)
        diffValIndex.remove(minDiffIndex);
        sumValIndex.remove(minDiffIndex);
        res = Math.min(res, getMinimumDistance(points, sumValIndex, diffValIndex, n - 1));
        diffValIndex.add(minDiffIndex);
        sumValIndex.add(minDiffIndex);

        return res;
    }

    private int getMinimumDistance(int[][] points, List<Integer> sumValIndex, List<Integer> diffValIndex, int n) {
        sumValIndex.sort(Comparator.comparingInt(i -> (points[i][0] + points[i][1])));
        diffValIndex.sort(Comparator.comparingInt(i -> (points[i][0] - points[i][1])));

        // max(max(x+y)-min(x+y), max(x-y)-min(x-y))
        return Math.max((points[sumValIndex.get(n - 1)][0] + points[sumValIndex.get(n - 1)][1])
                        - (points[sumValIndex.get(0)][0] + points[sumValIndex.get(0)][1]),
                (points[diffValIndex.get(n - 1)][0] - points[diffValIndex.get(n - 1)][1])
                        - (points[diffValIndex.get(0)][0] - points[diffValIndex.get(0)][1]));
    }


    /**
     * abs(xi-xj)+abs(yi-yj)，可以转化四种情况、总结起来就是：max(max(x+y)-min(x+y), max(x-y)-min(x-y))
     * 找到 x+y、x-y 分别的最大与最小值四个点，然后分别删除每个点求到最小的结果
     * 注意删掉某个点后，可能同时影响 x+y 与 x-y 的最值
     * 为了方便编写，可以枚举删除每个点，并使用 TreeMap 增删
     */
    public int minimumDistance(int[][] points) {
        int n = points.length;
        TreeMap<Integer, Integer> sumVal = new TreeMap<>();
        TreeMap<Integer, Integer> diffVal = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            int x = points[i][0];
            int y = points[i][1];
            sumVal.merge(points[i][0] + points[i][1], 1, Integer::sum);
            diffVal.merge(points[i][0] - points[i][1], 1, Integer::sum);
        }

        int res = Integer.MAX_VALUE;

        // 枚举删除每个点即可
        for (int i = 0; i < n; i++) {
            Integer sum = points[i][0] + points[i][1];
            Integer diff = points[i][0] - points[i][1];

            sumVal.merge(sum, -1, Integer::sum);
            if (sumVal.get(sum) == 0) {
                sumVal.remove(sum);
            }
            diffVal.merge(diff, -1, Integer::sum);
            if (diffVal.get(diff) == 0) {
                diffVal.remove(diff);
            }

            // max(max(x+y)-min(x+y), max(x-y)-min(x-y))
            res = Math.min(res, Math.max(sumVal.lastKey() - sumVal.firstKey(), diffVal.lastKey() - diffVal.firstKey()));

            sumVal.merge(sum, 1, Integer::sum);
            diffVal.merge(diff, 1, Integer::sum);
        }

        return res;
    }
}
