package leetcode.brushQuestions.medium;

import utils.TreeNode;

import java.util.*;

/**
 * @author gusixue
 * @description 2385. 感染二叉树需要的总时间
 * @date 2024/4/24
 */
public class AmountOfTime {

    /**
     * 遍历二叉树找到每个节点感染到叶子节点的最大时间 time，过程中将根节点到 start 节点倒序存入 list，
     * 遍历 list，结果就是每个祖先节点的另一边孩子节点的最大时间加距离 start 的位置再加 1，即 max(time[list[i].otherChild]+i+1)，
     */
    public int amountOfTime(TreeNode root, int start) {
        Map<TreeNode, Integer> mapNodeTime = new HashMap<>();
        List<TreeNode> listNode = new ArrayList<>();

        dfsTree(root, start, listNode, mapNodeTime);

        int res = mapNodeTime.get(listNode.get(0));
        TreeNode prevNode = listNode.get(0);
        for (int i = 1; i < listNode.size(); i++) {
            TreeNode ancestorNode = listNode.get(i);

            if (ancestorNode.left == prevNode) {
                res = Math.max(res, mapNodeTime.getOrDefault(ancestorNode.right, -1) + i + 1);

            } else {
                res = Math.max(res, mapNodeTime.getOrDefault(ancestorNode.left, -1) + i + 1);
            }

            prevNode = ancestorNode;
        }

        return res;
    }

    private int dfsTree(TreeNode curNode, int start, List<TreeNode> listNode, Map<TreeNode,Integer> mapNodeTime) {
        if (curNode == null) {
            return -1;
        }

        int leftTime = dfsTree(curNode.left, start, listNode, mapNodeTime);
        int rightTime = dfsTree(curNode.right, start, listNode, mapNodeTime);

        if (curNode.val == start) {
            listNode.add(curNode);

        } else if (listNode.size() > 0
                && (curNode.left == listNode.get(listNode.size() - 1) || curNode.right == listNode.get(listNode.size() - 1))) {
            listNode.add(curNode);
        }

        int curTime = Math.max(leftTime, rightTime) + 1;
        mapNodeTime.put(curNode, curTime);

        return curTime;
    }
}
