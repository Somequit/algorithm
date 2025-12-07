package leetcode.contest.contest_479;

import java.util.*;


/**
 * @author gusixue
 * @description
 * @date 2025/12/7 10:28 上午
 */
public class Contest_479_2 {

    private static final int TOTAL = (int) 1e6;
    private static final int[] PRIMES = new int[78500];
    private static final boolean[] NOT_PRIMES = new boolean[TOTAL + 1];
    private static int PRIMES_COUNT;

    private static final TreeSet<Integer> PRIMES_TOTAL = new TreeSet<>();

    /**
     * 每个数 x，乘以 <= lpf[x] 的质数，lpf[x] 指的是 x 的最小质因子
     * 时间复杂度：O（TOTAL），空间复杂度：O（TOTAL）
     */
    static {
        PRIMES_COUNT = 0;

        for (int i = 2; i <= TOTAL; i++) {
            if (!NOT_PRIMES[i]) {
                PRIMES[PRIMES_COUNT] = i;
                PRIMES_COUNT++;
            }

            // 每个数 x，乘以 <= lpf[x] 的质数
            for (int j = 0; j < PRIMES_COUNT; ++j) {
                int p = PRIMES[j];
                if (i * p > TOTAL) {
                    break;
                }

                NOT_PRIMES[i * p] = true;

                // p 是 lpf[i]
                if (i % p == 0) {
                    break;
                }
            }
        }

        int curTotal = 0;
        for (int i = 0; curTotal + PRIMES[i] <= TOTAL; i++) {
            curTotal += PRIMES[i];
            if (!NOT_PRIMES[curTotal]) {
                PRIMES_TOTAL.add(curTotal);
            }
        }
    }

    public int largestPrime(int n) {
        if (n == 1) {
            return 0;
        }
//        System.out.println(PRIMES_TOTAL);
        return PRIMES_TOTAL.floor(n);
    }
}
