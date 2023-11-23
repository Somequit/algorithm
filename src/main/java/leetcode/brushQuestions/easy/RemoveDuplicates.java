package leetcode.brushQuestions.easy;


/**
 * 26. 删除有序数组中的重复项
 */
public class RemoveDuplicates {

    public static void main(String[] args) {
        RemoveDuplicates contest = new RemoveDuplicates();

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
    private int solution(int[] nums) {
        int duplicateIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[duplicateIndex]) {
                nums[duplicateIndex + 1] = nums[i];
                duplicateIndex++;
            }
        }

        return duplicateIndex + 1;
    }


}
