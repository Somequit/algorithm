package leetcode.contest.contest_396;

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
    public int minimumOperationsToMakeKPeriodic(String word, int k) {
        int n = word.length();

        Map<String, Integer> mapWordCount = new HashMap<>();

        int maxCount = 0;
        for (int i = 0; i < n; i += k) {
            mapWordCount.merge(word.substring(i, i + k), 1, Integer::sum);

            maxCount = Math.max(maxCount, mapWordCount.get(word.substring(i, i + k)));
        }

        return n / k - maxCount;
    }


}
