package leetcode.contest_vp.double_106_vp;


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
    private int solution(int[] nums, String s, int d) {
        int mod = 1_000_000_007;
        int n = nums.length;

        long[] newNums = new long[n];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'L') {
                newNums[i] = (long)nums[i] - d;

            } else {
                newNums[i] = (long)nums[i] + d;
            }
        }

        Arrays.sort(newNums);
        long res = 0L;
        for (int i = 1; i < n; i++) {
            res += (newNums[i] - newNums[i - 1]) % mod * i % mod * (n - i) % mod;
        }

        return (int) (res % mod);
    }


}
