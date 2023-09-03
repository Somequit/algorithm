package leetcode.contest.contest_361;


/**
 *
 */
public class Contest1 {

    public static void main(String[] args) {
        Contest1 contest = new Contest1();

        while (true) {
//            int[] nums = AlgorithmUtils.systemInArray();

//            int res = contest.solution(nums);
//            System.out.println(res);
        }

    }

    public int solution(int low, int high) {
        int res = 0;
        for (int i = low; i <= high; i++) {
            if (checkSymmetricIntegers(i)) {
//                System.out.println(i);
                res++;
            }
        }
        return res;
    }

    private boolean checkSymmetricIntegers(int num) {
        String numStr = num + "";
        if(numStr.length() % 2 == 1) {
            return false;
        }

        int left = 0;
        int right = 0;
        for (int i = 0; i < numStr.length() / 2; i++) {
            left += numStr.charAt(i) - '0';
            right += numStr.charAt(numStr.length() - i - 1) - '0';
        }

        return left == right;
    }


}
