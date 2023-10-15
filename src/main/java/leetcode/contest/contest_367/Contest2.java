package leetcode.contest.contest_367;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author gusixue
 * @date 2023/10/08
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
//            int purchaseAmount = AlgorithmUtils.systemInNumberInt();

//            int res = contest.solution(purchaseAmount);
//            System.out.println(res);
        }

    }

    /**
     * @return
     */
    private String solution(String s, int k) {
        List<String> resList = new ArrayList<>();
        int len = s.length();


        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {

            if (s.charAt(i) == '1') {
                int countOne = 0;
                for (int j = i; j < len; j++) {
                    if (s.charAt(j) == '1') {
                        countOne++;

                        if (countOne == k) {
                            minLen = Math.min(minLen, j - i + 1);

                        } else if (countOne > k) {
                            break;
                        }
                    }
                }
            }
        }
        if (minLen == Integer.MAX_VALUE) {
            return "";
        }

        for (int i = 0; i < len; i++) {

            if (s.charAt(i) == '1') {
                int countOne = 0;
                StringBuffer stringBuffer = new StringBuffer();
                for (int j = i; j < len; j++) {
                    stringBuffer.append(s.charAt(j));

                    if (s.charAt(j) == '1') {
                        countOne++;

                        if (countOne == k && j - i + 1 == minLen) {
                            resList.add(stringBuffer.toString());

                        } else if (countOne > k) {
                            break;
                        }
                    }
                }
            }
        }
        Collections.sort(resList);


        return resList.get(0);
    }


}
