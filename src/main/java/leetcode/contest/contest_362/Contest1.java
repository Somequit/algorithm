package leetcode.contest.contest_362;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
public class Contest1 {

    public static void main(String[] args) {
        Contest1 contest = new Contest1();

        while (true) {
//            int[] nums = AlgorithmUtils.systemInArray();

//            int res = contest.solution(nums);
//            System.out.println(res);
        }

    }

    public int solution(List<List<Integer>> nums) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.size(); i++) {
            for (int j = nums.get(i).get(0); j <= nums.get(i).get(1); j++) {
                set.add(j);
            }
        }
        return set.size();
    }


}
