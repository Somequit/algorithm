package leetcode.brushQuestions.easy;


/**
 * 88. 合并两个有序数组
 */
public class Merge {

    public static void main(String[] args) {
        Merge contest = new Merge();

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
    private void solution(int[] nums1, int m, int[] nums2, int n) {
        // nums1 与 nums2 倒序处理放入 nums1
        int curIndex = m + n - 1;
        m--;
        n--;

        while (n >= 0) {
            if (m < 0) {
                nums1[curIndex] = nums2[n];
                n--;

            } else {
                if (nums1[m] > nums2[n]) {
                    nums1[curIndex] = nums1[m];
                    m--;

                } else {
                    nums1[curIndex] = nums2[n];
                    n--;
                }
            }

            curIndex--;
        }
    }


}
