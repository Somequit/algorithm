package leetcode.brushQuestions.medium;

/**
 * @author gusixue
 * @description 2079. 给植物浇水
 * @date 2024/5/8
 */
public class WateringPlants {

    /**
     * 一次遍历
     */
    public int wateringPlants(int[] plants, int capacity) {
        int res = plants.length;
        int curCapacity = capacity;

        for (int i = 0; i < plants.length; i++) {
            if (curCapacity >= plants[i]) {
                curCapacity -= plants[i];

            } else {
                res += i * 2;
                curCapacity = capacity - plants[i];
            }
        }

        return res;
    }
}
