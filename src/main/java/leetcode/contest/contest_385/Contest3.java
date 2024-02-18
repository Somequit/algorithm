package leetcode.contest.contest_385;

import java.util.*;

/**
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

//            int res = contest.solution(n, nums, list);
//            System.out.println(res);
        }

    }

    /**
     * @return
     */
    public int mostFrequentPrime(int[][] mat) {
        for (int i = 0; i < 11; i++) {
            NOT_PRIMES[i] = true;
        }

        int m = mat.length;
        int n = mat[0].length;
        int[][] move = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};

        Map<Integer, Integer> primeCountMap = new HashMap<>();
        int maxCount = 0;
        int res = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < move.length; k++) {
                    int cur = mat[i][j];
                    int x = i + move[k][0];
                    int y = j + move[k][1];
                    while (x >= 0 && x < m && y >= 0 && y < n) {
                        cur = cur * 10 + mat[x][y];
                        x = x + move[k][0];
                        y = y + move[k][1];

                        if (!NOT_PRIMES[cur]) {
                            primeCountMap.merge(cur, 1, Integer::sum);
                            if (primeCountMap.get(cur) > maxCount) {
                                maxCount = primeCountMap.get(cur);
                                res = cur;

                            } else if (primeCountMap.get(cur) == maxCount && cur > res) {
                                res = cur;
                            }
                        }
                    }
                }
            }
        }

        return res;
    }

    private static final int TOTAL = (int) 1e6;
    private static final int[] PRIMES = new int[TOTAL];
    private static final boolean[] NOT_PRIMES = new boolean[TOTAL + 1];
    private static int PRIMES_COUNT;

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
    }


}
