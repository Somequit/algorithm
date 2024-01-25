package leetcode.hot_100.first.easy;

import lombok.ToString;
import org.springframework.util.CollectionUtils;
import utils.AlgorithmUtils;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 101. 对称二叉树
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * 树中节点数目在范围 [1, 1000] 内
 * -100 <= Node.val <= 100
 * 进阶：你可以运用递归和迭代两种方法解决这个问题吗？
 * @author gusixue
 * @date 2023/2/16
 */
public class IsSymmetric {
    @ToString
    public class TreeNode {
        int val;
        IsSymmetric.TreeNode left;
        IsSymmetric.TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, IsSymmetric.TreeNode left, IsSymmetric.TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        // 数组使用完全二叉树格式
        List<Integer> listTree = AlgorithmUtils.systemInList();
        System.out.println(listTree);

        IsSymmetric isSymmetric = new IsSymmetric();
        IsSymmetric.TreeNode root = isSymmetric.createTree(listTree);
        System.out.println(root);

        boolean res = isSymmetric.solution(root);
        System.out.println(res);
    }

    private TreeNode createTree(List<Integer> listTree) {
        if (CollectionUtils.isEmpty(listTree) || listTree.get(0) == null) {
            return null;
        }

        TreeNode root = new TreeNode(listTree.get(0));
        Queue<TreeNode> queue = new ConcurrentLinkedQueue<>();
        queue.add(root);
        for (int i = 1; i < listTree.size(); i+=2) {
            TreeNode node = queue.poll();
            if (node == null) {
                break;
            }

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

    /**
     * 根的左右孩子分别形成 L 子树与 R 子树，然后对 L 子树使用先序遍历、同时对 R 子树使用"根-右-左"方式遍历，如果位置与值均相同则返回相同
     * @param root
     * @return
     */
    private boolean solution(TreeNode root) {
        if (root == null) {
            return true;
        }

        return traversalTree(root.left, root.right);
    }

    private boolean traversalTree(TreeNode lTree, TreeNode rTree) {
        if (lTree == null && rTree == null) {
            return true;
        } else if (lTree == null || rTree == null) {
            return false;
        } else if (lTree.val != rTree.val) {
            return false;
        }

        if (!traversalTree(lTree.left, rTree.right) || !traversalTree(lTree.right, rTree.left)) {
            return false;
        }

        return true;
    }

}
