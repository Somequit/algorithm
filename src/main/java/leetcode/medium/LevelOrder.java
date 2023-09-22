package leetcode.medium;

import javafx.util.Pair;
import utils.AlgorithmUtils;
import utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author gusixue
 * @description
 * 102. 二叉树的层序遍历
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 * @date 2023/9/22
 */
public class LevelOrder {

    public static void main(String[] args) {
        LevelOrder levelOrder = new LevelOrder();
        while (true) {
            TreeNode root = AlgorithmUtils.createTree(AlgorithmUtils.systemInList());
            List<List<Integer>> res = levelOrder.solution(root);
            System.out.println(res);

        }
    }

    /**
     * 使用队列遍历
     * @param root
     * @return
     */
    public List<List<Integer>> solution(TreeNode root) {
        // 判空
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList<>();
        // 使用队列遍历 node-layer
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 0));
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> curNodePair = queue.poll();

            if (curNodePair.getValue() + 1 > res.size()) {
                res.add(new ArrayList<>());
            }
            res.get(res.size() - 1).add(curNodePair.getKey().val);

            if (curNodePair.getKey().left != null) {
                queue.offer(new Pair<>(curNodePair.getKey().left, curNodePair.getValue() + 1));

            }
            if (curNodePair.getKey().right != null) {
                queue.offer(new Pair<>(curNodePair.getKey().right, curNodePair.getValue() + 1));
            }

        }

        return res;
    }
}
