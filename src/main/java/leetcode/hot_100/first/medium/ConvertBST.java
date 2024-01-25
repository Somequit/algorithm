package leetcode.hot_100.first.medium;

import utils.AlgorithmUtils;
import utils.TreeNode;

import java.util.List;

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
            List<Integer> treeList = AlgorithmUtils.systemInList();
            TreeNode root = AlgorithmUtils.createTree(treeList);
            TreeNode root2 = AlgorithmUtils.createTree(treeList);

            TreeNode res1 = convertBST.solution1(root);
            System.out.println(res1);

            TreeNode res2 = convertBST.solution2(root2);
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
     * 循环、Morris 遍历：核心思想是修改树的空指针，实现树型转化为数组型方便遍历，此时空间就能极限压缩。反中序遍历规则总结如下：
     * 1. 如果当前节点的右子节点为空，当前节点就累加上，然后移动到当前节点的左子节点（可通过"虚拟左子节点"回到前驱）
     * 2. 如果当前节点的右子节点不为空，找到当前节点右子树的"真实左子节点"的最后一个、称为最左子节点（该节点为当前节点反中序遍历的前驱节点）
     *     如果最左子节点的"虚拟左子节点"为空，将最左节点的"虚拟左子节点"指向当前节点（修改树添加"虚拟左子节点"，指向前驱、用于"回溯"），然后移动到当前节点的右子节点
     *     如果最左子节点的"虚拟左子节点"不为空，将"虚拟左子节点"重新置为空（恢复树的原状删除"虚拟左子节点"，右子树完成），当前节点就累加上，然后移动到当前节点的左子节点
     * 3. 重复步骤 1 和步骤 2，直到遍历结束。
     * 时间复杂度：O（n）每个点最多访问两次，空间复杂度：O（1）
     */
    private TreeNode solution2(TreeNode root) {
        // 判空
        if (root == null) {
            return root;
        }

        // 从根节点开始 Morris 遍历
        TreeNode cur = root;
        int sum = 0;
        while (cur != null) {
            // 当前节点的右子节点为空
            if (cur.right == null) {
                // 当前节点累加
                sum += cur.val;
                cur.val = sum;
                // 移动到左子节点（可通过"虚拟左子节点"回到前驱）
                cur = cur.left;

            // 如果当前节点的右子节点不为空
            } else {
                // 找到当前节点反中序遍历的前驱节点
                TreeNode prev = getTreePrevious(cur);

                // "虚拟左子节点"为空
                if (prev.left == null) {
                    // "虚拟左子节点"指向当前节点
                    prev.left = cur;
                    // 然后移动到当前节点的右子节点
                    cur = cur.right;

                // "虚拟左子节点"不为空
                } else {
                    // "虚拟左子节点"重新置为空
                    prev.left = null;
                    // 当前节点就累加
                    sum += cur.val;
                    cur.val = sum;
                    // 然后移动到当前节点的左子节点
                    cur = cur.left;

                }

            }
        }

        return root;
    }

    /**
     * 找到当前节点反中序遍历的前驱节点
     * @param cur cur 节点存在右子节点
     */
    private TreeNode getTreePrevious(TreeNode cur) {
        TreeNode prev = cur.right;
        // "虚拟左子节点"不能返回
        while (prev.left != null && prev.left != cur) {
            prev = prev.left;
        }
        return prev;
    }
}
