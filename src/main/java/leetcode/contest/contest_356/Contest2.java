package leetcode.contest.contest_356;

import utils.AlgorithmUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 *
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();

            int res = contest.solution(nums);
            System.out.println(res);
        }

    }

    public int solution(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length << 1);
        for (int num : nums) {
            set.add(num);
        }
        int differentCount = set.size();

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> setTemp = new HashSet<>(nums.length << 1);

            for (int j = i; j < nums.length; j++) {
                setTemp.add(nums[j]);
                if (setTemp.size() == differentCount) {
                    res++;
                }
            }
        }

        return res;
    }


}
