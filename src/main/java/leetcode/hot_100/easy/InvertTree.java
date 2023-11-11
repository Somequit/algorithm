package leetcode.hot_100.easy;

import utils.AlgorithmUtils;
import utils.TreeNode;

import java.util.List;

/**
 * @author gusixue
 * @description
 * 226. 翻转二叉树
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 * 树中节点数目范围在 [0, 100] 内
 * -100 <= Node.val <= 100
 * @date 2023/11/11
 */
public class InvertTree {

    public static void main(String[] args) {
        InvertTree invertTree = new InvertTree();
        while (true) {
            TreeNode root = AlgorithmUtils.createTree(AlgorithmUtils.systemInList());

            TreeNode res = invertTree.solution(root);
            System.out.println(res);
        }
    }

    /**
     * 递归遍历整棵树，从顶到底（从底到顶也行）每个非空节点均反转即可
     * @param root
     * @return
     */
    private TreeNode solution(TreeNode root) {
        // 判空
        if (root == null) {
            return root;
        }


        recursionInvertTree(root);

        return root;
    }

    private void recursionInvertTree(TreeNode node) {
        if (node == null) {
            return;
        }

        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;

        recursionInvertTree(node.left);
        recursionInvertTree(node.right);
    }
}
