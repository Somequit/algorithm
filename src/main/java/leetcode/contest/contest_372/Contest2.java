package leetcode.contest.contest_372;


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
    private long solution(String s) {
        long res = 0L;
        int oneCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                res += oneCount;

            } else {
                oneCount++;
            }
        }
        return res;
    }


}
