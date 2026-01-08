package leetcode.brushQuestions.medium;

import utils.TreeNode;

import java.util.*;

/**
 * @author gusixue
 * @description 1339. 分裂二叉树的最大乘积
 * @date 2026/1/8 10:54 下午
 */
public class MaxProduct {

    public int maxProduct(TreeNode root) {
        int mod = (int) (1e9 + 7);
        List<Integer> listTotalSubTree = new ArrayList<>();
        dfs(listTotalSubTree, root);

        long maxProduct = 0;
        int totalVal = listTotalSubTree.get(listTotalSubTree.size() - 1);
        for (int i = 0; i < listTotalSubTree.size() - 1; i++) {
            int curTotal = listTotalSubTree.get(i);
            maxProduct = Math.max(maxProduct, (long) curTotal * (totalVal - curTotal));
        }

        return (int) (maxProduct % mod);
    }

    private int dfs(List<Integer> listTotalSubTree, TreeNode curNode) {
        if (curNode == null) {
            return 0;
        }

        int curVal = dfs(listTotalSubTree, curNode.left) + dfs(listTotalSubTree, curNode.right) + curNode.val;
        listTotalSubTree.add(curVal);
        return curVal;
    }
}
