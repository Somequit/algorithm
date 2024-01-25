package leetcode.hot_100.first.easy;

import utils.AlgorithmUtils;
import utils.TreeNode;

import java.util.List;

/**
 * @author gusixue
 * @description
 * 104. 二叉树的最大深度
 * 给定一个二叉树 root ，返回其最大深度。
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 * 树中节点的数量在 [0, 104] 区间内。
 * -100 <= Node.val <= 100
 * @date 2023/11/11
 */
public class MaxDepth {

    public static void main(String[] args) {
        MaxDepth maxDepth = new MaxDepth();
        while (true) {
            TreeNode root = AlgorithmUtils.createTree(AlgorithmUtils.systemInList());

            int res = maxDepth.solution(root);
            System.out.println(res);
        }
    }

    /**
     * 递归返回左子树深度与右子树深度最大值再加一
     * @param root
     * @return
     */
    private int solution(TreeNode root) {
        // 判空
        if (root == null) {
            return 0;
        }

        return recursionMaxDepth(root);
    }

    private int recursionMaxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return Math.max(recursionMaxDepth(node.left), recursionMaxDepth(node.right)) + 1;
    }
}
