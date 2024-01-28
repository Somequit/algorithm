package leetcode.contest.contest_382;

import java.util.*;
/**
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
    public int maximumLength(int[] nums) {
        Arrays.sort(nums);

        Map<Integer, Integer> mapCount = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            mapCount.merge(nums[i], 1, Integer::sum);
        }

        int res = Math.max(1, mapCount.getOrDefault(1, 0));
        if (res % 2 == 0) {
            res--;
        }
        for (int num : nums) {
            if (num == 1) {
                continue;
            }

            int count = 0;
            if (mapCount.get(num) > 1) {
                count += 2;

                long next = num;
                next *= next;
                while (next <= nums[nums.length - 1] && mapCount.getOrDefault((int)next, 0) > 1) {
                    count += 2;
                    next *= next;
                }

                if (next <= nums[nums.length - 1] && mapCount.getOrDefault((int) next, 0) == 1) {
                    count++;

                } else {
                    count--;
                }

                res = Math.max(res, count);
            }

        }

        return res;
    }


}
