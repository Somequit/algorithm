package leetcode.brushQuestions.medium;

import utils.TreeNode;

import java.util.*;

/**
 * @author gusixue
 * @description 894. 所有可能的真二叉树
 * @date 2024/4/2
 */
public class AllPossibleFBT2 {

    private static final List<TreeNode>[] LIST_FBT;
    static {
        LIST_FBT = new ArrayList[10];
        Arrays.setAll(LIST_FBT, arr -> new ArrayList<>());

        // 1 个节点仅有根节点
        LIST_FBT[0].add(new TreeNode(0));

        // i 个节点可以拆分为：左子树 1、3、5...个节点、右子树 i-2、i-4、i-6...个节点，然后就可以拆分为更小的子问题
        for (int all = 3; all < 20; all += 2) {
            // 枚举左子树节点个数
            for (int left = 1; left < all - 1; left += 2) {
                int right = all - left - 1;

                // 枚举左子树所有类型
                for (TreeNode leftTreeNode : LIST_FBT[left / 2]) {
                    // 枚举右子树所有类型
                    for (TreeNode rightTreeNode : LIST_FBT[right / 2]) {

                        LIST_FBT[all / 2].add(new TreeNode(0, leftTreeNode, rightTreeNode));
                    }
                }
            }
        }
    }

    // 预处理 + 动态规划
    public List<TreeNode> allPossibleFBT(int n) {
        // 偶数不能构成
        if (n % 2 == 0) {
            return new ArrayList<>();
        } else {
            return LIST_FBT[n / 2];
        }
    }

}
