package leetcode.contest_vp.contest_348_vp;


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
    private int solution(int[] nums) {
        int minIndex = 0;
        int maxIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                minIndex = i;
            }
            if (nums[i] == nums.length) {
                maxIndex = i;
            }
        }

        return minIndex + nums.length - maxIndex - 1 - (minIndex > maxIndex ? 1 : 0);
    }


}
