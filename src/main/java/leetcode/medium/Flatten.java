package leetcode.medium;

import utils.AlgorithmUtils;
import utils.TreeNode;

import java.util.Arrays;

/**
 * @author gusixue
 * @description
 * 114. 二叉树展开为链表
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * 树中结点数在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 * @date 2023/9/22
 */
public class Flatten {

    public static void main(String[] args) {
        Flatten flatten = new Flatten();
        while (true) {
            TreeNode root = AlgorithmUtils.createTree(AlgorithmUtils.systemInList());

            flatten.solution(root);
            System.out.println(root);
        }
    }

    /**
     * 先将链表展开成左节点单链表：使用前序遍历，返回当前节点作为上一个节点，回溯时如果该节点为父节点的右节点则将其移动到上一个节点的左节点、否则不动
     * 再从根节点到非叶子节点，将所有节点的左节点变成右节点
     * @param root
     */
    private void solution(TreeNode root) {
        // 判空
        if (root == null) {
            return;
        }

        // 将链表展开成左节点单链表：使用前序遍历，返回当前节点作为上一个节点，回溯时如果该节点为父节点的右节点则将其移动到上一个节点的左节点、否则不动
        preorderTraversalFlattenLeft(root);
//        System.out.println(root);

        // 从根节点开始，将所有节点的左节点变成右节点
        reverseLeftToRightTree(root);
    }

    /**
     * 从根节点开始，将所有节点的左节点变成右节点
     * @param curNode
     */
    private void reverseLeftToRightTree(TreeNode curNode) {
        while (curNode != null) {
            curNode.right = curNode.left;
            curNode.left = null;

            curNode = curNode.right;
        }
    }

    /**
     * 将链表展开成左节点单链表：使用前序遍历，返回当前节点作为上一个节点，回溯时如果该节点为父节点的右节点则将其移动到上一个节点的左节点、否则不动
     * @param curNode
     */
    private TreeNode preorderTraversalFlattenLeft(TreeNode curNode) {
        if (curNode.left == null && curNode.right == null) {
            return curNode;
        }


        TreeNode leftNode = null;
        if (curNode.left != null) {
            leftNode = preorderTraversalFlattenLeft(curNode.left);
        }

        if (curNode.right != null) {
            TreeNode rightNode = null;

            // 先移动到上一个节点的左边、再进入右节点
            if (leftNode != null) {
                leftNode.left = curNode.right;
                curNode.right = null;
                rightNode = preorderTraversalFlattenLeft(leftNode.left);

            } else {
                curNode.left = curNode.right;
                curNode.right = null;
                rightNode = preorderTraversalFlattenLeft(curNode.left);
            }
            return rightNode;

        } else {
            return leftNode;
        }

    }
}
