package leetcode;

import utils.AlgorithmUtils;

/**
 * @author gusixue
 * @description
 * @date 2023/6/18
 */
public class LeetcodeTest {

    public static void main(String[] args) {
        LeetcodeTest leetcodeTest = new LeetcodeTest();
        while (true) {
            String[] strs = AlgorithmUtils.systemInArrayString();
            String res = leetcodeTest.solution(strs);
            System.out.println(res);
        }
    }

    private String solution(String[] strs) {
        StringBuffer res = new StringBuffer("");

        int minLen = strs[0].length();
        for (String str : strs) {
            minLen = Math.min(minLen, str.length());
        }

        for (int i = 0; i < minLen; i++) {
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].charAt(i) != strs[j - 1].charAt(i)) {
                    return res.toString();
                }
            }
            res.append(strs[0].charAt(i));
        }

        return res.toString();
    }
}
