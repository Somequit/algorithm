package leetcode.hot_100.first.hard;

import utils.AlgorithmUtils;
import utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author gusixue
 * @description
 * 297. 二叉树的序列化与反序列化
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，
 * 采取相反方式重构得到原数据。
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
 * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，
 * 你也可以采用其他的方法解决这个问题。
 * 树中结点数在范围 [0, 10 ^ 4] 内
 * -1000 <= Node.val <= 1000
 * @date 2023/11/10
 */
public class Codec {

    /**
     * 使用层序遍历将 val 存入结果（根-左-右），为了记录左/右节点，则将非空的所有节点的叶子全部存入，置为 null，每个节点以逗号分割
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder compressRoot = new StringBuilder();
        if (root == null) {
            compressRoot.append("null");
            return compressRoot.toString();
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerLast(root);
        compressRoot.append(root.val);

        while (!queue.isEmpty()) {
            TreeNode curNode = queue.pollFirst();
            if (curNode.left == null) {
                compressRoot.append(",null");

            } else {
                compressRoot.append(",").append(curNode.left.val);
                queue.offerLast(curNode.left);
            }

            if (curNode.right == null) {
                compressRoot.append(",null");

            } else {
                compressRoot.append(",").append(curNode.right.val);
                queue.offerLast(curNode.right);
            }
        }

        return compressRoot.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.equals("null")) {
            return null;
        }

        // 同个逗号分割，非空树第一个一定是根节点
        String[] treeArray = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(treeArray[0]));

        // 非空节点放入队列，模拟序列化的层序遍历
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerLast(root);

        for (int i = 1; i < treeArray.length; i+=2) {
            TreeNode curNode = queue.pollFirst();
            // 先非空节点的左节点
            if (!"null".equals(treeArray[i])) {
                curNode.left = new TreeNode(Integer.parseInt(treeArray[i]));
                queue.offerLast(curNode.left);
            }

            // 再是非空节点的右节点（一定存在）
            if (!"null".equals(treeArray[i + 1])) {
                curNode.right = new TreeNode(Integer.parseInt(treeArray[i + 1]));
                queue.offerLast(curNode.right);
            }
        }

        return root;
    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        while (true) {
            TreeNode treeNode = AlgorithmUtils.createTree(AlgorithmUtils.systemInList());

            String data = codec.serialize(treeNode);
            System.out.println(data);
            TreeNode newTree = codec.deserialize(data);
            System.out.println(newTree);
        }
    }
}
