package leetcode.contest.contest_360;

import java.util.List;


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

    public int solution(String moves) {
        int res = 0;

        int pos = 0;
        for (int i = 0; i < moves.length(); i++) {
            if (moves.charAt(i) == 'L' || moves.charAt(i) == '_') {
                pos--;
            } else {
                pos++;
            }
        }
        res = Math.abs(pos);

        pos = 0;
        for (int i = 0; i < moves.length(); i++) {
            if (moves.charAt(i) == 'L') {
                pos--;
            } else {
                pos++;
            }
        }
        res = Math.max(res, Math.abs(pos));
        return res;
    }


}
