package leetcode.contest.contest_478;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/11/30 10:24 上午
 */
public class Contest_478_3 {
    public static void main(String[] args) {
        Contest_478_3 contest = new Contest_478_3();

        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

//            int res = contest.solution(n, nums, list);
//            System.out.println(res);
        }

    }

    public int minMirrorPairDistance(int[] nums) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        int n = nums.length;
        int res = n;

        treeMap.put(nums[n - 1], n - 1);
        for (int i = n - 2; i >= 0; i--) {
            int reverseNum = reverse(nums[i]);
            if (treeMap.getOrDefault(reverseNum, -1) != -1) {
                res = Math.min(res, treeMap.get(reverseNum) - i);
            }

            treeMap.put(nums[i], i);
        }

        return res == n ? -1 : res;
    }

    private int reverse(int num) {
        int res = 0;
        int powNum = 1;
        for (char c : (num + "").toCharArray()) {
            res += (c-'0') * powNum;
            powNum *= 10;
        }
//        System.out.println(res);
        return res;
    }


}
