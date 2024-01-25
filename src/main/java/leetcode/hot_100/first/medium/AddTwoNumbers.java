package leetcode.hot_100.first.medium;

import leetcode.ListNode;
import utils.AlgorithmUtils;

/**
 * @author gusixue
 * @description
 * 2. 两数相加
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 * @date 2023/11/12
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();

        while (true) {
            ListNode l1 = AlgorithmUtils.arrToListNode(AlgorithmUtils.systemInArray());
            ListNode l2 = AlgorithmUtils.arrToListNode(AlgorithmUtils.systemInArray());

            ListNode res = addTwoNumbers.solution(l1, l2);
            System.out.println(res);
        }

    }

    /**
     * 遍历 l1、l2，将 l1、l2 与进位加入新加的 ListNode
     */
    private ListNode solution(ListNode l1, ListNode l2) {
        // 判空
        if (l1 == null && l2 == null) {
            return null;

        } else if (l1 == null) {
            return l2;

        } else if (l2 == null) {
            return l1;
        }

        // 结果节点添加新的虚拟节点方便处理
        ListNode virtualNode = new ListNode();
        ListNode curNode = virtualNode;
        int carry = 0;

        while (l1 != null || l2 != null || carry > 0) {
            int sum = carry + (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val);
            carry = sum / 10;

            curNode.next = new ListNode(sum % 10);
            curNode = curNode.next;

            l1 = (l1 == null ? null : l1.next);
            l2 = (l2 == null ? null : l2.next);
        }

        return virtualNode.next;
    }
}
