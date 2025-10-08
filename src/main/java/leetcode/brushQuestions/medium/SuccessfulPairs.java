package leetcode.brushQuestions.medium;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * @author gusixue
 * @description 2300. 咒语和药水的成功对数
 * @date 2025/10/8 12:06 下午
 */
public class SuccessfulPairs {

    /**
     * 排序 + 二分/TreeMap
     */
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;
        Arrays.sort(potions);
        // 大于等于强度的个数
        TreeMap<Long, Integer> potionsNumTreeMap = new TreeMap<>();
        for (int i = 0; i < potions.length; i++) {
            potionsNumTreeMap.putIfAbsent((long)potions[i], potions.length - i);
        }

        int[] pairs = new int[n];
        for (int i = 0; i < n; i++) {
            long needPotions = success / spells[i];
            if (needPotions * spells[i] < success) {
                needPotions++;
            }
            if (potionsNumTreeMap.ceilingKey(needPotions) != null) {
                pairs[i] = potionsNumTreeMap.getOrDefault(potionsNumTreeMap.ceilingKey(needPotions), 0);
            }
        }

        return pairs;
    }
}
