package leetcode.hot_100.easy;

import lombok.ToString;
import utils.AlgorithmUtils;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 543. 二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 * 树中节点数目在范围 [1, 10 ^ 4] 内
 * -100 <= Node.val <= 100
 * @author gusixue
 * @date 2023/2/15
 */
public class DiameterOfBinaryTree {


    @ToString
    public class TreeNode {
        int val;
        DiameterOfBinaryTree.TreeNode left;
        DiameterOfBinaryTree.TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, DiameterOfBinaryTree.TreeNode left, DiameterOfBinaryTree.TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        // 数组使用完全二叉树格式
        List<Integer> listTree = AlgorithmUtils.systemInList();
        System.out.println(listTree);

        DiameterOfBinaryTree diameterOfBinaryTree = new DiameterOfBinaryTree();
        TreeNode root = diameterOfBinaryTree.createTree(listTree);
        System.out.println(root);

        int res = diameterOfBinaryTree.solution(root);
        System.out.println(res);
    }

    private TreeNode createTree(List<Integer> listTree) {
        if (listTree == null || listTree.size() == 0) {
            return null;
        }

        TreeNode root = new TreeNode(listTree.get(0));
        Queue<TreeNode> queue = new ConcurrentLinkedQueue<>();
        queue.add(root);
        for (int i = 1; i < listTree.size(); i+=2) {
            TreeNode node = queue.poll();

            if (listTree.get(i) != null) {
                node.left = new TreeNode(listTree.get(i));
                queue.add(node.left);
            }
            if (i + 1 < listTree.size() && listTree.get(i + 1) != null) {
                node.right = new TreeNode(listTree.get(i + 1));
                queue.add(node.right);
            }
        }
        return root;
    }

    private int res = 0;

    /**
     * 首先两个点路径长度等于两个节点之间节点数（包括俩点）减一，
     * 其次任意一条路径，均可以看做以某个节点为根，它左子树的深度（左孩子经过的节点个数）与右子树深度的和
     * 然后树上最大直径长度可以通过遍历每个点，求它左子树最大深度与右子树最大深度的和，其中最大值就是结果
     * @param root
     * @return
     */
    private int solution(TreeNode root) {
        if (root == null) {
            return 0;
        }

        maxDiameter(root);
        return res;
    }

    private int maxDiameter(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftMaxDep = maxDiameter(node.left);
        int rightMaxDep = maxDiameter(node.right);

        res = Math.max(leftMaxDep + rightMaxDep, res);
        return Math.max(leftMaxDep, rightMaxDep) + 1;
    }

}
