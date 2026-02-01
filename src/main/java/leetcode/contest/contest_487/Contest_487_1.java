package leetcode.contest.contest_487;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2026/2/1 10:22 上午
 */
public class Contest_487_1 {
    public int countMonobit(int n) {
        int bitNum = 1;
        int res = 1;
        while (bitNum <= n) {
            res++;
            bitNum = (bitNum << 1) + 1;
        }
        return res;
    }
}
