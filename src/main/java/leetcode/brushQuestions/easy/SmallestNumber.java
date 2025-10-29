package leetcode.brushQuestions.easy;

/**
 * @author gusixue
 * @description 3370. 仅含置位位的最小整数
 * @date 2025/10/29 10:13 上午
 */
public class SmallestNumber {

    public int smallestNumber(int n) {
        return (Integer.highestOneBit(n) << 1) - 1;
    }

}
