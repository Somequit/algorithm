package leetcode.contest.contest_366;


import java.util.List;

/**
 * @author gusixue
 * @date 2023/10/08
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
//            int purchaseAmount = AlgorithmUtils.systemInNumberInt();

//            int res = contest.solution(purchaseAmount);
//            System.out.println(res);
        }

    }

    /**
     * @return
     */
    private int solution(List<Integer> nums, int k) {
        int mod = 1_000_000_007;
        long res = 0L;

        long[] ansK = new long[k];
        for (int i = 0; i < 31; i++) {
            int ansCount = 0;
            for (int num : nums) {
                if ((num & (1 << i)) > 0) {
                    ansCount++;
                }
            }

            for (int j = 0; j < Math.min(ansCount, k); j++) {
                ansK[j] += (1 << i);
                ansK[j] %= mod;
            }
        }

        for (int i = 0; i < k; i++) {
            res += ansK[i] * ansK[i];
            res %= mod;
        }

        return (int)res;
    }


}
