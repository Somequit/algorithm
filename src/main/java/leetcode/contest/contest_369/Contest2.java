package leetcode.contest.contest_369;


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
    private long solution(int[] nums1, int[] nums2) {
        long total1 = 0L;
        int count1 = 0;
        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] == 0) {
                count1++;
            } else {
                total1 += nums1[i];
            }
        }

        long total2 = 0L;
        int count2 = 0;
        for (int i = 0; i < nums2.length; i++) {
            if (nums2[i] == 0) {
                count2++;
            } else {
                total2 += nums2[i];
            }
        }

        if (total1 > total2) {
            long temp = total1;
            total1 = total2;
            total2 = temp;

            temp = count1;
            count1 = count2;
            count2 = (int)temp;
        }

        if (total1 == total2) {
            if ((count1 == 0 && count2 != 0) || (count2 == 0 && count1 != 0)) {
                return -1;

            } else {
                return Math.max(total1 + count1, total2 + count2);
            }

        } else {
            if (count1 == 0 || (count2 == 0 && total1 + count1 > total2)) {
                return -1;

            } else {
                return Math.max(total1 + count1, total2 + count2);
            }

        }
    }


}
