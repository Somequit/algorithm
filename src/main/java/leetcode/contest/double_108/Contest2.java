package leetcode.contest.double_108;

import utils.AlgorithmUtils;

import java.util.*;


/**
 * @author gusixue
 * @date 2023/5/14
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
            int[] moveFrom = AlgorithmUtils.systemInArray();
            int[] moveTo = AlgorithmUtils.systemInArray();

            List<Integer> res = contest.solution(nums, moveFrom, moveTo);
            System.out.println(res);
        }

    }

    private List<Integer> solution(int[] nums, int[] moveFrom, int[] moveTo) {
        Set<Integer> marblesSet = new TreeSet<>();

        for (int i = 0; i < nums.length; i++) {
            marblesSet.add(nums[i]);
        }

        for (int i = 0; i < moveFrom.length; i++) {
            marblesSet.remove(moveFrom[i]);
            marblesSet.add(moveTo[i]);
        }

        return new ArrayList<>(marblesSet);
    }

}
