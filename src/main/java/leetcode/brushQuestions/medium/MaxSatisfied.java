package leetcode.brushQuestions.medium;

/**
 * @author gusixue
 * @description 1052. 爱生气的书店老板
 * @date 2024/4/23
 */
public class MaxSatisfied {

    /**
     * 先 [0, minutes] 好脾气的值，然后使用划窗即可
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length;

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (i < minutes || grumpy[i] == 0) {
                res += customers[i];
            }
        }

        int curVal = res;
        for (int i = minutes; i < n; i++) {
            if (grumpy[i - minutes] == 1) {
                curVal -= customers[i - minutes];

            }
            if (grumpy[i] == 1) {
                curVal += customers[i];
            }

            res = Math.max(res, curVal);
        }

        return res;
    }
}
