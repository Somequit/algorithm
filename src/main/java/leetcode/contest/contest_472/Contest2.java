package leetcode.contest.contest_472;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

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
    public int longestBalanced(int[] nums) {
        int n = nums.length;

        int res = 0;
        int even = 0;
        int odd = 0;
        Map<Integer, Integer> numCountMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                numCountMap.merge(nums[j - 1], 1, Integer::sum);

                if (numCountMap.get(nums[j - 1]) == 1) {
                    if (nums[j - 1] % 2 == 0) {
                        even++;

                    } else {
                        odd++;
                    }
                }

                if (even == odd) {
                    res = Math.max(res, j - i);
                }
            }

            even = 0;
            odd = 0;
            numCountMap.clear();
        }

        return res;
    }


}
