package leetcode.contest_vp.double_114_vp;


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
    private int solution(int[] nums) {
        int res = 0;

        int andTotal = 0;
        for (int i = 0; i < nums.length; i++) {
            if (andTotal == 0) {
                andTotal = nums[i];

            } else {
                andTotal &= nums[i];
            }

            if (andTotal == 0) {
                res++;
            }
        }
        if (res == 0) {
            res = 1;
        }

        return res;
    }


}
