package leetcode.contest.contest_387;

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
    public int[] resultArray(int[] nums) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(nums[0]);
        List<Integer> list2 = new ArrayList<>();
        list2.add(nums[1]);
        for (int i = 2; i < nums.length; i++) {
            if (list1.get(list1.size() - 1) > list2.get(list2.size() - 1)) {
                list1.add(nums[i]);
            } else {
                list2.add(nums[i]);
            }
        }
        list1.addAll(list2);

        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = list1.get(i);
        }
        return res;
    }


}
