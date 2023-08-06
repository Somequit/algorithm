package leetcode.contest.contest_357;

import utils.AlgorithmUtils;


/**
 *
 */
public class Contest1 {

    public static void main(String[] args) {
        Contest1 contest = new Contest1();

        while (true) {
            String s = AlgorithmUtils.systemInString();

            String res = contest.solution(s);
            System.out.println(res);
        }

    }

    public String solution(String s) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != 'i'){
                stringBuffer.append(s.charAt(i));
            } else {
                stringBuffer = stringBuffer.reverse();
            }
        }
        return stringBuffer.toString();
    }


}
