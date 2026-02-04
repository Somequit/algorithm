package leetcode.brushQuestions.hard;

/**
 * @author gusixue
 * @description 3640. 三段式数组 II
 * @date 2026/2/4 6:10 下午
 */
public class MaxSumTrionic {

    /**
     * 直接找每个极大三段式（即不能左右扩展），减的一段全加入结果，前面增的一段从后往前加到刚好负数（最少加一个），后面增的一段要么不加要么全加
     */
    public long maxSumTrionic(int[] nums) {
        int n = nums.length;
        long res = Long.MIN_VALUE;
        long curTrionic = 0;
        int curP = 0;
        int curQ = 0;
        for (int left = 0, right = 1; right < n; ) {

            if (nums[left] >= nums[right]) {
                left = right;
                right++;
                continue;
            }

            curTrionic = 0;
            int cntSeg = 1;
            for (; right < n; right++) {
                if (nums[right - 1] == nums[right]) {
                    break;

                    // 增
                } else if (nums[right - 1] < nums[right]) {
                    if (cntSeg == 2) {
                        curQ = right - 1;
                        cntSeg++;
                    }

                    // 减
                } else {
                    if (cntSeg == 1) {
                        curP = right - 1;
                        cntSeg++;
                    } else if (cntSeg == 3) {
                        break;
                    }
                }
            }

            if (cntSeg != 3) {
                left = right;
                right++;
                continue;
            }

            right--;
            for (int i = curP - 1; i < curQ + 2; i++) {
                curTrionic += nums[i];
            }
            for (int i = curP - 2; i >= left; i--) {
                if (nums[i] <= 0) {
                    break;
                }
                curTrionic += nums[i];
            }
            long tmp = 0;
            for (int i = curQ + 2; i <= right; i++) {
                tmp += nums[i];
            }
            if (tmp > 0) {
                curTrionic += tmp;
            }
            res = Math.max(res, curTrionic);

            left = curQ;
            right = left + 1;

        }

        return res;
    }

}
