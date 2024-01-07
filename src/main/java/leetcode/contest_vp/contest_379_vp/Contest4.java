package leetcode.contest_vp.contest_379_vp;

import java.util.*;
/**
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

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
    public int maxPartitionsAfterOperations(String s, int k) {
        Arrays.sort(s.toCharArray());
        if (k == 26) {
            return 1;
        }
        s += "0";

        int res = 0;
        boolean change = false;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < s.length() - 1; i++) {
            set.add(s.charAt(i) - 'a');

            if (set.size() == k && s.charAt(i) != s.charAt(i + 1)) {
                res++;
                set.clear();
            }
        }

        if (!set.isEmpty()) {
            res++;
        }


        return res;
    }


}
