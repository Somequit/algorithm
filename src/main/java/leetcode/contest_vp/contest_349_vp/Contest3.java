package leetcode.contest_vp.contest_349_vp;


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
    private long solution(int[] nums, int x) {
        long res = 0;
        int n = nums.length;
        int[] change = new int[n];
        for (int i = 0; i < n; i++) {
            res += nums[i];
            change[i] = nums[i];
        }

        for (int i = 1; i < n; i++) {
            long resTemp = 0L;

            for (int j = 0; j < n; j++) {
                change[j] = Math.min(change[j], nums[(j - i + n) % n]);
                resTemp += change[j];
            }

            res = Math.min(res, resTemp + (long)i * x);
        }
        return res;
    }


}
