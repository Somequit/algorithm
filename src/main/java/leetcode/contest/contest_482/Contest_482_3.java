package leetcode.contest.contest_482;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/12/28 10:19 上午
 */
public class Contest_482_3 {
    public int minAllOneMultiple(int k) {
        Set<Integer> set = new HashSet<>();
        int curMod = 1;
        set.add(curMod);

        int res = 2;
        curMod = (curMod * 10 + 1) % k;
        while (curMod != 0 && !set.contains(curMod)) {
            set.add(curMod);
            curMod = (curMod * 10 + 1) % k;
            res++;
        }

        if (curMod != 0) {
            return -1;

        } else {
            return res;
        }
    }
}
