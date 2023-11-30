package leetcode.brushQuestions.easy;

import utils.TreeNode;

/**
 * @author gusixue
 * @description
 * 108. 将有序数组转换为二叉搜索树
 * @date 2023/11/30
 */
public class SortedArrayToBST {
    public static void main(String[] args) {
        SearchInsert contest = new SearchInsert();

        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

//            int res = contest.solution(n, nums, list);
//            System.out.println(res);
        }
    }

    /**
     * @return
     */
    private TreeNode solution(int[] nums) {
        return recursionTree(new TreeNode(), nums, 0, nums.length);
    }

    private TreeNode recursionTree(TreeNode node, int[] nums, int begin, int end) {
        if (begin >= end) {
            return null;
        }

        int mid = (begin + end) >> 1;
        node.val = nums[mid];

        node.left = recursionTree(new TreeNode(), nums, begin, mid);
        node.right = recursionTree(new TreeNode(), nums, mid + 1, end);

        return node;
    }
}
