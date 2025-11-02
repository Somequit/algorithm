package leetcode.contest.contest_474;

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
    public List<Integer> findMissingElements(int[] nums) {
        Arrays.sort(nums);
        List<Integer> res = new ArrayList<>();
        int curNum = nums[0];
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + curNum) {
                res.add(i + curNum);
                curNum++;
            }
        }

        return res;
    }


}
