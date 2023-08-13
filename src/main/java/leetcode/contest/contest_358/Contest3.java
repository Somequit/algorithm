package leetcode.contest.contest_358;

import utils.AlgorithmUtils;

import java.util.List;
import java.util.TreeSet;


/**
 *
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
            List<Integer> nums = AlgorithmUtils.systemInList();
            int x = AlgorithmUtils.systemInNumberInt();

            int res = contest.solution(nums, x);
            System.out.println(res);
        }

    }

    public int solution(List<Integer> nums, int x) {
        if (x == 0) {
            return 0;
        }

        int res = Integer.MAX_VALUE;
        int n = nums.size();
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = n - 1 - x; i >= 0; i--) {
            treeSet.add(nums.get(i + x));
//            System.out.println(treeSet + " : " + nums.get(i));
//            System.out.println(treeSet.floor(nums.get(i)));
//            System.out.println(treeSet.ceiling(nums.get(i)));

            if (treeSet.floor(nums.get(i)) != null) {
                res = Math.min(res, Math.abs(nums.get(i) - treeSet.floor(nums.get(i))));
            }
            if (treeSet.ceiling(nums.get(i)) != null) {
                res = Math.min(res, Math.abs(nums.get(i) - treeSet.ceiling(nums.get(i))));
            }

        }
        return res;
    }


}
