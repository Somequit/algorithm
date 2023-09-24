package leetcode.contest.contest_364;


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

    private String solution(String s) {
        int count = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                count++;
            }
        }

        StringBuffer res = new StringBuffer();
        for (int i = 0; i < s.length()-1; i++) {
            if (count > 0) {
                res.append("1");
                count--;

            } else {
                res.append("0");
            }
        }
        return res.append("1").toString();
    }


}
