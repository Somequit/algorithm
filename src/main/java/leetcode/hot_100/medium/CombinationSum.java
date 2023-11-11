package leetcode.hot_100.medium;

import utils.AlgorithmUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gusixue
 * @description
 * 39. 组合总和
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 * 1 <= candidates.length <= 30
 * 2 <= candidates[i] <= 40
 * candidates 的所有元素 互不相同
 * 1 <= target <= 40
 * @date 2023/6/13
 */
public class CombinationSum {
    public static void main(String[] args) {
        CombinationSum combinationSum = new CombinationSum();

        while (true) {
            int[] candidates = AlgorithmUtils.systemInArray();
            int target = AlgorithmUtils.systemInNumberInt();
            List<List<Integer>> res = combinationSum.solution(candidates, target);
            System.out.println(res);
        }
    }

    /**
     * 完全背包：标准完全背包问题，只是存储需要使用 List 数组，
     * 定义状态（子问题）：定义 dp[i][j] 为前 i 个元素能组成 j 的所有组合
     * 状态转移方程：dp[i][j] = add dp[i-1][j]（不添加），add dp[i-1][j-candidates[i]]（上一轮后添加 i 元素），add dp[i][j-candidates[i]]（本轮可添加多个 i 元素）
     * 空间压缩：由于 i 仅与 i-1 相关，因此可以仅使用 dp[j]，循环 i 即可
     * 注意：由于每个元素均不相同、所以组合时不需要去重，需要 List<List> 双重集合、必须深拷贝才能避免添加元素相互影响
     * 定义 candidates.length 为 n，还需要循环组合数，时间复杂度：O（n*target*组合数（< 150）），空间复杂度：O（n*target*组合数（< 150））
     */
    private List<List<Integer>> solution(int[] candidates, int target) {
        // 判空
        if (candidates == null || candidates.length <= 0 || target < 0) {
            return null;
        }

        // 定义状态 + 空间压缩
        List[] dp = new ArrayList[target + 1];

        // 初始化
        int dpLen = dp.length;
        for (int i = 0; i < dpLen; i++) {
            // 组合数少于 150 个，避免扩容
            dp[i] = new ArrayList<List<Integer>>(149);
        }

        // 位置 0 存一个空集合代表可以从 0 开始
        int candidatesLen = candidates.length;
        dp[0].add(new ArrayList<>());

        // 转移方程循环执行
        for (int i = 0; i < candidatesLen; i++) {
            // 顺序执行即可使状态压缩前的 dp[i] 与 dp[i-1] 一起添加、此时仅需要计算一次即可
            for (int j = candidates[i]; j < dpLen; j++) {
                // 需要放入的组合，dp 数组已经初始化过
                List<List<Integer>> candidatePreLists = dp[j - candidates[i]];
                // 当前添加的组合
                List<List<Integer>> candidateCurLists = dp[j];

//                System.out.println("pre " + i + " " + j + ":" + candidatePreLists + ":" + candidateCurLists);
                // j-candidates[i] 非空代表可从其转化而来，否则不可加入
                if (candidatePreLists.size() > 0) {

                    // 组合数少于 150 个，避免扩容
                    List<List<Integer>> candidatePreTmpLists = new ArrayList<>(149);

                    // 复制所有前一轮的集合
                    for (List<Integer> candidatePreList : candidatePreLists) {
                        // 最多放入 candidatesLen 个元素、避免扩容
                        List<Integer> candidatePreTmpList = new ArrayList<>(candidatesLen);
                        candidatePreTmpList.addAll(candidatePreList);
                        candidatePreTmpLists.add(candidatePreTmpList);
                    }

                    // 循环组合数
                    for (List<Integer> candidatePreTmpList : candidatePreTmpLists) {
                        candidatePreTmpList.add(candidates[i]);
                    }

                    candidateCurLists.addAll(candidatePreTmpLists);
                }

//                System.out.println(i + " " + j + ":" + candidatePreLists + ":" + candidateCurLists);
            }
        }

        // 返回结果
        return dp[target];
    }
}
