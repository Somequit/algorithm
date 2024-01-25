package leetcode.hot_100.first.hard;

import utils.AlgorithmUtils;
import utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gusixue
 * @description
 * 124. 二叉树中的最大路径和
 * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 * 树中节点数目范围是 [1, 3 * 10 ^ 4]
 * -1000 <= Node.val <= 1000
 * @date 2023/9/22
 */
public class MaxPathSum {

    public static void main(String[] args) {
        MaxPathSum maxPathSum = new MaxPathSum();
        while (true) {
            TreeNode root = AlgorithmUtils.createTree(AlgorithmUtils.systemInList());
            int res = maxPathSum.solution(root);
            System.out.println(res);

        }
    }

    /**
     * 前序遍历方式存储每个节点：过当前节点一条链最大值（仅有左 或 右子树），
     * 过当前节点一条链最大值 = max（左子树过当前节点一条链最大值，右子树过当前节点一条链最大值，0）+ 当前节点值
     * 结果是过当前节点的最大值 = max（左子树过当前节点一条链最大值，0）+ max（右子树过当前节点一条链最大值，0）+ 当前节点值，所有节点中再取个最大
     * 时间复杂度：O（n），空间复杂度：O（n）
     */
    private int solution(TreeNode root) {
        // 判空
        if (root == null) {
            return 0;
        }

        // 存储节点-过当前节点一条链最大值
        Map<TreeNode, Integer> nodeMaxNumMap = new HashMap<>();

        // 前序遍历方式存储每个节点两个值：过当前节点的最大值，过当前节点一条链最大值（仅有左 或 右子树），
        return inorderTraversalMaxPathSum(root, nodeMaxNumMap);
    }

    private int inorderTraversalMaxPathSum(TreeNode curNode, Map<TreeNode, Integer> nodeMaxNumMap) {

        if (curNode.left == null && curNode.right == null) {
            nodeMaxNumMap.put(curNode, curNode.val);
            return curNode.val;

        }

        int leftMax = Integer.MIN_VALUE;
        int leftSingleMax = 0;
        if (curNode.left != null) {
            leftMax = inorderTraversalMaxPathSum(curNode.left, nodeMaxNumMap);
            leftSingleMax = nodeMaxNumMap.get(curNode.left);
        }

        int rightMax = Integer.MIN_VALUE;
        int rightSingleMax = 0;
        if (curNode.right != null) {
            rightMax = inorderTraversalMaxPathSum(curNode.right, nodeMaxNumMap);
            rightSingleMax = nodeMaxNumMap.get(curNode.right);
        }

        nodeMaxNumMap.put(curNode, Math.max(0, Math.max(leftSingleMax, rightSingleMax)) + curNode.val);
        int curMax = Math.max(leftSingleMax, 0) + Math.max(rightSingleMax, 0) + curNode.val;

        return Math.max(curMax, Math.max(leftMax, rightMax));
    }

}
