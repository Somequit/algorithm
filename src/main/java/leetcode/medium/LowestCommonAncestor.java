package leetcode.medium;

import org.springframework.util.CollectionUtils;
import utils.AlgorithmUtils;
import utils.TreeNode;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 树中节点数目在范围 [2, 105] 内。
 * -109 <= Node.val <= 109
 * 所有 Node.val 互不相同 。
 * p != q
 * p 和 q 均存在于给定的二叉树中。
 *
 * @author gusixue
 * @date 2023/3/10
 */
public class LowestCommonAncestor {

    public static void main(String[] args) {
        LowestCommonAncestor lowestCommonAncestor = new LowestCommonAncestor();

        while (true) {
            List<Integer> treeList = AlgorithmUtils.systemInList();
            TreeNode p = new TreeNode(AlgorithmUtils.systemInNumberInt());
            TreeNode q = new TreeNode(AlgorithmUtils.systemInNumberInt());

            // 建树
            TreeNode root = lowestCommonAncestor.createTree(treeList);
            System.out.println(root);

            TreeNode ancestorNode = lowestCommonAncestor.solution(root, p, q);
            System.out.println(ancestorNode.val);
        }
    }

    /**
     * 通过 List 建树，数组是树的层序方式，如果父节点存在、但子节点存在零个或一个则使用 null 表示
     */
    private TreeNode createTree(List<Integer> treeList) {
        if (CollectionUtils.isEmpty(treeList)) {
            return null;
        }

        TreeNode root = new TreeNode(treeList.get(0));
        Queue<TreeNode> treeNodeQueue = new ConcurrentLinkedQueue<>();
        treeNodeQueue.add(root);
        for (int i = 1; i < treeList.size(); i += 2) {
            TreeNode nowNode = treeNodeQueue.poll();

            if (treeList.get(i) != null) {
                nowNode.left = new TreeNode(treeList.get(i));
                treeNodeQueue.add(nowNode.left);
            }
            if (i + 1 < treeList.size() && treeList.get(i + 1) != null) {
                nowNode.right = new TreeNode(treeList.get(i + 1));
                treeNodeQueue.add(nowNode.right);
            }

        }

        return root;
    }

    private TreeNode solution(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        return getAncestor(root, p ,q);
    }

    /**
     * 最近公共祖先核心：类似前/中/后序方式递归遍历二叉树，
     * 左递归上方代表该课子树还未遍历，左递归与右递归中间代表遍历完左子树回溯，右递归下方代表左右子树都遍历完成处理，
     * 子树未遍历时，如果根节点就是 p 节点，则该子树无需遍历，回溯返回该节点即可，
     *  1. q 是它孩子则返回的是子树最近公共祖先，
     *  2. q 不是孩子则返回 p 节点就往其他方向找（q 和 p 互换），
     * 左右子树遍历后，分情况返回节点
     *  1. 如果左右子树均返回了节点，即 p 和 q 分别在左右子树，根节点就一定是最近公共祖先，返回根节点
     *  2. 如果一颗子树返回节点，那它一定是 p 或 q 或最近公共祖先（深度更大时出现的 1 那种情况），返回那个节点，有三种可能，
     *      要么该子树只存在 p 或者 q，那个节点就是 p 或者 q，
     *      要么 q 是 p 孩子节点，那个节点就是 p（q 和 p 互换），
     *      要么返回的就是最近公共祖先，
     *      都可以返回该节点继续回溯，
     *  3. 如果左右子树均没有返回节点（null），则该子树没有 p 或 q，返回 null
     */
    private TreeNode getAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root.val == p.val || root.val == q.val) {
            return root;
        }

        TreeNode left = getAncestor(root.left, p, q);
        TreeNode right = getAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        } else {
            return null;
        }
    }
}
