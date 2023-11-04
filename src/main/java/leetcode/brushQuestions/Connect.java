package leetcode.brushQuestions;

import javafx.util.Pair;
import template.Algorithm;
import utils.AlgorithmUtils;
import utils.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author gusixue
 * @description
 * 117. 填充每个节点的下一个右侧节点指针 II
 * 给定一个二叉树：
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL 。
 * 初始状态下，所有 next 指针都被设置为 NULL 。
 * 树中的节点数在范围 [0, 6000] 内
 * -100 <= Node.val <= 100
 * @date 2023/11/4
 */
public class Connect {

    public static void main(String[] args) {

        Connect connect = new Connect();
        while (true) {
            TreeNode root = AlgorithmUtils.createTree(AlgorithmUtils.systemInList());

            TreeNode res = connect.solution(root);
            System.out.println(res);
        }

    }

    /**
     * 层序遍历
     * @param root
     * @return
     */
    private TreeNode solution(TreeNode root) {
        // 判空
        if (root == null) {
            return root;
        }

        // 层序遍历，每个节点的 next 指向同层下一个节点
        Deque<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 0));

        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> curPair = queue.poll();
            TreeNode curNode = curPair.getKey();
            int curLayer = curPair.getValue();

            // 同层
            if (!queue.isEmpty() && queue.peek().getValue().equals(curLayer)) {
                curNode.next = queue.peek().getKey();
            }

            if (curNode.left != null) {
                queue.offer(new Pair<>(curNode.left, curLayer + 1));
            }
            if (curNode.right != null) {
                queue.offer(new Pair<>(curNode.right, curLayer + 1));
            }

        }
        return root;
    }
}
