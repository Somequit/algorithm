package leetcode.brushQuestions.medium;

import utils.AlgorithmUtils;

import java.util.*;

/**
 * @author gusixue
 * @description
 * 40. 组合总和 II
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 * 注意：解集不能包含重复的组合。
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 * @date 2023/9/27
 */
public class CombinationSum2 {

    public static void main(String[] args) {
        CombinationSum2 combinationSum2 = new CombinationSum2();
        while (true) {
            int[] candidates = AlgorithmUtils.systemInArray();
            int target = AlgorithmUtils.systemInNumberInt();

            List<List<Integer>> res = combinationSum2.solution(candidates, target);
            System.out.println(res);
        }
    }

    /**
     * dp 存储 [1,target] 可以使用哪些下标，然后 dfs 根据下标遍历所有可能性
     * 去重：首先排序使相同元素放在一起，dp 存储时、如果相同的数已经存在则更新到新的下标，因为现在的下标更靠后、能构成更多的可能
     * n 为 candidates.length，m 为 target，k 为结果集，时间复杂度：O（n*n*m+k），空间复杂度：O（n*n*m+k）
     */
    private List<List<Integer>> solution(int[] candidates, int target) {
        Arrays.sort(candidates);
//        System.out.println(Arrays.toString(candidates));

        int len = candidates.length;
        // 存储前 i 个元素和为 j 时，可以使用哪些非重复 candidates[i] 的下标 i，使得可以减到零
        List<Integer>[][] knapsackIndexList = new ArrayList[len][target + 1];
        // 初始化如果可以使用第一个元素，则填入下标 0
        for (int i = 0; i < target + 1; i++) {
            knapsackIndexList[0][i] = new ArrayList<>();
            if (i == candidates[0]) {
                knapsackIndexList[0][i].add(0);
            }
        }

        for (int i = 1; i < len; i++) {
            for (int j = target; j >= 0; j--) {
                // 继承上一层所有可移动的元素
                knapsackIndexList[i][j] = new ArrayList<>();
                knapsackIndexList[i][j].addAll(knapsackIndexList[i - 1][j]);

                // 如果 减去candidates[i]后它存在值使得减到零 或者 减 candidates[i] 为零，则可以使用 candidates[i]，同时之前的值不存在 candidates[i] 则添加下标 i
                if ((j > candidates[i] && knapsackIndexList[i - 1][j - candidates[i]].size() > 0) || j == candidates[i]) {

                    // 查找是否有相同的数
                    int indexTemp = -1;
                    for (int k = 0; k < knapsackIndexList[i - 1][j].size(); k++) {
                        if (candidates[knapsackIndexList[i - 1][j].get(k)] == candidates[i]) {
                            indexTemp = k;
                            break;
                        }
                    }
                    // 如果相同的数已经存在则更新到新的下标
                    if (indexTemp > -1) {
                        knapsackIndexList[i][j].set(indexTemp, i);

                    } else {
                        knapsackIndexList[i][j].add(i);

                    }
                }
            }
        }

//        AlgorithmUtils.systemOutArrayObject(knapsackIndexList);

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> listSingle = new ArrayList<>();
        // 递归获取所有可能性，此时不需要去重
        dfs(res, candidates, knapsackIndexList, len - 1, target, listSingle);

        return res;
    }

    private void dfs(List<List<Integer>> res, int[] candidates, List<Integer>[][] knapsackIndexList, int index, int curTarget, List<Integer> listSingle) {
        // 如果减到零就将结果放入 res
        if (curTarget == 0) {
            List<Integer> listTemp = new ArrayList<>(listSingle);
            res.add(listTemp);
            return;
        }
        if (index < 0) {
            return;
        }

        List<Integer> prevList = knapsackIndexList[index][curTarget];
        for (Integer prev : prevList) {
            listSingle.add(candidates[prev]);
            // 由于需要减去 prev，因此移动到 prev-1
            dfs(res, candidates, knapsackIndexList, prev - 1, curTarget - candidates[prev], listSingle);
            listSingle.remove(listSingle.size() - 1);
        }
    }
}
