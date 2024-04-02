package leetcode.brushQuestions.medium;

import javafx.util.Pair;
import utils.TreeNode;

import java.util.*;

/**
 * @author gusixue
 * @description 894. 所有可能的真二叉树
 * @date 2024/4/2
 */
public class AllPossibleFBT {

    /**
     * 求解过程中会将前面数据求出，因此可以预处理
     */
    public List<TreeNode> allPossibleFBT(int n) {
        // 偶数不能构成
        if (n % 2 == 0) {
            return new ArrayList<>();
        } else {
            return LIST_FBT[n / 2];
        }
    }

    private static final List<TreeNode>[] LIST_FBT;
    static {
        LIST_FBT = new ArrayList[10];

        List<TreeNode> listNode = new ArrayList<>();
        TreeNode root = new TreeNode(0);
        listNode.add(root);
        Set<Set<Integer>> setDuplicate = new HashSet<>();
        Set<Integer> setNodeNum = new HashSet<>();
        setNodeNum.add(1);
        setDuplicate.add(setNodeNum);

        // 存入 TreeSet 叶子节点的值去重，节点值：根节点为 1、左子节点=父节点*2、右子节点=父节点*2+1
        doAllPossibleFBT(listNode, 20, setDuplicate);
    }

    /**
     * 遍历当前所有树，深拷贝每颗树，枚举每个叶子添加俩子节点同时 count-2，过程中注意去重，直到 count 为 0
     */
    private static void doAllPossibleFBT(List<TreeNode> listNode, int count, Set<Set<Integer>> setDuplicate) {
        List<TreeNode> resList = listNode;
        LIST_FBT[0] = resList;

        Set<Integer> setNodeNum = new HashSet<>();
        // 叶子节点的值 - 叶子节点
        Map<Integer, TreeNode> mapAllLeaf = new HashMap<>();

        for (int i = 1; i < count / 2; i++) {
            listNode = new ArrayList<>();

            for (TreeNode curTreeNode : resList) {
                deepCopyTreeNode(curTreeNode, 1, setNodeNum, mapAllLeaf);

                for (Map.Entry<Integer, TreeNode> leaf : mapAllLeaf.entrySet()) {
                    Set<Integer> setNodeNumTemp = new HashSet<>(setNodeNum);
                    setNodeNumTemp.remove(leaf.getKey());
                    setNodeNumTemp.add(leaf.getKey() * 2);
                    setNodeNumTemp.add(leaf.getKey() * 2 + 1);

                    if (setDuplicate.contains(setNodeNumTemp)) {
                        continue;
                    }

                    setDuplicate.add(setNodeNumTemp);

                    Map<Integer, TreeNode> mapAllLeafTemp = new HashMap<>();
                    TreeNode nodeTemp = deepCopyTreeNode(curTreeNode, 1, setNodeNum, mapAllLeafTemp);
                    TreeNode leafTemp = mapAllLeafTemp.get(leaf.getKey());
                    leafTemp.left = new TreeNode();
                    leafTemp.right = new TreeNode();

                    listNode.add(nodeTemp);
                }

                setNodeNum.clear();
                mapAllLeaf.clear();
            }

            resList = listNode;

            LIST_FBT[i] = resList;
        }
    }

    private static TreeNode deepCopyTreeNode(TreeNode fromTreeNode, int curNum, Set<Integer> setNodeNum, Map<Integer, TreeNode> mapAllLeaf) {
        if (fromTreeNode == null) {
            return null;
        }

        TreeNode curNode = new TreeNode();
        if (fromTreeNode.left == null && fromTreeNode.right == null) {
            setNodeNum.add(curNum);
            mapAllLeaf.put(curNum, curNode);
        }

        curNode.left = deepCopyTreeNode(fromTreeNode.left,curNum*2, setNodeNum, mapAllLeaf);
        curNode.right = deepCopyTreeNode(fromTreeNode.right,curNum * 2 + 1, setNodeNum, mapAllLeaf);

        return curNode;
    }


}
