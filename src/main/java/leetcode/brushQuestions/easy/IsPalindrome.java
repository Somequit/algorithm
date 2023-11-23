package leetcode.brushQuestions.easy;


/**
 */
public class IsPalindrome {

    public static void main(String[] args) {
        IsPalindrome contest = new IsPalindrome();

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
    private boolean solution(int x) {
        if  (x < 0) {
            return false;
        }

        String xStr = x + "";
        int len = xStr.length();
        int half = len >> 1;
        for (int i = 0; i < half; i++) {
            if (xStr.charAt(i) != xStr.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }


}
