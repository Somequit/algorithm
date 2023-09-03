package leetcode.contest.contest_361;


/**
 *
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
//            int[] nums = AlgorithmUtils.systemInArray();

//            int res = contest.solution(nums);
//            System.out.println(res);
        }

    }

    public int solution(String num) {
        int res = num.length();

        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) == '0') {
                res--;
                break;
            }
        }

        // 00 50 25 75
        res = Math.min(res, checkMinimumOperations(num, '0', '0'));
        res = Math.min(res, checkMinimumOperations(num, '2', '5'));
        res = Math.min(res, checkMinimumOperations(num, '5', '0'));
        res = Math.min(res, checkMinimumOperations(num, '7', '5'));

        return res;
    }

    private int checkMinimumOperations(String num, char left, char right) {
        int n = num.length();
        int res = num.length();

        boolean rightFlag = false;
        for (int i = n - 1; i >= 0; i--) {

            if (rightFlag && num.charAt(i) == left) {
                res = n - i - 2;
                break;

            } if (num.charAt(i) == right) {
                rightFlag = true;
            }
        }
        // System.out.println(res);
        return res;
    }


}
