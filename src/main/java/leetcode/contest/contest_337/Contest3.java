package leetcode.contest.contest_337;


import utils.AlgorithmUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author gusixue
 * @date 2023/3/19
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
            int k = AlgorithmUtils.systemInNumberInt();

            int res = contest.solution(nums, k);
            System.out.println(res);
        }

    }

    private int solution(int[] nums, int k) {
//        Map<Integer, Integer> numsMap = new HashMap<>((k << 2) / 3);
        Map<Integer, Integer> numsMap = new TreeMap<>();
        return recursionNums(nums, 0, k, numsMap) - 1;
    }

    private int recursionNums(int[] nums, int i, int k, Map<Integer, Integer> numsMap) {
        if (i == nums.length) {
            numsMap.forEach((kk, v) -> {
                if (v > 0) {
                    System.out.print(kk + ":" + v + " ");
                }
            } );
            System.out.println();
            return 1;
        }

        int res = 0;

        if (numsMap.getOrDefault(nums[i] + k, 0) == 0
                && numsMap.getOrDefault(nums[i] - k, 0) == 0) {

            numsMap.put(nums[i], numsMap.getOrDefault(nums[i], 0) + 1);

            res += recursionNums(nums, i + 1, k, numsMap);

            numsMap.put(nums[i], numsMap.get(nums[i]) - 1);
        }

        res += recursionNums(nums, i + 1, k, numsMap);

        return res;
    }

}
