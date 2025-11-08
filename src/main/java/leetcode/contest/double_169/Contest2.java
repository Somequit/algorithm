package leetcode.contest.double_169;

/**
 * Q2. 统计主要元素子数组数目 I
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

    public int countMajoritySubarrays(int[] nums, int target) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int targetCnt = 0;
            for (int j = i; j < nums.length; j++) {
                targetCnt += nums[j] == target ? 1 : 0;

                res += (targetCnt > (j - i + 1) / 2) ? 1 : 0;
            }
        }

        return res;
    }


}
