package leetcode.brushQuestions.easy;


/**
 * 35. 搜索插入位置
 */
public class SearchInsert {

    public static void main(String[] args) {
        SearchInsert contest = new SearchInsert();

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
    private int solution(int[] nums, int target) {
        // 大于等于 target 的最小下标
        int left = 0;
        int right = nums.length - 1;
        int res = nums.length;
        while (left <= right) {
            int mid = ((right - left) >> 1) + left;

            if (nums[mid] == target) {
                res = mid;
                break;

            } else if (nums[mid] > target) {
                res = mid;
                right = mid - 1;

            } else  {
                left = mid + 1;
            }
        }
        return res;
    }


}
