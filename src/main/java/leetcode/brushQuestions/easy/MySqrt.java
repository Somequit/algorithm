package leetcode.brushQuestions.easy;


/**
 * 69. x 的平方根
 */
public class MySqrt {

    public static void main(String[] args) {
        MySqrt contest = new MySqrt();

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
    private int solution(int x) {
        // 二分答案，找到平方小于等于 x 的最大值
        int left = 0;
        int right = x;
        int res = 0;

        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            long midPow = (long)mid * mid;
            if (midPow == x) {
                res = mid;
                break;

            } else if (midPow < x) {
                res = mid;
                left = mid + 1;

            } else {
                right = mid - 1;
            }
        }
        return res;
    }


}
