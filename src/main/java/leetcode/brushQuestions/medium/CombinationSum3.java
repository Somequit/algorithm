package leetcode.brushQuestions.medium;

import java.util.*;

/**
 * @author gusixue
 * @description 216. 组合总和 III
 * @date 2024/4/21
 */
public class CombinationSum3 {

    /**
     * 模拟 01背包 打 [1,45] 的表即可，大于 45 结果为空（[1,9] 和为 45）
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        return dpCombination[n][k];
    }

    private static final List<List<Integer>>[][] dpCombination;

    static {
        // i 个数和为 j 需要选择 k 个数，空间压缩掉 i
        dpCombination = new ArrayList[61][10];
        // 初始化均不选
        Arrays.stream(dpCombination).forEach(arr -> {
            Arrays.setAll(arr, list -> new ArrayList<>());
        });
        dpCombination[0][0].add(new ArrayList<>());

        for (int i = 1; i < 10; i++) {
            for (int j = 45; j >= i; j--) {

                for (int k = 1; k < 10; k++) {
                    // 不选就是自己，可以选就 add
                    List<List<Integer>> listListTemp = dpCombination[j - i][k - 1];
                    for (List<Integer> listTemp : listListTemp) {
                        List<Integer> listCopy = new ArrayList<>(listTemp);
                        listCopy.add(i);

                        dpCombination[j][k].add(listCopy);
                    }

                }

            }
        }

    }
}
