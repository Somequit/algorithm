package leetcode.contest.contest_336;

import utils.AlgorithmUtils;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author gusixue
 * @date 2023/3/12
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
            int nums[] = AlgorithmUtils.systemInArray();

            int res = contest.solution(nums);
            System.out.println(res);
        }

    }

    private int solution(int[] nums) {
        int maxScore = 0;
        if (nums == null || nums.length == 0) {
            return maxScore;
        }

        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));

        Integer[] nums2 = new Integer[]{1, 5, 6, 1, 2, 4};
        Arrays.sort(nums2);
        System.out.println(Arrays.toString(nums2));
        Arrays.sort(nums2, Collections.reverseOrder());
        System.out.println(Arrays.toString(nums2));


        long prefix = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            prefix += nums[i];
            if (prefix > 0) {
                maxScore++;
            }
        }

        return maxScore;
    }

}
