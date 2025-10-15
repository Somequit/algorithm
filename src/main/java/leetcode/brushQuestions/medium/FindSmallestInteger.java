package leetcode.brushQuestions.medium;

/**
 * @author gusixue
 * @description 2598. 执行操作后的最大 MEX
 * @date 2025/10/16 5:53 上午
 */
public class FindSmallestInteger {

    /**
     * 首先每个 nums[i] 无论正负均可 +/-多次value 成 [0,value) 的值，此后再通过 +x*value 顺序转化为自然数即可，
     * 其次转化后值的个数，决定了哪个自然数先不出现，
     *     如果 [0,value) 某个数先未出现那结果就是它，
     *     如果每个数均出现过，则最少的值中最小的值就会被先用完，例如：value=4 且（0-3次，1-2次，2-2次，3-3次） 则 1+2*4=9 就首先因为 1用了两次而用完导致不够
     */
    public int findSmallestInteger(int[] nums, int value) {
        int[] count = new int[value];

        for (int num : nums) {
            if (num >= 0 && num < value) {
                count[num]++;

            } else {
                // 无论正负均可 +/-多次value 成 [0,value) 的值
                int tmp = (num % value + value) % value;
                count[tmp]++;
            }
        }

        int minCountIndex = 0;
        for (int i = 1; i < value; i++) {
            if (count[i] < count[minCountIndex]) {
                minCountIndex = i;
            }
        }

        return minCountIndex + count[minCountIndex] * value;
    }
}
