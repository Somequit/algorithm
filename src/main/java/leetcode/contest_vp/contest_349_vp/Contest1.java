package leetcode.contest_vp.contest_349_vp;


import java.util.Arrays;

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
    private int solution(int[] nums) {
        if (nums.length < 3) {
            return -1;
        }
        // 只对前三个数排序
        Arrays.sort(nums, 0, 3);
        return nums[1];
    }


}
