package leetcode.contest.contest_473;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

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
    public long maxAlternatingSum(int[] nums) {
        long res = 0;
        List<Integer> absNumList = Arrays.stream(nums).map(Math::abs).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        for (int i = 0; i < absNumList.size(); i++) {
            if (i < (absNumList.size() + 1) / 2) {
                res += (long) absNumList.get(i) * absNumList.get(i);

            } else {
                res -= (long) absNumList.get(i) * absNumList.get(i);
            }
        }

        return res;
    }


}
