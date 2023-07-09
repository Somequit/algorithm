package leetcode.contest.contest_353;

import utils.AlgorithmUtils;

import java.util.*;


/**
 * @author gusixue
 * @date 2023/5/14
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
            int k = AlgorithmUtils.systemInNumberInt();

            boolean res = contest.solution(nums, k);
            System.out.println(res);
        }

    }

    public boolean solution(int[] nums, int k) {
        if(k == 1 || (nums[0] == 0 && nums.length == 1)) {
            return true;
        }

        int total = 0;
        Queue<String> queue = new ArrayDeque<>(nums.length);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < total) {
                return false;
            }

            if (nums[i] > total) {
                queue.offer((nums[i] - total) + "_" + i);
                total = nums[i];
            }

            if (!queue.isEmpty()) {
                String numberIndex = queue.peek();
                int number = Integer.parseInt(numberIndex.split("_")[0]);
                int index = Integer.parseInt(numberIndex.split("_")[1]);

                if (i - index + 1 == k) {
                    queue.poll();
                    total -= number;
                }
            }
        }

        return queue.isEmpty();
    }


}
