package leetcode.contest.contest_358;

import utils.AlgorithmUtils;


/**
 *
 */
public class Contest1 {

    public static void main(String[] args) {
        Contest1 contest = new Contest1();

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();

            int res = contest.solution(nums);
            System.out.println(res);
        }

    }

    public int solution(int[] nums) {
        int n = nums.length;

        int[] dig = new int[n];
        for (int i = 0; i < n; i++) {
            dig[i] = getDig(nums[i]);
        }

        int res = -1;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (dig[i] == dig[j]) {
                    res = Math.max(res, nums[i] + nums[j]);
                }
            }
        }
        return res;
    }

    private int getDig(int num) {
        int dig = 0;
        while (num > 0) {
            dig = Math.max(dig, num % 10);
            num /= 10;
        }
        return dig;
    }

}
