package leetcode.contest.contest_473;

import java.util.HashSet;
import java.util.Set;

/**
 *
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
    public long removeZeros(long n) {
        long res = 0;
        for (char c : (n + "").toCharArray()) {
            if (c != '0') {
                res = res * 10 + (c - '0');
            }
        }

        return res;
    }


}
