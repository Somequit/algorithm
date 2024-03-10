package leetcode.contest.contest_388;

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
    public int minimumBoxes(int[] apple, int[] capacity) {
        int sumApple = 0;
        for (int appleVal : apple) {
            sumApple += appleVal;
        }

        Arrays.sort(capacity);

        int res = 0;
        for (int i = capacity.length - 1; i >= 0; i--) {
            sumApple -= capacity[i];
            res++;

            if (sumApple <= 0) {
                break;
            }
        }
        return res;
    }


}
