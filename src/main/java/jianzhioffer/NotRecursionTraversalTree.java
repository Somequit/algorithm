package jianzhioffer;

import utils.AlgorithmUtils;
import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 不使用递归遍历树
 */
public class NotRecursionTraversalTree {



    public static void main(String[] args) {
        while(true){
            TreeNode root = AlgorithmUtils.systemInTree();
            // 非递归 层序遍历
            List<TreeNode> treeNodeList = levelTraversal(root);
            System.out.println("层序遍历");
            AlgorithmUtils.systemOutList(treeNodeList, " ");
//            // 递归先序遍历输出一颗树
//            System.out.println("递归先序遍历输出一颗树");
//            AlgorithmUtils.preorderTraversalSystemOutTree(root);
//            System.out.println();
//            List<TreeNode> preorderList = preorderTraversal(root);
//            AlgorithmUtils.systemOutList(preorderList, "");
//            // 递归中序遍历输出一颗树
//            System.out.println("递归中序遍历输出一颗树");
//            AlgorithmUtils.middleOrderTraversalSystemOutTree(root);
//            System.out.println();
//            List<TreeNode> middleOrderList = middleOrderTraversal(root);
//            AlgorithmUtils.systemOutList(middleOrderList, "");
            // 递归后序遍历输出一颗树
            System.out.println("递归后序遍历输出一颗树");
            AlgorithmUtils.postorderTraversalSystemOutTree(root);
            System.out.println();
            List<TreeNode> postOrderList = postorderTraversal(root);
            AlgorithmUtils.systemOutList(postOrderList, "");
        }
    }

    /**
     * 非递归层序遍历
     */
    private static List<TreeNode> levelTraversal(TreeNode root) {
        List<TreeNode> resultList = new ArrayList<>();
        if(root == null){
            return resultList;
        }
        // 左右子树存储队列
        Queue<TreeNode> queue = new ConcurrentLinkedQueue<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode treeTemp = queue.poll();
            if(treeTemp != null) {
                resultList.add(treeTemp);
                if (treeTemp.left != null) {
                    queue.add(treeTemp.left);
                }
                if (treeTemp.right != null) {
                    queue.add(treeTemp.right);
                }
            }
        }
        return resultList;
    }

    /**
     * 非递归前序遍历
     * @param root
     * @return
     */
    private static List<TreeNode> preorderTraversal(TreeNode root) {
        TreeNode rootTemp = root;
        List<TreeNode> resultList = new ArrayList<>();
        if(root == null){
            return resultList;
        }
        // 左右子树存储栈
        Stack<TreeNode> stack = new Stack<>();
        stack.push(rootTemp);
        while(!stack.isEmpty()){
            // 弹栈 此为根
            TreeNode treeTemp = stack.pop();
            resultList.add(treeTemp);
            // 右孩子放在栈底
            if(treeTemp.right != null) {
                stack.push(treeTemp.right);
            }
            // 左孩子先存后出
            if(treeTemp.left != null) {
                stack.push(treeTemp.left);
            }
        }
        return resultList;
    }

    /**
     * 非递归中序遍历
     * @param root
     * @return
     */
    private static List<TreeNode> middleOrderTraversal(TreeNode root) {
        List<TreeNode> resultList = new ArrayList<>();
        if(root == null){
            return resultList;
        }
        // 存储树的栈
        Stack<TreeNode> treeNodeStack = new Stack<>();
        treeNodeStack.push(root);
        while(!treeNodeStack.empty()){
            // 返回栈顶数据
            TreeNode treeTemp = treeNodeStack.peek();
            // 循环左孩子压栈
            while(treeTemp.left != null){
                treeTemp = treeTemp.left;
                treeNodeStack.push(treeTemp);
            }
            // 当前最左边的孩子弹栈输出
            treeTemp = treeNodeStack.pop();
            resultList.add(treeTemp);
            // 当前节点是最左边的孩子，如果没有右孩子那父节点就不再判断左孩子、因此弹栈
            while(!treeNodeStack.empty() && treeTemp.right == null){
                treeTemp = treeNodeStack.pop();
                resultList.add(treeTemp);
            }
            // 上述中父节点如果有右孩子需要压栈继续判断
            if(treeTemp.right != null) {
                treeNodeStack.push(treeTemp.right);
            }
        }
        return resultList;
    }

    /**
     * 非递归后序遍历
     * @param root
     * @return
     */
    private static List<TreeNode> postorderTraversal(TreeNode root) {
        List<TreeNode> resultList =  new ArrayList<>();
        if(root == null){
            return resultList;
        }
        // 树的节点压栈弹栈 保持顺序
        Stack<TreeNode> stack = new Stack<>();
        // 该节点已经被使用过 因此不需要再再次遍历
        Stack<TreeNode> stackUsed = new Stack<>();
        stack.push(root);
        while(!stack.empty()){
            // 当前是否输出 如果左右都不能向下才输出
            boolean flag = true;
            TreeNode treeNodeTemp = stack.peek();
            // 循环左孩子压栈
            while(treeNodeTemp.left != null){
                // 左孩子或者右孩子已经输出过了 该子树不再执行
                if(!stackUsed.empty() && stackUsed.peek() != null){
                    if(stackUsed.peek().val == treeNodeTemp.left.val){
                        stackUsed.pop();
                        break;
                    } else if(treeNodeTemp.right != null && stackUsed.peek().val == treeNodeTemp.right.val) {
                        break;
                    }
                }
                treeNodeTemp = treeNodeTemp.left;
                stack.push(treeNodeTemp);
                flag = false;
            }
            // 右孩子压栈
            if(treeNodeTemp.right != null){
                // 该节点已经输出过了 该子树不再执行
                if(!stackUsed.empty() && stackUsed.peek() != null && stackUsed.peek().val == treeNodeTemp.right.val){
                    stackUsed.pop();
                } else {
                    treeNodeTemp = treeNodeTemp.right;
                    stack.push(treeNodeTemp);
                    flag = false;
                }
            }
            if(flag) {
                treeNodeTemp = stack.pop();
                resultList.add(treeNodeTemp);
                stackUsed.add(treeNodeTemp);
            }
        }
        return resultList;
    }
}
