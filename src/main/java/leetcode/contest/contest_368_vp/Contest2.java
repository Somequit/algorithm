package leetcode.contest.contest_368_vp;


import utils.AlgorithmUtils;

import java.util.Arrays;

/**
 * @author gusixue
 * @date 2023/10/08
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();

            int res = contest.solution(nums);
            System.out.println(res);
        }

    }

    /**
     * @return
     */
    private int solution(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = Math.min(prefix[i - 1], nums[i]);
        }
//        System.out.println(Arrays.toString(prefix));
        int[] suffix = new int[n];
        suffix[n-1] = nums[n-1];
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = Math.min(suffix[i + 1], nums[i]);
        }
//        System.out.println(Arrays.toString(suffix));

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < n - 1; i++) {
//            System.out.println(nums[i] + " : " + prefix[i - 1] + " : " + suffix[i + 1]);
            if (nums[i] > prefix[i - 1] && nums[i] > suffix[i + 1]) {
                ans = Math.min(ans, nums[i] + prefix[i - 1] + suffix[i + 1]);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

}
