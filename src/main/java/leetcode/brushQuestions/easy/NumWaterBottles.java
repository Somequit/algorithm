package leetcode.brushQuestions.easy;

/**
 * @author gusixue
 * @description 1518. 换水问题
 * @date 2025/10/1 11:57 上午
 */
public class NumWaterBottles {

    public int numWaterBottles(int numBottles, int numExchange) {
        int res = numBottles;
        int numEmpty = numBottles;

        while (numEmpty >= numExchange) {
            res += numEmpty / numExchange;
            numEmpty = numEmpty / numExchange + numEmpty % numExchange;
        }

        return res;
    }
}
