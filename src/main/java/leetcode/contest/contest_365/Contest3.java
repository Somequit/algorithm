package leetcode.contest.contest_365;


import java.util.HashMap;
import java.util.Map;

/**
 * @author gusixue
 * @date 2023/10/01
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
//            int purchaseAmount = AlgorithmUtils.systemInNumberInt();

//            int res = contest.solution(purchaseAmount);
//            System.out.println(res);
        }

    }

    private int solution(int[] nums, int target) {
        int countAnother = 0;

        long total = 0;
        for (int num : nums) {
            total += num;
        }
        if (target >= total) {
            countAnother += target / total * nums.length;
            target %= total;
        }
        if (target == 0) {
            return countAnother;
        }

        int len = nums.length;
        int count = Integer.MAX_VALUE;

        Map<Long, Integer> prefixMap = new HashMap<>(len << 1);
        prefixMap.put(0L, -1);
        long prefix = 0L;
        for (int i = 0; i < (len << 1); i++) {
            prefix += nums[i % len];

            if (prefixMap.containsKey(prefix - target)) {
                count = Math.min(count, i - prefixMap.get(prefix - target));
            }

            prefixMap.put(prefix, i);
        }

        return count == Integer.MAX_VALUE ? -1 : count + countAnother;
    }


}
