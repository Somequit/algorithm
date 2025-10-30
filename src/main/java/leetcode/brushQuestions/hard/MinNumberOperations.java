package leetcode.brushQuestions.hard;

/**
 * @author gusixue
 * @description 1526. 形成目标数组的子数组最少增加次数
 * @date 2025/10/30 5:58 下午
 */
public class MinNumberOperations {

    /**
     * 看做一个先非递减、再非递增的山峰，此子数组最少需要执行峰顶次操作即可，
     * 转化过来就是：当后一个值比前一个值大时，就多执行差分次操作
     */
    public int minNumberOperations(int[] target) {
        int res = 0;
        int prev = 0;

        for(int t : target) {
            if (t > prev) {
                res += t-prev;
            }

            prev = t;
        }

        return res;
    }
}
