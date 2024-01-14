package leetcode.contest_vp.contest_380_vp;

import java.util.*;
/**
 */
public class Contest1 {

    public static void main(String[] args) {
        Contest1 contest = new Contest1();

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
    public int maxFrequencyElements(int[] nums) {
        Map<Integer, Integer> numCount = new HashMap<>();
        int maxFrequency = 0;
        for (int num : nums) {
            numCount.merge(num, 1, Integer::sum);
            maxFrequency = Math.max(maxFrequency, numCount.get(num));
        }

        int res = 0;
        for (int value : numCount.values()) {
            if (value == maxFrequency) {
                res += value;
            }
        }
        return res;
    }


}
