package leetcode.contest_vp.double_114_vp;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private int solution(List<Integer> nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = nums.size() - 1; i >= 0; i--) {
            int num = nums.get(i);

            if (num <= k) {
                set.add(num);
            }
            if (set.size() == k) {
                return nums.size() - i;
            }
        }
        return -1;
    }


}
