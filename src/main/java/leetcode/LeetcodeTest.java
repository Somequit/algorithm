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

        StringBuffer stringBuffer = new StringBuffer("12345");
        stringBuffer.insert(3, strs[0]);

        return stringBuffer.toString();
    }
}
