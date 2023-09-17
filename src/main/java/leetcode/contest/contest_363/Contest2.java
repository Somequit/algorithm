package leetcode.contest.contest_363;


import java.util.Collections;
import java.util.List;

/**
 * @author gusixue
 * @date 2023/7/22
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
//            int purchaseAmount = AlgorithmUtils.systemInNumberInt();

//            int res = contest.solution(purchaseAmount);
//            System.out.println(res);
        }

    }

    private int solution(List<Integer> nums) {
        Collections.sort(nums);
        int n = nums.size();

        int res = nums.get(0) == 0 ? 0 : 1;
        for (int i = 0; i < n; i++) {
            if (i + 1 > nums.get(i) && ((i != n - 1 && i + 1 < nums.get(i + 1)) || i == n - 1)) {
                res++;
            }
        }

        return res;
    }



}
