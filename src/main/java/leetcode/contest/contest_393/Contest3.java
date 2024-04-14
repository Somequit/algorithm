package leetcode.contest.contest_393;

import java.util.Arrays;

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
    public long findKthSmallest(int[] coins, int k) {
        Arrays.sort(coins);
        if (coins[0] == 1) {
            return k;
        }

//        Arrays.stream(LCM).forEach(arr -> System.out.println(Arrays.toString(arr)));

        long left = 1;
        long right = (long) coins[0] * k;
        long res = 1;
        while (left <= right) {
            long mid = (right - left) / 2 + left;

            // 前面有多少个
            long count = doFindKthSmallest(mid, coins);
//            System.out.println(count);

            if (count < k) {
                left = mid + 1;

            } else if (count > k) {
                right = mid - 1;

            } else {
                res = mid;
                right = mid - 1;
            }

        }
//        System.out.println();

        return res;
    }

    private static final int[][] GCD = new int[26][26];
    static {
        for (int i = 0; i < 26; i++) {
            GCD[i][0] = 1;
            GCD[0][i] = 1;
        }
        for (int i = 1; i < 26; i++) {
            for (int j = 1; j < 26; j++) {
                GCD[i][j] = (int)gcd(i, j);
            }
        }
    }
    private static long gcd(long a,long b){
        return (a == 0 ? b : gcd(b % a, a));
    }

    private long doFindKthSmallest(long mid, int[] coins) {
        long[] res = new long[]{0};

        // + mid/lcm1 - mid/lcm2 + mid/lcm3...
        dfs(mid, 0, coins, 1L, 0, 0, res);

        return res[0];
    }

    private void dfs(long mid, int index, int[] coins, long lcm, int gcd, long count, long[] res) {
        if (index == coins.length) {
            if (count == 0) {
                return;
            }

//            if (mid / lcm > 0) {
//                System.out.println(count + " : " + mid + " : " + lcm);
//            }

            if ((count & 1) == 0) {
                res[0] -= mid / lcm;
            } else {
                res[0] += mid / lcm;
            }
            return;
        }

        // 选
        int gcdTemp = gcd;
        if (gcd == 0) {
            gcd = coins[index];

        } else {
            gcd = GCD[gcd][coins[index]];
        }
        dfs(mid, index + 1, coins, lcm * coins[index] / gcd(lcm, coins[index]), gcd, count + 1, res);
        gcd = gcdTemp;

        // 不选
        dfs(mid, index + 1, coins, lcm, gcd, count, res);

    }


}
