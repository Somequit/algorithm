package leetcode.contest.contest_360;


import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
//            int[] nums = AlgorithmUtils.systemInArray();

//            int res = contest.solution(nums);
//            System.out.println(res);
        }

    }

    public long solution(int n, int target) {
        long res = 0;
        Set<Integer> set = new HashSet<>();

        for (int i = 1; set.size() < n; i++) {
            if (!set.contains(target - i)) {
                res += i;
                set.add(i);
            }
        }

        return res;
    }


}
