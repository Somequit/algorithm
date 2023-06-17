package leetcode.easy;

import utils.AlgorithmUtils;
import utils.TreeNode;

/**
 * @author gusixue
 * @description
 * 617. 合并二叉树
 * 给你两棵二叉树： root1 和 root2 。
 * 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null
 * 的节点将直接作为新二叉树的节点。
 * 返回合并后的二叉树。
 * 注意: 合并过程必须从两个树的根节点开始。
 * 两棵树中的节点数目在范围 [0, 2000] 内
 * -10^4 <= Node.val <= 10^4
 * @date 2023/6/17
 */
public class MergeTrees {

    public static void main(String[] args) {
        MergeTrees mergeTrees = new MergeTrees();
        while (true) {
            TreeNode root1 = AlgorithmUtils.createTree(AlgorithmUtils.systemInList());
            TreeNode root2 = AlgorithmUtils.createTree(AlgorithmUtils.systemInList());
            TreeNode resRoot = mergeTrees.solution(root1, root2);
            System.out.println(resRoot);
        }
    }

    /**
     * 两棵树从根节点同时开始遍历（前中后层均可以），这儿选择前序遍历，当两颗树节点均为 null 时才返回，否则按照规则添加即可
     * 优化：如果某个节点为 null，可以直接使用另一棵此子树
     * 时间复杂度：O（min（m,n）），空间复杂度：O（min（m,n））
     */
    private TreeNode solution(TreeNode root1, TreeNode root2) {
        // 判空
        if (root1 == null && root2 == null) {
            return null;
        }

        // 两棵树进行前序遍历
        TreeNode resRoot = recursionPreorder(root1, root2);

        return resRoot;
    }

    /**
     * 两棵树进行前序遍历
     */
    private TreeNode recursionPreorder(TreeNode node1, TreeNode node2) {
        // 两节点均为 null 则返回 null
        if (node1 == null && node2 == null) {
            return null;
        } else if (node1 == null) {
            return node2;
        } else if (node2 == null) {
            return node1;
        }

        // 由于空节点已被返回，val 为俩节点之和
        TreeNode fatherNode = new TreeNode(node1.val + node2.val);

        // 由于空节点已被返回，递归左子结点，返回为父节点的左孩子
        fatherNode.left = recursionPreorder(node1.left, node2.left);

        // 由于空节点已被返回，递归右子结点，返回为父节点的右孩子，注意 null 传入 null
        fatherNode.right = recursionPreorder(node1.right, node2.right);

        // 返回父节点
        return fatherNode;
    }
}
