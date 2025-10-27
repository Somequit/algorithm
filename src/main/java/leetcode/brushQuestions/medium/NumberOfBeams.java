package leetcode.brushQuestions.medium;

import java.util.Arrays;

/**
 * @author gusixue
 * @description 2125. 银行中的激光束数量
 * @date 2025/10/27 9:23 上午
 */
public class NumberOfBeams {

    public int numberOfBeams(String[] bank) {
        int res = 0;
        int prevCount = 0;
        for (String bankTemp : bank) {
            int curCount = (int) bankTemp.chars().filter(c -> c == '1').count();
            if (curCount > 0) {
                res += prevCount * curCount;
                prevCount = curCount;
            }
        }

        return res;
    }

}
