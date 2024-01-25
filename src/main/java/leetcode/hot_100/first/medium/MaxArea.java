package leetcode.hot_100.first.medium;

import static java.lang.Math.max;

/**
 * 11. 盛最多水的容器
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 */
public class MaxArea {

    public static void main(String[] args) {
        unitTest();
//        while(true) {
//            int[] height = AlgorithmUtils.systemInArray();
//            int result = solution(height);
//            System.out.println(result);
//        }
    }

    private static void unitTest() {
        assert solution(new int[]{1,8,6,2,5,4,8,3,7}) == 48;
        System.out.println();

        assert solution(new int[]{5,5,4,6,7,9,8,10,5,1}) == 40;
        System.out.println();

        assert solution(new int[]{5}) == 0;
        System.out.println();


        assert solution2(new int[]{1,8,6,2,5,4,8,3,7}) == 49;
        System.out.println();

        assert solution2(new int[]{5,5,4,6,7,9,8,10,5,1}) == 40;
        System.out.println();

        assert solution2(new int[]{5}) == 0;
        System.out.println();
    }

    /**
     * 贪心算法-双指针
     * 从左往右遍历左端点，找到最右边的不小于次端点的右端点即可，结果：左端点高度 * 左右端点距离
     * 易得后一个左端点如果不比前一个高，则不影响结果，因此左端点遍历中增加，右端点则可不回溯的从最右往左遍历
     * 然后从右往左遍历右端点，方法与上方描述类似
     * 时间复杂度：O(n),空间复杂度：O(1)
     */
    private static int solution(int[] height) {
        int result = 0;

        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            while (left < right && height[left] > height[right]) {
                right--;
            }
            result = max(result, (right - left) * height[left]);
            left++;
            while (left < right && height[left] <= height[left - 1]) {
                left++;
            }
        }

        left = 0;
        right = height.length - 1;
        while (left < right) {
            while (left < right && height[right] > height[left]) {
                left++;
            }
            result = max(result, (right - left) * height[right]);
            right--;
            while (left < right && height[right] <= height[right + 1]) {
                right--;
            }
        }
        return result;
    }

    /**
     * 贪心算法-双指针
     * 双指针以左右边缘为容器计算结果，然后将比较短的边缘向中间移动（相等均移动），然后形成新容器继续计算，直到相互触碰
     * 证明：
     * 假设左右边缘为i、j，则装水量为：(j - i) * min (height[i], height[j])
     * 由于移动后（j - i）一定会变小，那么需要min (height[i], height[j]) 变大才能让结果变大，因此一定要移动比较矮的(相等则同时移动)
     * 时间复杂度：O(n),空间复杂度：O(1)
     */
    private static int solution2(int[] height) {
        int result = 0;

        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            if (height[left] > height[right]) {
                result = max(result, (right - left) * height[right]);
                right--;
            } else if(height[left] < height[right]) {
                result = max(result, (right - left) * height[left]);
                left++;
            } else {
                result = max(result, (right - left) * height[left]);
                left++;
                right--;
            }
        }

        return result;
    }
}
