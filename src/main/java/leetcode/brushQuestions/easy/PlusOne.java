package leetcode.brushQuestions.easy;


import java.util.ArrayList;
import java.util.List;

/**
 * 66. 加一
 */
public class PlusOne {

    public static void main(String[] args) {
        PlusOne contest = new PlusOne();

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
    private int[] solution(int[] digits) {
        List<Integer> resList = new ArrayList<>();
        int carry = 1;

        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] + carry > 9) {
                resList.add((digits[i] + carry) % 10);

            } else {
                resList.add(digits[i] + carry);
                carry = 0;
            }
        }
        if (carry > 0) {
            resList.add(carry);
        }

        int[] res = new int[resList.size()];
        for (int i = resList.size() - 1; i >= 0; i--) {
            res[resList.size() - 1 - i] = resList.get(i);
        }

        return res;
    }


}
