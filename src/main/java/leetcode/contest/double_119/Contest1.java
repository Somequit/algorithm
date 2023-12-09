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

        Set<Integer> numSet2 = new HashSet<>(Arrays.stream(nums2).boxed().collect(Collectors.toList()));
        for (int num : nums1) {
            if (numSet2.contains(num)) {
                res[0]++;
            }
        }

        Set<Integer> numSet1 = new HashSet<>(Arrays.stream(nums1).boxed().collect(Collectors.toList()));
        for (int num : nums2) {
            if (numSet1.contains(num)) {
                res[1]++;
            }
        }

        return res;
    }


}
