package leetcode.contest.double_112;

import utils.AlgorithmUtils;

import java.util.List;


/**
 * @author gusixue
 * @date 2023/7/22
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

    private boolean solution(String s1, String s2) {
        int[][] strIndexCount = new int[26][2];

        for (int i = 0; i < s1.length(); i++) {
            strIndexCount[s1.charAt(i) - 'a'][i & 1]++;
        }
//        AlgorithmUtils.systemOutArray(strIndexCount);

        for (int i = 0; i < s2.length(); i++) {
            // 奇偶相同
            if (strIndexCount[s2.charAt(i) - 'a'][i & 1] > 0) {
                strIndexCount[s2.charAt(i) - 'a'][i & 1]--;

            } else {
                return false;
            }
        }

        return true;
    }



}
