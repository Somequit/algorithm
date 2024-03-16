package leetcode.contest.double_126;

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
    public long[] unmarkedSumArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int m = queries.length;

        int[][] numIndex = new int[n][2];
        long sum = 0L;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            numIndex[i][0] = nums[i];
            numIndex[i][1] = i;
        }

        Arrays.sort(numIndex, ((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]));

//        Arrays.stream(numIndex).forEach(o -> System.out.println(Arrays.toString(o)));

        int curIndex = 0;
        boolean[] indexArr = new boolean[n];
        long[] res = new long[m];
        for (int i = 0; i < m; i++) {
            int index = queries[i][0];
            int k = queries[i][1];

            if (!indexArr[index]) {
                indexArr[index] = true;
                sum -= nums[index];
            }

            for (; curIndex < n && k > 0; curIndex++) {
                index = numIndex[curIndex][1];

                if (!indexArr[index]) {
                    indexArr[index] = true;
                    sum -= nums[index];
                    k--;
                }
            }

            res[i] = sum;
        }


        return res;
    }


}
