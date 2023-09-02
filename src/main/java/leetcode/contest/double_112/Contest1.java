package leetcode.contest.double_112;

import java.util.List;


/**
 * @author gusixue
 * @date 2023/7/22
 */
public class Contest1 {

    public static void main(String[] args) {
        Contest1 contest = new Contest1();

        while (true) {
//            int purchaseAmount = AlgorithmUtils.systemInNumberInt();

//            int res = contest.solution(purchaseAmount);
//            System.out.println(res);
        }

    }

    private boolean solution(String s1, String s2) {
        if ((s1.charAt(0) == s2.charAt(0) && s1.charAt(2) == s2.charAt(2)) ||
                (s1.charAt(0) == s2.charAt(2) && s1.charAt(2) == s2.charAt(0))) {

            if ((s1.charAt(1) == s2.charAt(1) && s1.charAt(3) == s2.charAt(3)) ||
                    (s1.charAt(1) == s2.charAt(3) && s1.charAt(3) == s2.charAt(1))) {
                return true;
            }
        }
        return false;
    }



}
