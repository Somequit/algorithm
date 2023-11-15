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
        if (nums.length < 2) {
            return -1;
        }
        Arrays.sort(nums);

        for (int num : nums) {
            if (num > nums[0] && num < nums[nums.length - 1]) {
                return num;
            }
        }

        return -1;
    }


}
