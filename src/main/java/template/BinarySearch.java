package template;

/**
 * todo:二分搜索模板
 * @author gusixue
 * @date 2023/3/26
 */
public class BinarySearch {

    /**
     * nums 数组中小于等于 query 的最大下标
     * 如果均大于 query 返回 0
     */
    public int binarySearchMin(long[] nums, int key) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            // 避免 left + right 越界
            int mid = ((right + 1 - left) >> 1) + left;

            System.out.println(left + ":" + right + ":" + mid + ":" + key);

            if (nums[mid] > key) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }

}
