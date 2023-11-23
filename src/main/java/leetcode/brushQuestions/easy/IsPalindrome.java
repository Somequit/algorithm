package leetcode.brushQuestions.easy;


/**
 * 9. 回文数
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 例如，121 是回文，而 123 不是。
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
