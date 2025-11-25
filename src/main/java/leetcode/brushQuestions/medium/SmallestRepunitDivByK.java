package leetcode.brushQuestions.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * @author gusixue
 * @description 1015. 可被 K 整除的最小整数
 * @date 2025/11/25 11:46 上午
 */
public class SmallestRepunitDivByK {

    public int smallestRepunitDivByK(int k) {
        Set<Integer> setRem = new HashSet<>();
        int curNum = 1;
        curNum %= k;
        int res = 1;
        while (curNum != 0 && !setRem.contains(curNum)) {
            setRem.add(curNum);
            curNum = curNum * 10 + 1;
            curNum %= k;
            res++;
        }
        return curNum > 0 ? -1 : res;
    }
}
