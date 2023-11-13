package leetcode.contest_vp.double_114_vp;


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
        int res = 0;
        int[] count = new int[1_000_001];

        for (int num : nums) {
            count[num]++;
        }

        for (int countNum : count) {
            if (countNum == 0) {
                continue;
            }

            if (countNum == 1) {
                res = -1;
                break;

            } else {
                res += (countNum + 2) / 3;
            }
        }

        return res;
    }


}
