package leetcode.contest.contest_385;

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
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        int min = 1;
        int count = 1;
        int res = 0;

        while (true) {
            Set<String> set = new HashSet<>();
            for (int i = 0; i < arr1.length; i++) {
                if (arr1[i] >= min) {
                    set.add((arr1[i] + "").substring(0, count));
                }
            }

            for (int i = 0; i < arr2.length; i++) {
                if (arr2[i] >= min) {
                    if (set.contains((arr2[i]+"").substring(0, count))) {
                        res = count;
                        break;
                    }
                }
            }

            if (res < count) {
                break;
            }
            count++;
            min *= 10;
        }

        return res;
    }


}
