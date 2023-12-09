package leetcode.contest.double_119;


import java.util.*;
import java.util.stream.Collectors;

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
    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        int[] res = new int[2];

        res[0] = getIntersectionValues(nums1, nums2);
        res[1] = getIntersectionValues(nums2, nums1);
        return res;
    }

    private int getIntersectionValues(int[] nums1, int[] nums2) {
        int res = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums2) {
            set.add(num);
        }
        for (int num : nums1) {
            if (set.contains(num)) {
                res++;
            }
        }
        return res;
    }


}
