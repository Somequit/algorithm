package leetcode.brushQuestions.medium;


import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gusixue
 * @description 1261. 在受污染的二叉树中查找元素
 * @date 2024/3/12
 */
public class FindElements {

    private final TreeNode root;

    public FindElements(TreeNode root) {
        this.root = root;

        if (root == null) {
            return;
        }

        createTree(root, 0);
    }

    private void createTree(TreeNode root, int newVal) {
        if (root == null) {
            return;
        }

        root.val = newVal;
        createTree(root.left, newVal * 2 + 1);
        createTree(root.right, newVal * 2 + 2);
    }

    public boolean find(int target) {
        if (this.root == null) {
            return false;

        } else if (target == 0) {
            return true;
        }

        List<Integer> targetHistoryList = new ArrayList<>();
        targetHistoryList.add(target);

        int curTarget = target;
        while (curTarget > 0) {
            curTarget = (curTarget - 1) / 2;
            targetHistoryList.add(curTarget);
        }

        TreeNode curNode = this.root;
        for (int i = targetHistoryList.size() - 2; i >= 0; i--) {

            if (curNode.left != null && curNode.left.val == targetHistoryList.get(i)) {
                curNode = curNode.left;

            } else if (curNode.right != null && curNode.right.val == targetHistoryList.get(i)) {
                curNode = curNode.right;

            } else {
                break;
            }

        }

        return target == curNode.val;
    }
}
