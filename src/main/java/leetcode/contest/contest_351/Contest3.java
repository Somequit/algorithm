package leetcode.contest.contest_351;

import utils.AlgorithmUtils;


/**
 * @author gusixue
 * @date 2023/5/14
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();

            int res = contest.solution(nums);
            System.out.println(res);
        }

    }

    public int solution(int[] nums) {
        int mod = 1_000_000_007;
        int len = nums.length;

        int oneIndex = 0;
        for (oneIndex = 0; oneIndex < len; oneIndex++) {
            if (nums[oneIndex] == 1) {
                break;
            }
        }
        if (oneIndex == len) {
            return 0;
        }

        long res = 1;
        while (oneIndex + 1 < len) {
            oneIndex++;
            int zeroNum = 0;
            for (; oneIndex < len; oneIndex++) {
                zeroNum++;
                if (nums[oneIndex] == 1) {
                    break;
                }
            }
            if (oneIndex >= len) {
                break;
            }
            System.out.println(res + ":" + zeroNum + ":" + oneIndex);
            res = res * zeroNum % mod;
        }

        return (int)(res % mod);
    }


}
