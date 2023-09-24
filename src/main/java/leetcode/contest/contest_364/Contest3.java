package leetcode.contest.contest_364;


import javafx.util.Pair;
import utils.AlgorithmUtils;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author gusixue
 * @date 2023/7/22
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
            List<Integer> maxHeights = AlgorithmUtils.systemInList();
            long res = contest.solution(maxHeights);
            System.out.println(res);
        }

    }

    /**
     * 单调栈 + 两遍统计
     * @param maxHeights
     * @return
     */
    private long solution(List<Integer> maxHeights) {
        if(maxHeights.size() == 1) {
            return maxHeights.get(0);
        }

        int len = maxHeights.size();
        // 从左向右（含当前）
        long[] leftTotal = new long[len];

        // num-count
        Deque<Pair<Integer, Integer>> increaseStack = new LinkedList<>();
        increaseStack.push(new Pair<>(0, 0));
        long curTotal = 0;

        for (int i = 0; i < len; i++) {
            int count = 1;
            while (increaseStack.peekFirst().getKey() >= maxHeights.get(i)) {
                Pair<Integer, Integer> pair = increaseStack.pop();
                count += pair.getValue();
                curTotal -= (long)pair.getKey() * pair.getValue();
            }

            increaseStack.push(new Pair<>(maxHeights.get(i), count));
            curTotal += (long)maxHeights.get(i) * count;

            leftTotal[i] = curTotal;
        }


        // 从右向左（含当前）
        long[] rightTotal = new long[len];
        increaseStack.clear();
        increaseStack.push(new Pair<>(0, 0));
        curTotal = 0;

        for (int i = len - 1; i >= 0; i--) {
            int count = 1;
            while (increaseStack.peekFirst().getKey() >= maxHeights.get(i)) {
                Pair<Integer, Integer> pair = increaseStack.pop();
                count += pair.getValue();
                curTotal -= (long) pair.getKey() * pair.getValue();
            }

            increaseStack.push(new Pair<>(maxHeights.get(i), count));
            curTotal += (long) maxHeights.get(i) * count;

            rightTotal[i] = curTotal;
        }

//        System.out.println(Arrays.toString(leftTotal));
//        System.out.println(Arrays.toString(rightTotal));

        long res = 0L;
        for (int i = 0; i < len; i++) {
            res = Math.max(res, leftTotal[i] + rightTotal[i] - maxHeights.get(i));
        }

        return res;
    }


}
