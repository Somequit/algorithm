package leetcode.brushQuestions.easy;


import java.math.BigInteger;

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

    public String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        int aLen = a.length();
        int bLen = b.length();

        int carry = 0;
        for (int i = 0; i < Math.max(aLen, bLen); i++) {
            int aVal = 0;
            int bVal = 0;
            if (i < aLen && i < bLen) {
                aVal = a.charAt(aLen - i - 1) - '0';
                bVal = b.charAt(bLen - i - 1) - '0';

            } else if (i < aLen) {
                aVal = a.charAt(aLen - i - 1) - '0';

            } else {
                bVal = b.charAt(bLen - i - 1) - '0';
            }

            res.append(aVal ^ bVal ^ carry);
            carry = (aVal + bVal + carry) / 2;
        }
        if (carry > 0) {
            res.append(carry);
        }

        return res.reverse().toString();
    }

}
