package leetcode.contest.contest_482;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/12/28 10:19 上午
 */
public class Contest_482_2 {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long res = 0;
        if (costBoth >= cost1 + cost2) {
            res = (long) need1 * cost1 + (long) need2 * cost2;

        } else if (costBoth <= cost1 && costBoth <= cost2) {
            res = (long) costBoth * Math.max(need1, need2);

        } else if (costBoth <= cost1) {
            res = (long) costBoth * need1 + (long) cost2 * Math.max(0, need2 - need1);

        } else if (costBoth <= cost2) {
            res = (long) costBoth * need2 + (long) cost1 * Math.max(0, need1 - need2);

        } else {
            int needMin = Math.min(need1, need2);
            res = (long) costBoth * needMin + (long) cost1 * Math.max(0, need1 - needMin) + (long) cost2 * Math.max(0, need2 - needMin);
        }

        return res;
    }
}
