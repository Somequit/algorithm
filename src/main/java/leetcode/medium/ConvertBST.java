package leetcode.medium;

import utils.AlgorithmUtils;
import utils.TreeNode;

/**
 * @author gusixue
 * @description
 * 538. 把二叉搜索树转换为累加树
 * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
 * 提醒一下，二叉搜索树满足下列约束条件：
 *     节点的左子树仅包含键 小于 节点键的节点。
 *     节点的右子树仅包含键 大于 节点键的节点。
 *     左右子树也必须是二叉搜索树。
 * 树中的节点数介于 0 和 10^4 之间。
 * 每个节点的值介于 -10^4 和 10^4 之间。
 * 树中的所有值 互不相同 。
 * 给定的树为二叉搜索树。
 * @date 2023/5/14
 */
public class ConvertBST {

    public static void main(String[] args) {
        ConvertBST convertBST = new ConvertBST();
        while (true) {
            TreeNode root = AlgorithmUtils.createTree(AlgorithmUtils.systemInList());

            TreeNode res1 = convertBST.solution1(root);
            System.out.println(res1);

            TreeNode res2 = convertBST.solution2(root);
            System.out.println(res2);
        }
    }

    /**
     * 累加树本质上就是从最后一个元素往前，每个元素加上前面所有元素 val 的和
     * 反序中序递归：使用"右-根-左"的方式遍历，每个根节点加上 上一个节点的 val 总值，
     * 注意：由于上一个节点不一定是直接孩子/父亲节点，因此要么使用全局变量保存，要么使用就传值、然后每个递归与回溯都要返回当前值（如果更新返回跟新后的值）
     * 时间复杂度：O（n），空间复杂度：O（n）
     */
    private TreeNode solution1(TreeNode root) {

        traversalTree(root, 0);

        return root;
    }

    private int traversalTree(TreeNode root, int parentVal) {
        // 空节点
        if (root == null) {
            return parentVal;
        }

        // 右-根-左
        int prevRightVal = traversalTree(root.right, parentVal);
        root.val += prevRightVal;
        int prevLeftVal =  traversalTree(root.left, root.val);

        return prevLeftVal;
    }

    /**
     * 累加树本质上就是从最后一个元素往前，每个元素加上前面所有元素 val 的和
     * 循环：
     * 时间复杂度：O（n），空间复杂度：O（1）
     */
    private TreeNode solution2(TreeNode root) {
        // loopTree
        return root;
    }
}
