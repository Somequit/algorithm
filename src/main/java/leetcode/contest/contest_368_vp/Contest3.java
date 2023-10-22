package leetcode.contest.contest_368_vp;


import utils.AlgorithmUtils;

import java.util.*;

/**
 * @author gusixue
 * @date 2023/10/08
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

    /**
     * @return
     */
    private int solution(int[] nums) {
        Map<Integer, Integer> numCountMap = new HashMap<>();
        for (int num : nums) {
            numCountMap.put(num, numCountMap.getOrDefault(num, 0) + 1);
        }
//        System.out.println(numCountMap);

        TreeMap<Integer, Integer> numCountCountMap = new TreeMap<>();
        for (int numCount : numCountMap.values()) {
            numCountCountMap.put(numCount, numCountCountMap.getOrDefault(numCount, 0) + 1);
        }
//        System.out.println(numCountCountMap);

        int res = 0;
        for (int i = numCountCountMap.firstKey(); i >= 1; i--) {
            int factor = i;
//            System.out.println(factor);

            boolean flag = true;
            res = 0;
            for (Map.Entry<Integer, Integer> entry : numCountCountMap.entrySet()) {
                int num = entry.getKey();
                int count = entry.getValue();
                if (num % factor > num / factor) {
                    flag = false;
                    break;
                }
                res += (num / (factor + 1) + Math.min(num % (factor + 1), 1)) * count;
            }
            if (flag) {
                break;
            }
        }

        return res;
    }


}
