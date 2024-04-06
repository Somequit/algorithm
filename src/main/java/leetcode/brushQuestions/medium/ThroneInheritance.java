package leetcode.brushQuestions.medium;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author gusixue
 * @description 1600. 王位继承顺序
 * @date 2024/4/7
 */
public class ThroneInheritance {

    // 国王姓名
    private final String rootKingName;

    // 存储父子关系多叉树，先出生的孩子放前面
    private final Map<String, List<String>> mapTreeNode;

    // 死亡集合
    private final Set<String> setDeathName;

    /**
     * Successor(x, curOrder) 本质上是模拟的二叉树的前序遍历，此处使用多叉树，先出生的孩子放前面遍历即可
     */
    public ThroneInheritance(String kingName) {
        this.rootKingName = kingName;

        this.mapTreeNode = new HashMap<>();
        this.mapTreeNode.put(kingName, new ArrayList<>());

        this.setDeathName = new HashSet<>();
    }


    public void birth(String parentName, String childName) {
        this.mapTreeNode.get(parentName).add(childName);
        this.mapTreeNode.put(childName, new ArrayList<>());
    }

    /**
     * 由于死亡不影响 Successor 函数过程，仅仅在最后输出不需要，因此只需要标记此人死亡、不需要在树中删除（避免影响未死亡的子孙节点）
     */
    public void death(String name) {
        this.setDeathName.add(name);
    }

    /**
     * 调用数量较少，因此每次调用直接模拟前序遍历多叉树即可
     */
    public List<String> getInheritanceOrder() {
        List<String> listOriginalOrder = new ArrayList<>();
        recursionPreOrder(this.rootKingName, listOriginalOrder);

        return listOriginalOrder.stream().filter(name -> !setDeathName.contains(name)).collect(Collectors.toList());
    }

    private void recursionPreOrder(String curName, List<String> listOriginalOrder) {
        if (curName == null) {
            return;
        }

        listOriginalOrder.add(curName);
        for (String childName : this.mapTreeNode.get(curName)) {
            recursionPreOrder(childName, listOriginalOrder);
        }
    }
}
