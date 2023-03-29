package template;

/**
 * @description 二分搜索模板
 * @author gusixue
 * @date 2023/3/26
 */
public class BinarySearch {

    /**
     * 二分搜索：返回非递减数组中小于等于 key 的最大下标
     * 如果均大于 key 返回 -1
     */
    public int binarySearchHigh(long[] nums, int key) {
        int left = 0;
        int right = nums.length - 1;
        int res = -1;

        while (left <= right) {
            // 避免 left+right 越界
            int mid = ((right - left) >> 1) + left;

            if (nums[mid] > key) {
                right = mid - 1;

            } else if (nums[mid] < key) {
                res = mid;
                left = mid + 1;

            } else {
                res = mid;
                left = mid + 1;

            }
        }

        return res;
    }


    /**
     * 二分搜索：返回非递减数组中大于等于 key 的最小下标
     * 如果均小于 key 返回 -1
     */
    public int binarySearchLow(long[] nums, int key) {
        int left = 0;
        int right = nums.length - 1;
        int res = -1;

        while (left <= right) {
            // 避免 left+right 越界
            int mid = ((right - left) >> 1) + left;

            if (nums[mid] > key) {
                res = mid;
                right = mid - 1;

            } else if (nums[mid] < key) {
                left = mid + 1;

            } else {
                res = mid;
                right = mid - 1;

            }
        }

        return res;
    }

}
