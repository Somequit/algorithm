package template;

/**
 * @author gusixue
 * @description 欧拉筛（线性筛）模板：每个合数只被划掉（最小质因子）一次
 * @date 2023/11/19
 */
public class EulerPrime {
    private static final int TOTAL = (int) 1e6;
    private static final int[] PRIMES = new int[78500];
    private static int PRIMES_COUNT;

    /**
     * 每个数 x，乘以 <= lpf[x] 的质数，lpf[x] 指的是 x 的最小质因子
     * 时间复杂度：O（TOTAL），空间复杂度：O（TOTAL）
     */
    static {
        PRIMES_COUNT = 0;

        boolean[] not_primes = new boolean[TOTAL + 1];
        for (int i = 2; i <= TOTAL; i++) {
            if (!not_primes[i]) {
                PRIMES[PRIMES_COUNT] = i;
                PRIMES_COUNT++;
            }

            // 每个数 x，乘以 <= lpf[x] 的质数
            for (int j = 0; j < PRIMES_COUNT; ++j) {
                int p = PRIMES[j];
                if (i * p > TOTAL) {
                    break;
                }

                not_primes[i * p] = true;

                // p 是 lpf[i]
                if (i % p == 0) {
                    break;
                }
            }
        }
    }

}
