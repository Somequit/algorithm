package leetcode.contest.double_117;


import javafx.util.Pair;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

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
    private long solution(int[][] values) {
        int m = values.length;
        int n = values[0].length;

        // values i-j
        Queue<Pair<Integer, Integer>> minHeap = new PriorityQueue<>(new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                return values[o1.getKey()][o1.getValue()] - values[o2.getKey()][o2.getValue()];
            }
        });
        for (int i = 0; i < m; i++) {
            minHeap.add(new Pair<>(i, n - 1));
        }

        long res = 0L;
        for (int i = 1; i <= m * n; i++) {
            Pair<Integer, Integer> pair = minHeap.poll();
            if (pair.getValue() > 0) {
                minHeap.add(new Pair<>(pair.getKey(), pair.getValue() - 1));
            }

            res += (long)i * values[pair.getKey()][pair.getValue()];
        }

        return res;
    }


}
