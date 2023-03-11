package utils;

import lombok.ToString;

/**
 * 内部类 树的节点
 */
@ToString
public class TreeNode {
    public int val;
    public TreeNode left = null;
    public TreeNode right = null;

    public TreeNode(int val){
        this.val = val;
    }
}
