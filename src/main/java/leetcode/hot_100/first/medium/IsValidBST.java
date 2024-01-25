package leetcode.hot_100.first.medium;

import utils.AlgorithmUtils;
import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gusixue
 * @description
 * 98. 验证二叉搜索树
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 有效 二叉搜索树定义如下：
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 树中节点数目范围在[1, 104] 内
 * -2 ^ 31 <= Node.val <= 2 ^ 31 - 1
 * @date 2023/9/21
 */
public class IsValidBST {

    public static void main(String[] args) {
        IsValidBST isValidBST = new IsValidBST();
        while (true) {
            TreeNode root = AlgorithmUtils.createTree(AlgorithmUtils.systemInList());

            boolean res = isValidBST.solution(root);
            System.out.println(res);

            boolean res2 = isValidBST.solution2(root);
            System.out.println(res2);
        }
    }

    /**
     * 使用前序遍历，保证左子树的值在最小值和跟节点之间（不含），右子树的值在根节点到最大值之间（不含）
     */
    private boolean solution2(TreeNode root) {
        // 判空
        if (root == null) {
            return false;
        }

        // 前序遍历返回结果
        return checkPreorderTraversal(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean checkPreorderTraversal(TreeNode curNode, long minValue, long maxValue) {
        if (curNode == null) {
            return true;
        }

        if (curNode.val <= minValue || curNode.val >= maxValue) {
            return false;
        }

        return checkPreorderTraversal(curNode.left, minValue, curNode.val)
                && checkPreorderTraversal(curNode.right, curNode.val, maxValue);
    }

    /**
     * 保证中序遍历为严格递增数组则代表是二叉搜索树
     * 时间复杂度：O（n），空间复杂度：O（n）递归栈+额外空间
     */
    private boolean solution(TreeNode root) {
        // 判空
        if (root == null) {
            return false;
        }

        // 中序遍历返回结果
        List<Integer> treeList = new ArrayList<>();
        listInorderTraversal(root, treeList);

        return checkIncreaseArray(treeList);
    }

    private void listInorderTraversal(TreeNode curNode, List<Integer> treeList) {
        if (curNode == null) {
            return;
        }

        listInorderTraversal(curNode.left, treeList);
        treeList.add(curNode.val);
        listInorderTraversal(curNode.right, treeList);
    }

    private boolean checkIncreaseArray(List<Integer> treeList) {
        boolean res = true;
        for (int i = 1; i < treeList.size(); i++) {
            if (treeList.get(i) <= treeList.get(i - 1)) {
                res = false;
                break;
            }
        }

        return res;
    }
}
