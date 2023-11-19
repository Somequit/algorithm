package leetcode.contest.contest_372;


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
    private int solution(String s1, String s2, String s3) {
        int i = 0;
        for (; i < s1.length() && i < s2.length() && i < s3.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i) || s1.charAt(i) != s3.charAt(i)) {
                break;
            }
        }

        if (i == 0) {
            return -1;

        } else {
            return s1.length() + s2.length() + s3.length() - i * 3;
        }
    }


}
