package leetcode.brushQuestions.medium;

import javafx.util.Pair;
import utils.TreeNode;

import java.util.*;

/**
 * @author gusixue
 * @description 1026. 节点与其祖先之间的最大差值
 * @date 2024/4/5
 */
public class MaxAncestorDiff {

    /**
     * 树形 dp，dp[i][0]：节点 i 子树（含 i）的最小值、dp[i][1]：节点 i 子树（含 i）的最大值，注意叶子节点最小值最大值等于自己
     * dp[i][0]=Math.min(dp[child][0], val[i])，dp[i][1] 相同做法
     *
     */
    public int maxAncestorDiff(TreeNode root) {
        Map<TreeNode, Pair<Integer, Integer>> mapDpMinMax = new HashMap<>();

        return dfsMaxAncestorDiff(root, mapDpMinMax);
    }

    private int dfsMaxAncestorDiff(TreeNode curNode, Map<TreeNode,Pair<Integer,Integer>> mapDpMinMax) {
        if (curNode == null) {
            return 0;
        }

        // 叶子节点
        if (curNode.left == null && curNode.right == null) {
            mapDpMinMax.put(curNode, new Pair<>(curNode.val, curNode.val));
            return 0;
        }

        int res = dfsMaxAncestorDiff(curNode.left, mapDpMinMax);
        res = Math.max(res, dfsMaxAncestorDiff(curNode.right, mapDpMinMax));

        int minVal = curNode.val;
        int maxVal = curNode.val;
        if (curNode.left != null) {
            minVal = Math.min(minVal, mapDpMinMax.get(curNode.left).getKey());
            maxVal = Math.max(maxVal, mapDpMinMax.get(curNode.left).getValue());
        }
        if (mapDpMinMax.get(curNode.right) != null) {
            minVal = Math.min(minVal, mapDpMinMax.get(curNode.right).getKey());
            maxVal = Math.max(maxVal, mapDpMinMax.get(curNode.right).getValue());
        }

        mapDpMinMax.put(curNode, new Pair<>(minVal, maxVal));

        return Math.max(res, Math.max(Math.abs(curNode.val - minVal), Math.abs(maxVal - curNode.val)));
    }
}
