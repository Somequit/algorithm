package leetcode.contest.double_128;

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
    public long numberOfSubarrays(int[] nums) {
        long res = nums.length;

        Deque<int[]> stack = new LinkedList<>();
        stack.push(new int[]{nums[0], 1});

        for (int i = 1; i < nums.length; i++) {
            boolean flag = true;
            while (!stack.isEmpty()) {
                int[] stackNum = stack.peekFirst();

                if (stackNum[0] == nums[i]) {
                    res += stackNum[1];
                    stackNum[1]++;
                    flag = false;
                    break;

                } else if (stackNum[0] > nums[i]) {
                    stack.push(new int[]{nums[i], 1});
                    flag = false;
                    break;

                } else {
                    stack.pop();
                }
//                System.out.println(stack + " : " + i + " : " + res);
            }
            if (flag) {
                stack.push(new int[]{nums[i], 1});
            }
        }
//        System.out.println();
        return res;
    }


}
