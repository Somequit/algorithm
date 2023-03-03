package utils;

/**
 * 内部类 树的节点
 */
public class TreeNode {
    public int val;
    public TreeNode left = null;
    public TreeNode right = null;

    public TreeNode(int val){
        this.val = val;
    }

    @Override
    public String toString() {
        String result = "TreeNode{" +
                "val=" + val;
        if(left != null){
            result += ", left=" + left.val;
        }
        if(right != null){
            result += ", right=" + right.val;
        }
        return result + '}';
    }
}
