package leetcode.contest_vp.contest_374_vp;


import java.util.*;

/**
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
    public int minimumAddedCoins(int[] coins, int target) {
        Arrays.sort(coins);

        int n = coins.length;

        int res = 0;
        int index = 0;
        long total = 0;

        for (int i = 1; i <= target; i++) {
            while (index < n && i >= coins[index]) {
                total += coins[index];
                index++;
            }

            while (total < i) {
                total += total + 1;
                res++;
            }

        }

        return res;
    }


}
