package leetcode.contest.contest_369;


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
    private int solution(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < 31; i++) {
            int num = (1 << i);
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if ((nums[j] & num) == num) {
                    count++;
                }
            }

            if (count >= k) {
                res |= num;
            }
        }

        return res;
    }


}
