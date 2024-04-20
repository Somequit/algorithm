package leetcode.brushQuestions.medium;

import java.util.*;

/**
 * @author gusixue
 * @description 39. 组合总和
 * @date 2024/4/20
 */
public class CombinationSum {

    /**
     * 完全背包，dpCombination[i][j] 代表前 i 位和为 j 时有哪些组合
     * 空间优化：第一维可以优化掉，每次循环复用上次的结果
     * 去重：由于candidates 的所有元素 互不相同，所以不会重复
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>>[] dpCombination = new ArrayList[target + 1];
        Arrays.setAll(dpCombination, i -> new ArrayList<>());

        // 初始化
        dpCombination[0].add(new ArrayList<>());
        // 完全背包
        for (int candidate : candidates) {
            for (int j = candidate; j < target + 1; j++) {

                // 注意使用 addAll 不会创建 List 的副本，会只想原有的 List
                for (int l = 0; l < dpCombination[j - candidate].size(); l++) {
                    List<Integer> list = dpCombination[j-candidate].get(l);

                    List<Integer> listTemp = new ArrayList<>(list);
                    listTemp.add(candidate);
                    dpCombination[j].add(listTemp);
                }
            }
        }

        return dpCombination[target];
    }
}
