package leetcode.contest.double_131;

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
    public int duplicateNumbersXOR(int[] nums) {
        int res = 0;

        Map<Integer, Integer> mapCount = new HashMap<>();
        for (int num : nums) {
            mapCount.merge(num, 1, Integer::sum);

            if (mapCount.get(num) == 2) {
                res ^= num;
            }
        }

        return res;
    }


}
