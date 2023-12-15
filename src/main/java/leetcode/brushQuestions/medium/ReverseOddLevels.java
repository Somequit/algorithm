package leetcode.brushQuestions.medium;

import javafx.util.Pair;
import utils.TreeNode;

import java.util.*;

/**
 * @author gusixue
 * @description 2415. 反转二叉树的奇数层
 * @date 2023/12/15
 */
public class ReverseOddLevels {

    /**
     * 层序遍历，奇数层则将其放入 list，偶数层且 list 非空，则遍历交换 list 头尾的 val
     */
    public TreeNode reverseOddLevels(TreeNode root) {

        Deque<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 0));
        List<TreeNode> list = new ArrayList<>();

        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> curPair = queue.poll();

            if (curPair.getValue() % 2 == 1) {
                list.add(curPair.getKey());

            } else if (!list.isEmpty()) {

                for (int i = 0; i < list.size() / 2; i++) {
                    TreeNode leftNode = list.get(i);
                    TreeNode rightNode = list.get(list.size() - i - 1);

                    int temp = leftNode.val;
                    leftNode.val = rightNode.val;
                    rightNode.val = temp;
                }
                list.clear();
            }

            if (curPair.getKey() != null) {
                queue.offer(new Pair<>(curPair.getKey().left, curPair.getValue() + 1));
                queue.offer(new Pair<>(curPair.getKey().right, curPair.getValue() + 1));
            }

        }

        return root;
    }
}
