package leetcode.contest.double_116;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author gusixue
 * @date 2023/10/28
 */
public class Contest1 {

    public static void main(String[] args) {
        Contest1 contest = new Contest1();

        while (true) {
//            int purchaseAmount = AlgorithmUtils.systemInNumberInt();

//            int res = contest.solution(purchaseAmount);
//            System.out.println(res);
        }

    }

    /**
     * @return
     */
    private int solution(List<Integer> nums) {
        int mod = 1_000_000_007;

        long res = 0L;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i; j < nums.size(); j++) {
                set.add(nums.get(j));
                res += 1L * set.size() * set.size();
                res %= mod;
            }
            set.clear();
        }


        return (int) (res % mod);
    }


}
