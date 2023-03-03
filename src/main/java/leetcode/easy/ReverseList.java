package leetcode.easy;

import leetcode.ListNode;
import utils.AlgorithmUtils;

/**
 * 206. 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * 链表中节点的数目范围是 [0, 5000]
 * -5000 <= Node.val <= 5000
 * 链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
 * @author gusixue
 * @date 2023/2/22
 */
public class ReverseList {

    public static void main(String[] args) {
        while (true) {
            int[] arrs = AlgorithmUtils.systemInArray();
//            System.out.println(head);

            ReverseList reverseList = new ReverseList();

            ListNode head = AlgorithmUtils.arrToListNode(arrs);
            ListNode res = reverseList.solution(head);
            System.out.println(res);

            ListNode head2 = AlgorithmUtils.arrToListNode(arrs);
            ListNode res2 = reverseList.solution2(head2);
            System.out.println(res2);

        }
    }

    /**
     * 迭代反转单链表
     * （虚拟）新开一个链表头结点 null，原节点暂存二个节点，然后头结点指向新开节点的头结点，原头结点变成第二个节点，循环直到源链表为空
     * 举例：
     * 1->2->3->4->n，n
     * 2->3->4->n，1->n
     * 3->4->n，2->1->n
     * 4->n，3->2->1->n
     * 4->3->2->1->n
     * @return
     */
    private ListNode solution(ListNode head) {
        // 判空或者一个节点
        if (head == null || head.getNext() == null) {
            return head;
        }

        ListNode virtual = null;

        while (head != null) {
            ListNode second = head.getNext();
            head.setNext(virtual);
            virtual = head;
            head = second;
        }

        return virtual;
    }

    /**
     * 递归反转单链表
     * 递归的下一层是下一个节点，直到尾结点返回当前节点（这是最新头结点），回溯后当前节点的 next 指向当前节点，当前节点指向空，返回新头结点
     * 举例：
     * 1->2->3->4->n，当前节点：3
     * 4->3，1->2->3->n，当前节点：2
     * 4->3->2，1->2->n，当前节点：1
     * 4->3->2->1，1->n
     * @param head
     * @return
     */
    private ListNode solution2(ListNode head) {
        return recursionReverse(head);
    }

    private ListNode recursionReverse(ListNode head) {
        if (head == null || head.getNext() == null) {
            return head;
        }

        ListNode newHead = recursionReverse(head.getNext());

        head.getNext().setNext(head);
        head.setNext(null);

        return newHead;
    }


}
