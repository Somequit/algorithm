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
    public int[] plusOne(int[] digits) {
        List<Integer> listRes = new ArrayList<>();

        int addNum = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            listRes.add((digits[i] + addNum) % 10);
            addNum = (digits[i] + addNum) / 10;
        }
        if (addNum != 0) {
            listRes.add(addNum);
        }

        int[] res = new int[listRes.size()];
        for (int i = 0; i < listRes.size(); i++) {
            res[listRes.size() - i - 1] = listRes.get(i);
        }
        return res;
    }


}
