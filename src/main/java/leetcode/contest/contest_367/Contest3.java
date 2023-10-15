package leetcode.contest.contest_367;


/**
 * @author gusixue
 * @date 2023/10/08
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
//            int purchaseAmount = AlgorithmUtils.systemInNumberInt();

//            int res = contest.solution(purchaseAmount);
//            System.out.println(res);
        }

    }

    /**
     * @return
     */
    private int[] solution(int[] nums, int indexDifference, int valueDifference) {
        int minValueIndex = 0;
        int maxValueIndex = 0;

        int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;
        for (int i = indexDifference; i < nums.length; i++) {
            if (nums[minValueIndex] > nums[i - indexDifference]) {
                minValueIndex = i - indexDifference;
            }
            if (nums[maxValueIndex] < nums[i - indexDifference]) {
                maxValueIndex = i - indexDifference;
            }

            if (Math.abs(nums[i] - nums[minValueIndex]) >= valueDifference) {
                res[0] = minValueIndex;
                res[1] = i;
                break;
            }
            if (Math.abs(nums[i] - nums[maxValueIndex]) >= valueDifference) {
                res[0] = maxValueIndex;
                res[1] = i;
                break;
            }
        }

        return res;
    }


}
