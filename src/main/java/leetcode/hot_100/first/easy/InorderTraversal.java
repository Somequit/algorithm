package leetcode.hot_100.first.easy;

import utils.AlgorithmUtils;
import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gusixue
 * @description
 * 94. 二叉树的中序遍历
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 * @date 2023/11/11
 */
public class InorderTraversal {

    public static void main(String[] args) {
        InorderTraversal inorderTraversal = new InorderTraversal();
        while (true) {
            TreeNode root = AlgorithmUtils.createTree(AlgorithmUtils.systemInList());

            List<Integer> res = inorderTraversal.solution(root);
            System.out.println(res);
        }
    }

    // 递归中序遍历
    private List<Integer> solution(TreeNode root) {
        // 判空
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> resList = new ArrayList<>();

        recursionInorderTraversal(root, resList);

        return resList;
    }

    /**
     * 左-根-右
     */
    private void recursionInorderTraversal(TreeNode node, List<Integer> resList) {
        if (node == null) {
            return;
        }

        recursionInorderTraversal(node.left, resList);
        resList.add(node.val);
        recursionInorderTraversal(node.right, resList);
    }
}
