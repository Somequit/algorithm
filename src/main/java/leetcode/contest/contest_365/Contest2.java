package leetcode.contest.contest_365;


/**
 * @author gusixue
 * @date 2023/10/01
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
//            int purchaseAmount = AlgorithmUtils.systemInNumberInt();

//            int res = contest.solution(purchaseAmount);
//            System.out.println(res);
        }

    }

    private long solution(int[] nums) {
        long res = 0;

        long prefixMax = nums[0];
        long subtractMax = prefixMax - nums[1];
        for (int i = 2; i < nums.length; i++) {
            prefixMax = Math.max(prefixMax, nums[i - 2]);
            subtractMax = Math.max(subtractMax, prefixMax - nums[i - 1]);
            res = Math.max(res, subtractMax * nums[i]);
        }

        return res;
    }


}
