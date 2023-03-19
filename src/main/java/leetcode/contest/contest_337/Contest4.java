package leetcode.contest.contest_337;


import utils.AlgorithmUtils;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author gusixue
 * @date 2023/3/19
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
            int value = AlgorithmUtils.systemInNumberInt();

            int res = contest.solution(nums, value);
            System.out.println(res);
        }

    }

    private int solution(int[] nums, int value) {
        if (value == 1) {
            return nums.length;
        }

        int[] cnt = new int[value];

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                nums[i] += ((-nums[i] + value - 1) / value) * value;
            } else if (nums[i] >= value) {
                nums[i] -= (nums[i] / value) * value;
            }
            cnt[nums[i]]++;
        }
//        System.out.println(Arrays.toString(cnt));

        for (int i = 0; i < nums.length; i++) {
            if (cnt[i % value] == 0) {
                return i;
            }
            cnt[i % value]--;
        }

        return nums.length;

//        int minNum = Integer.MAX_VALUE;
//        for (int i = 0; i < value; i++) {
//            if (cnt[i] == 0) {
//                return i;
//            }
//            minNum = Math.min(minNum, cnt[i]);
//        }
//        System.out.println(minNum);
//
//        int surplus = 0;
//        for (int i = 0; i < value; i++) {
//            if (cnt[i] == minNum) {
//                break;
//            }
//            surplus++;
//        }
//        System.out.println(surplus);
//
//        return value * minNum + surplus;
    }

}
