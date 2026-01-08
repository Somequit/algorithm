package leetcode.brushQuestions.medium;

import utils.TreeNode;

/**
 * @author gusixue
 * @description 865. 具有所有最深节点的最小子树
 * @date 2026/1/9 12:05 上午
 */
public class SubtreeWithAllDeepest {

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        int maxDepth = getMaxDepth(root, 0);

        return dfs(root, 0, maxDepth);
    }

    private TreeNode dfs(TreeNode curNode, int curDepth, int maxDepth) {
        if (curDepth == maxDepth - 1) {
            return curNode;
        }

        if (curNode == null) {
            return null;
        }

        TreeNode leftNode = dfs(curNode.left, curDepth + 1, maxDepth);
        TreeNode rightNode = dfs(curNode.right, curDepth + 1, maxDepth);

        if (leftNode != null && rightNode != null) {
            return curNode;
        }

        return leftNode != null ? leftNode : rightNode;

    }

    private int getMaxDepth(TreeNode curNode, int depth) {
        if (curNode == null) {
            return depth;
        }

        return Math.max(getMaxDepth(curNode.left, depth + 1), getMaxDepth(curNode.right, depth + 1));
    }

}
