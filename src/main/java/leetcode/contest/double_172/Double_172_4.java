package leetcode.contest.double_172;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/12/20 10:27 下午
 */
public class Double_172_4 {
    public long lastInteger(long n) {
        if (n <= 2) {
            return 1;
        }

        long start = 1;
        long skip = 1;
        while (n > 1) {
            skip *= 2;
            n -= n / 2;

            if (n == 1) {
                break;
            }

//            System.out.println(n);
            if (n % 2 == 0) {
                start += skip;
            }
            skip *= 2;
            n -= n / 2;

        }

        return start;
    }
}
