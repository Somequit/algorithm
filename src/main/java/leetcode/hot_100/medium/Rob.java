package leetcode.hot_100.medium;

import utils.AlgorithmUtils;
import utils.TreeNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gusixue
 * @description
 * 337. 打家劫舍 III
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
 * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
 * 树的节点数在 [1, 10^4] 范围内
 * 0 <= Node.val <= 10^4
 * @date 2023/5/25
 */
public class Rob {

    public static void main(String[] args) {
        Rob rob = new Rob();
        while (true) {
            List<Integer> treeList = AlgorithmUtils.systemInList();
            TreeNode root = AlgorithmUtils.createTree(treeList);
            int res = rob.solution(root);
            System.out.println(res);
        }
    }

    /**
     * 动态规划+二叉树后序遍历：定义二维动规数组 dp[i][2] 代表 i 这颗子树最大偷窃金额，dp[i][0] 代表 i 节点不偷、dp[i][1] 代表 i 节点偷窃，
     * 由于需要先遍历孩子节点再遍历根节点，因此使用后序遍历"左-右-根"，动规转移方程是当前节点不偷时、左右孩子偷与不偷的最大值之和，
     * 当前节点偷窃时、当前节点的值加上左右孩子均不偷的的和
     * 时间复杂度：O（n），空间复杂度：O（n）栈的空间为 n
     */
    private int solution(TreeNode root) {
        // 判空
        if (root == null) {
            return 0;
        }
        // 后序遍历二叉树
        int[] stealOption = postorderTraversal(root);

        return Math.max(stealOption[0], stealOption[1]);
    }

    /**
     * 后续遍历二叉树，返回的数组有两个值，0-不偷、1-偷
     * 计算方式：当前节点不偷时、左右孩子偷与不偷的最大值之和，当前节点偷窃时、当前节点的值加上左右孩子均不偷的的和
     */
    private int[] postorderTraversal(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }

        int[] leftStealOption = postorderTraversal(root.left);
        int[] rightStealOption = postorderTraversal(root.right);
        // 0-不偷 1-偷
        int[] stealOption = new int[2];

        stealOption[0] = Math.max(leftStealOption[0], leftStealOption[1]) + Math.max(rightStealOption[0], rightStealOption[1]);
        stealOption[1] = root.val + leftStealOption[0] + rightStealOption[0];

        return stealOption;
    }
}
