package leetcode.medium;

import lombok.extern.slf4j.Slf4j;
import utils.AlgorithmUtils;
import utils.TreeNode;

import java.util.*;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历，
 * inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder 和 inorder 均 无重复 元素
 * inorder 均出现在 preorder
 * preorder 保证 为二叉树的前序遍历序列
 * inorder 保证 为二叉树的中序遍历序列
 *
 * @author gusixue
 * @date 2023/3/17
 */
@Slf4j
public class BuildTree {

    public static void main(String[] args) {
        BuildTree buildTree = new BuildTree();

        buildTree.test();

        while (true) {
            int[] preorder = AlgorithmUtils.systemInArray();
            int[] inorder = AlgorithmUtils.systemInArray();

            TreeNode root = buildTree.solution(preorder, inorder);
            System.out.println(root);

            TreeNode root2 = buildTree.solution2(preorder, inorder);
            System.out.println(root2);
        }

    }

    private void test() {
        TreeNode root1 = solution2(new int[]{1, 2, 3}, new int[]{2, 1, 3});

        assert 1 == root1.val;
        assert 2 == root1.left.val;
        assert 3 == root1.right.val;

        TreeNode root2 = solution2(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});

        assert 3 == root2.val;
        assert 9 == root2.left.val;
        assert 20 == root2.right.val;
        assert 15 == root2.right.left.val;
        assert 7 == root2.right.right.val;
        assert 1 == -1;

    }

    /**
     * 前序第一个节点是中序的根节点，中序通过根节点后划分左右两个区间，然后分别递归左区间和右区间，直到仅有一个元素即回溯，
     * 这个过程前序一直顺序遍历，每个节点都是中序当前区间的根节点（可先存储中序到 Map，快速获取根节点位置），直到前序遍历完成，树就建立好了
     * @param preorder 树的正确前序遍历
     * @param inorder 树的正确中续遍历
     * @return 构成树的根节点
     */
    public TreeNode solution(int[] preorder, int[] inorder) {
        // 判空
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0) {
            return null;
        }

        // 根据前序第一个节点建根节点
        TreeNode root = new TreeNode(preorder[0]);

        // 存储中序遍历值-下标，用于快速找中序根节点下标，空间换时间
        Map<Integer, Integer> inorderMap = createInorderMap(inorder);

        // 递归建树
        recursionCreateTree(root, preorder, 0, inorder, 0, inorder.length - 1, inorderMap);

        return root;
    }

    /**
     * 存储中序遍历值-下标，用于快速找中序根节点下标，空间换时间
     * @param inorder
     * @return 中序遍历的 值-下标
     */
    private Map<Integer,Integer> createInorderMap(int[] inorder) {
        Map<Integer, Integer> inorderMap = new HashMap<>((inorder.length >> 2) * 3);

        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return inorderMap;
    }

    /**
     * 递归建树
     * @param root 当前子树根节点
     * @param preorder 前序数组
     * @param preIndex 前序数组遍历的下标
     * @param inorder 中序数组
     * @param beginIndex 中序当前区间开始下标（含）
     * @param endIndex 中序当前区间结束下标（含）
     * @param inorderMap 中序数组的 值-下标
     * @return 前一个前序数组遍历的下标
     */
    public int recursionCreateTree(TreeNode root, int[] preorder, int preIndex, int[] inorder,
                                     int beginIndex, int endIndex, Map<Integer, Integer> inorderMap) {
        // log.info("preIndex:{} beginIndex:{} endIndex:{}", preIndex, beginIndex, endIndex);
        // 仅有一个节点就是根节点
        if (endIndex == beginIndex) {
            // 根节点赋值
            root.val = preorder[preIndex];
            return preIndex;
        }

        int midIndex = inorderMap.getOrDefault(preorder[preIndex], -1);
        // log.info("midIndex:{}", midIndex);

        if (midIndex == -1) {
            throw new RuntimeException("输入前序或者中序异常 ...");
        }

        // 根节点赋值
        root.val = inorder[midIndex];

        // 中序数组查到当前左子树存在
        if (beginIndex < midIndex) {
            // 先建立左子树节点，后续再赋值
            TreeNode leftNode = new TreeNode();
            root.left = leftNode;
            preIndex = recursionCreateTree(leftNode, preorder, preIndex + 1, inorder,
                    beginIndex, midIndex - 1, inorderMap);
        }
        // 中序数组查到当前右子树存在
        if (endIndex > midIndex) {
            // 先建立右子树节点，后续再赋值
            TreeNode rightNode = new TreeNode();
            root.right = rightNode;
            preIndex = recursionCreateTree(rightNode, preorder, preIndex + 1, inorder,
                    midIndex + 1, endIndex, inorderMap);
        }

        return preIndex;
    }


    /**
     * 迭代解决：前序相邻节点 a1、a2 有两大类关系：a2 是 a1 的左孩子，a2 是 a1（以及 a1 到根节点一条链中作为左孩子的父节点称 f`）的右孩子，
     * 中序第一个节点是最左边的节点，前序第一个节点是根节点，遍历前序与中序，
     *     步骤一：如果树上当前节点不等于中序节点，意思是还未到最左边，先序下一节点就是树上当前节点的左孩子，先序往后移，回到 步骤一 循环
     *     步骤二：如果等于的话，先序下一个节点就是的树上或者 f` 的右孩子，然后中序往后移（当前中序等于树上、所以被使用过了），
     *         如果中序节点不等于树上当前节点最近的 f`，代表 f` 左子树未满，先序下一个节点就是树上当前节点的右孩子，回到步骤一循环
     *         如果等于的话，先序下一个节点就是 f` 的右孩子，回到 步骤二 循环
     * 过程中，树要找 f` 可以添加指向父节点的索引，也可以使用栈、先序往后就入栈、查找 f` 就出栈即可
     * @param preorder 树的正确前序遍历
     * @param inorder 树的正确中续遍历
     * @return 构成树的根节点
     */
    private TreeNode solution2(int[] preorder, int[] inorder) {
        // 判空
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0) {
            return null;
        }

        // 根据前序第一个节点建根节点
        TreeNode root = new TreeNode(preorder[0]);

        // 建栈（链表模拟）查 f`
        Deque<TreeNode> treeFatherStack = new LinkedList<>();
        treeFatherStack.addFirst(root);

        // 迭代建树
        createTree(preorder,inorder,treeFatherStack);

        return root;
    }

    /**
     * 迭代建树
     * @param preorder 前序数组
     * @param inorder 中序数组
     * @param treeFatherStack 查询 f` 的栈
     */
    private void createTree(int[] preorder, int[] inorder, Deque<TreeNode> treeFatherStack) {

        // 中序下标
        int inoIndex = 0;

        // 树上当前节点
        TreeNode currNode = null;
        // 遍历前序
        for (int i = 1; i < preorder.length; i++) {
            currNode = treeFatherStack.getFirst();

            // 树上节点不等于中序，前序节点为左孩子
            if (currNode.val != inorder[inoIndex]) {
                currNode.left = new TreeNode(preorder[i]);
                treeFatherStack.addFirst(currNode.left);

                // 先序下一个节点就是的 树上或者 f` 的右孩子
            } else {
                inoIndex++;

                // 循环根节点时就一定为当前父节点
                while (treeFatherStack.size() > 1) {
                    // 可能为树上当前节点的右节点，因此树上节点的父节点不再保存
                    treeFatherStack.pop();
                    // 转到 f` 节点
                    TreeNode fatherNode = treeFatherStack.getFirst();
                    // 如果中序节点不等于树上当前节点最近的 f`，代表 f` 左子树未满，先序下一个节点就是树上当前节点的右孩子
                    if (fatherNode.val != inorder[inoIndex]) {
                        break;
                    }
                    // 如果等于的话，先序下一个节点就是 f` 的右孩子
                    currNode = fatherNode;
                    inoIndex++;
                }

                currNode.right = new TreeNode(preorder[i]);
                treeFatherStack.addFirst(currNode.right);
            }

        }

    }
}
