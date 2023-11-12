package leetcode.contest.contest_371;


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
    private int solution(int[] nums1, int[] nums2) {
        int n = nums1.length;

        int num1Max = nums1[n - 1];
        int num2Max = nums2[n - 1];
        int res = doMinOperation(num1Max, num2Max, nums1, nums2, n);

        num1Max = nums2[n - 1];
        num2Max = nums1[n - 1];
        res = Math.min(res, doMinOperation(num1Max, num2Max, nums1, nums2, n) + 1);

        return res >= Integer.MAX_VALUE - 1 ? -1 : res;
    }

    private int doMinOperation(int num1Max, int num2Max, int[] nums1, int[] nums2, int n) {
        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            if (num1Max < nums1[i] && num1Max < nums2[i]) {
                return Integer.MAX_VALUE - 1;
            }
            if (num2Max < nums1[i] && num2Max < nums2[i]) {
                return Integer.MAX_VALUE - 1;
            }

            if (num1Max >= nums1[i] && num2Max >= nums2[i]) {

            } else if (num1Max >= nums2[i] && num2Max >= nums1[i]) {
                res++;

            } else {
                return Integer.MAX_VALUE - 1;
            }

        }
        return res;
    }


}
