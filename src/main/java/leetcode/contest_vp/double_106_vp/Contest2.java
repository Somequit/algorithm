package leetcode.contest_vp.double_106_vp;


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
    private int solution(String s) {
        int res = 1;
        for (int i = 0; i < s.length(); i++) {
            int duplicate = 0;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(j) == s.charAt(j - 1)) {
                    duplicate++;
                }

                if (duplicate > 1) {
                    res = Math.max(res, j - i);
                    break;
                }
            }
            if (duplicate <= 1) {
                res = Math.max(res, s.length() - i);
            }
        }

        return res;
    }


}
