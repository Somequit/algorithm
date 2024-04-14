package leetcode.contest.contest_393;

/**
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

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
    public int maximumPrimeDifference(int[] nums) {
        int minIndex = Integer.MAX_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (!not_primes[nums[i]]) {
                if (minIndex == Integer.MAX_VALUE) {
                    minIndex = i;
                    maxIndex = i;

                } else {
                    maxIndex = i;
                }
            }
        }

        return maxIndex - minIndex;
    }

    private static final int TOTAL = 300_000;
    private static final int[] PRIMES = new int[78500];
    private static int PRIMES_COUNT;
    private static final boolean[] not_primes = new boolean[TOTAL + 1];

    /**
     * 每个数 x，乘以 <= lpf[x] 的质数，lpf[x] 指的是 x 的最小质因子
     * 时间复杂度：O（TOTAL），空间复杂度：O（TOTAL）
     */
    static {
        PRIMES_COUNT = 0;
        not_primes[0] = true;
        not_primes[1] = true;
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
