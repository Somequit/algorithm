package leetcode.contest.double_125;

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
    public int minOperations(int[] nums, int k) {
        Queue<Long> heap = new PriorityQueue<>();
        for (int num : nums) {
            heap.offer((long)num);
        }

        int res = 0;
        while (!heap.isEmpty()) {
            long minNum = heap.poll();

            if (minNum >= k || heap.isEmpty()) {
                break;
            }
            long maxNum = heap.poll();

            long curNum = minNum * 2 + maxNum;
            heap.offer(curNum);
            res++;
        }

        return res;
    }


}
