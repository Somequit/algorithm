package leetcode.contest.double_120;

import java.util.*;
/**
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

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
    public long incremovableSubarrayCount(int[] nums) {
        int n = nums.length;
        boolean[] prefix = new boolean[n];
        prefix[0] = true;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                prefix[i] = true;

            } else {
                break;
            }
        }

        boolean[] suffix = new boolean[n + 1];
        suffix[n] = true;
        suffix[n - 1] = true;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                suffix[i] = true;

            } else {
                break;
            }
        }
//        System.out.println(Arrays.toString(prefix));
//        System.out.println(Arrays.toString(suffix));

        long res = 0L;
        int minPrefixTrue = 0;
        for (int i = 0; i < n; i++) {
            while (i > minPrefixTrue && prefix[minPrefixTrue] && suffix[i + 1]
                    && (i == n - 1 || nums[i + 1] > nums[minPrefixTrue])) {
                minPrefixTrue++;
            }

//            System.out.println(i + ":" + minPrefixTrue + " : " + suffix[i + 1]);
            if (suffix[i + 1]) {
                res += minPrefixTrue + 1;
            }


        }

        return res;
    }


}
