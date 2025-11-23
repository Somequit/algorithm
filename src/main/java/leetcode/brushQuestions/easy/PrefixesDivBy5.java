package leetcode.brushQuestions.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gusixue
 * @description 1018. 可被 5 整除的二进制前缀
 * @date 2025/11/24 7:53 上午
 */
public class PrefixesDivBy5 {

    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> res = new ArrayList<>();
        int curNum = 0;
        for (int num : nums) {
            curNum = curNum * 2 + num;
            curNum %= 5;
            if (curNum == 0) {
                res.add(true);

            } else {
                res.add(false);
            }
        }

        return res;
    }
}
