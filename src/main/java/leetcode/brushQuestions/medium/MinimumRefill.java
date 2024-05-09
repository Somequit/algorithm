package leetcode.brushQuestions.medium;

/**
 * @author gusixue
 * @description 2105. 给植物浇水 II
 * @date 2024/5/9
 */
public class MinimumRefill {

    /**
     * 一次遍历，双指针解法，两边同时浇水，不够就将水壶灌满
     */
    public int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int res = 0;

        int curA = capacityA;
        int curB = capacityB;
        for (int left = 0, right = plants.length - 1; left <= right; left++, right--) {
            if (left == right) {
                if (Math.max(curA, curB) < plants[left]) {
                    res++;
                }

            } else {
                if (curA >= plants[left]) {
                    curA -= plants[left];

                } else {
                    res++;
                    curA = capacityA - plants[left];
                }

                if (curB >= plants[right]) {
                    curB -= plants[right];

                } else {
                    res++;
                    curB = capacityB - plants[right];
                }
            }

        }

        return res;
    }
}
