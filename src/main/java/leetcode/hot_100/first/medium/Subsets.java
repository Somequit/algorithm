package leetcode.hot_100.first.medium;

import utils.AlgorithmUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gusixue
 * @description
 * 78. 子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 * @date 2023/5/28
 */
public class Subsets {

    public static void main(String[] args) {
        Subsets subsets = new Subsets();
        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
            List<List<Integer>> subSets = solution(nums);
            System.out.println(subSets);
        }
    }

    /**
     * DFS：简单的全排列，使用 DFS 设置每个元素出现与不出现两种情况
     * 由于 nums 中的所有元素 互不相同，因此不需要判断重复
     * 元素个数总共 2^n 个，初始化时加入个数避免扩容
     * 时间复杂度：O（2^n*n），空间复杂度：O（n）栈和临时数组均为 n
     */
    private static List<List<Integer>> solution(int[] nums) {
        // 判空，返回一个含有空集合的结果
        if (nums == null || nums.length <= 0) {
            List<List<Integer>> subSets = new ArrayList<>();
            List<Integer> subSet = new ArrayList<>();
            subSets.add(subSet);
            return subSets;
        }
        // 元素总共 2^n 个，避免扩容
        int len = nums.length;
        List<List<Integer>> subSets = new ArrayList<>(1 << len);
        // DFS 实现全排列算法
        List<Integer> permutationList = new ArrayList<>();
        recursionPermutations(nums, 0, subSets, permutationList);

        return subSets;
    }

    /**
     * 使用 DFS 设置每个元素出现与不出现两种情况，将全排列的所有可能放入 subSets
     * @param nums 执行全排列的数组
     * @param cur 执行全排列的数组当前下标
     * @param subSets 存放所有全排列的可能
     * @param permutationList 当前排列的结果
     */
    private static void recursionPermutations(int[] nums, int cur, List<List<Integer>> subSets, List<Integer> permutationList) {
        if (cur >= nums.length){
            List<Integer> subSet = new ArrayList<>(permutationList.size());
            subSet.addAll(permutationList);
            subSets.add(subSet);
            return;
        }

        recursionPermutations(nums, cur+1, subSets, permutationList);

        permutationList.add(nums[cur]);
        recursionPermutations(nums, cur+1, subSets, permutationList);
        permutationList.remove((Integer) nums[cur]);
    }
}
