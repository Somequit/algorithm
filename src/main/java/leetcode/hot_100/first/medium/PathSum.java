package leetcode.hot_100.first.medium;

import utils.AlgorithmUtils;
import utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gusixue
 * @description
 * 437. 路径总和 III
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * 二叉树的节点个数的范围是 [0,1000]
 * -10 ^ 9 <= Node.val <= 10 ^ 9
 * -1000 <= targetSum <= 1000
 * @date 2023/9/22
 */
public class PathSum {

    public static void main(String[] args) {
        PathSum pathSum = new PathSum();
        while (true) {
            TreeNode root = AlgorithmUtils.createTree(AlgorithmUtils.systemInList());
            int targetSum = AlgorithmUtils.systemInNumberInt();
            int res = pathSum.solution(root, targetSum);
            System.out.println(res);

        }
    }

    /**
     * 从根节点开始前序遍历二叉树，类似子区间和为 k 的做法，求树上链路前缀和 prefix，放入 Map 中，每个点求 prefix-targetSum 在 Map 的个数就加到结果上，
     * 注意进入子节点 prefix 与 Map 都需要加上子节点，回溯到父节点 prefix 与 Map 都需要减去子节点
     * 时间复杂度：O（n），空间复杂度：O（n）
     */
    private int solution(TreeNode root, int targetSum) {
        // 判空
        if (root == null) {
            return 0;
        }

        // 前序遍历二叉树,类似子区间和为 k 的做法
        long prefix = 0L;
        Map<Long, Integer> prefixCountMap = new HashMap<>();
        prefixCountMap.put(0L, 1);
        return preorderTraversalPathSum(root, targetSum, prefix, prefixCountMap);
    }

    /**
     * 前序遍历二叉树,类似子区间和为 k 的做法
     */
    private int preorderTraversalPathSum(TreeNode curNode, int targetSum, long prefix, Map<Long,Integer> prefixCountMap) {
        if (curNode == null) {
            return 0;
        }

        prefix += curNode.val;
        int res = prefixCountMap.getOrDefault(prefix - targetSum, 0);
        prefixCountMap.put(prefix, prefixCountMap.getOrDefault(prefix, 0) + 1);
//        System.out.println(prefix + " : " + prefixCountMap);

        res += preorderTraversalPathSum(curNode.left, targetSum, prefix, prefixCountMap);
        res += preorderTraversalPathSum(curNode.right, targetSum, prefix, prefixCountMap);

        prefixCountMap.put(prefix, prefixCountMap.get(prefix) - 1);
        prefix -= curNode.val;

        return res;
    }
}
