package leetcode.brushQuestions.easy;


/**
 * 27. 移除元素
 */
public class RemoveElement {

    public static void main(String[] args) {
        RemoveElement contest = new RemoveElement();

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
    private int solution(int[] nums, int val) {
        int duplicateIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                duplicateIndex++;
                nums[duplicateIndex] = nums[i];
            }
        }

        return duplicateIndex + 1;
    }


}
