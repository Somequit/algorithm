package leetcode.brushQuestions.easy;


/**
 * 67. 二进制求和
 */
public class AddBinary {

    public static void main(String[] args) {
        AddBinary contest = new AddBinary();

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
    private String solution(String a, String b) {
        int carry = 0;
        StringBuilder res = new StringBuilder();

        int aIndex = a.length() - 1;
        int bIndex = b.length() - 1;
        while (aIndex >= 0 || bIndex >= 0) {
            carry += aIndex < 0 ? 0 : a.charAt(aIndex) - '0';
            carry += bIndex < 0 ? 0 : b.charAt(bIndex) - '0';

            res.append(carry & 1);
            carry >>= 1;

            aIndex--;
            bIndex--;
        }

        if (carry > 0) {
            res.append(carry);
        }

        return res.reverse().toString();
    }


}
