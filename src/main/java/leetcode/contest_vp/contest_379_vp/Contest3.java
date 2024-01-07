package leetcode.contest_vp.contest_379_vp;

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
    public int maximumSetSize(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }

        Set<Integer> set2 = new HashSet<>();
        for (int num : nums2) {
            set2.add(num);
        }

        Set<Integer> setTotal = new HashSet<>();
        setTotal.addAll(set1);
        setTotal.addAll(set2);

        int count1 = 0;
        int count2 = 0;
        for (int num : setTotal) {
            if (set1.contains(num) && !set2.contains(num)) {
                count1++;

            } else if (!set1.contains(num) && set2.contains(num)) {
                count2++;

            }
        }

        for (int num : setTotal) {
            if (set1.contains(num) && set2.contains(num)) {
                if (count1 < nums1.length / 2) {
                    count1++;

                } else {
                    count2++;
                }
            }
        }

        return Math.min(count1, nums1.length / 2) + Math.min(count2, nums1.length / 2);
    }


}
