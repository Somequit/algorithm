package leetcode.sort;

import leetcode.ListNode;
import utils.AlgorithmUtils;

/**
 * 链表版二路归并排序
 * @author gusixue
 * @date 2023/3/5
 */
public class LinkedMergeSort {

    public static void main(String[] args) {
        LinkedMergeSort linkedMergeSort = new LinkedMergeSort();
        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();

            ListNode head = AlgorithmUtils.arrToListNode(nums);
            ListNode newHead = linkedMergeSort.solution(head);
            System.out.println(newHead);

            ListNode head2 = AlgorithmUtils.arrToListNode(nums);
            ListNode newHead2 = linkedMergeSort.solutionOptimization(head);
            System.out.println(newHead2);
        }
    }

    /**
     * 链表版二路归并排序（升序，递归版自顶向下），稳定排序
     * 时间复杂度为 O（n*logn）；空间复杂度：递归栈的深度 O（logn）
     */
    private ListNode solution(ListNode head) {
        // 判空
        if (head == null || head.getNext() == null) {
            return head;
        }

        // 添加虚拟节点指向头结点，方便处理
        ListNode virtual = new ListNode(0, head);

        // 递归版归并排序核心算法
        recursionMergeSort(virtual, head, null);
        return virtual.getNext();
    }

    /**
     * 首先用快慢指针找到中心节点，以中心节点为左链表最后节点、递归左右俩链表，直到链表长度小于等于 1 回溯，
     * 回溯时是将俩有序链表合并成一个有序链表，合并后将头尾嵌回原链表，然后继续回溯
     * 注意：不能以 null 位结尾需要判断 tail 为结尾，最后要将排序好的链表头尾嵌回原链表，注意它是稳定排序处理
     * 特别注意：由于回溯合并链表后，老的 head 位置改变，因此再次回溯决不能使用 head 判断，而是使用 virtual.next 才是正确头结点
     * @param virtual 当前链表头结点前一个节点
     * @param head 当前链表头结点
     * @param tail 当前链表尾结点后一个节点
     */
    private void recursionMergeSort(ListNode virtual, ListNode head, ListNode tail) {
        // 小于等于 1 个节点回溯，注意结尾为 tail
        if (head == tail || head.getNext() == tail) {
            return;
        }

        // 快慢指针找到中心节点
        ListNode mid = getMidNode(virtual, tail);
//        System.out.println("head:" + head);
//        System.out.println("mid:" + mid);
//        System.out.println("tail:" + tail + "\n");

        // 递归左右俩链表
        recursionMergeSort(virtual, head, mid.getNext());
        recursionMergeSort(mid, mid.getNext(), tail);

        // 左右俩有序链表合并成一个有序链表，返回新的头尾节点
//        System.out.println("head:" + head);
//        System.out.println("mid:" + mid);
//        System.out.println("tail:" + tail + "\n");
        // 注意不能用使用回溯的 head
        ListNode[] newNodes = mergeTwoSortedLinked(virtual.getNext(), mid.getNext(), tail);
        ListNode newHead = newNodes[0];
        ListNode newPreTail = newNodes[1];

        // 头尾节点嵌回原链表
        virtual.setNext(newHead);
        newPreTail.setNext(tail);
//        System.out.println("virtual:" + virtual);
//        System.out.println("newHead:" + newHead);
//        System.out.println("newPreTail:" + newPreTail + "\n");
    }

    /**
     * 左右俩有序链表合并成一个有序链表，返回新的头尾节点
     * @param lHead 第一个链表头结点
     * @param rHead 第一个链表尾结点后一个节点，同时也是第二个链表的头结点
     * @param rTail 第二个链表尾结点后一个节点
     * @return 新的头尾节点：0-新头结点，1-新尾结点
     */
    private ListNode[] mergeTwoSortedLinked(ListNode lHead, ListNode rHead, ListNode rTail) {
        ListNode newVirtual = new ListNode();
        ListNode newPreTail = newVirtual;

        ListNode l = lHead;
        ListNode r = rHead;

        while (l != rHead || r != rTail) {
            if (l == rHead) {
                newPreTail.setNext(r);
                newPreTail = r;
                r = r.getNext();
            } else if (r == rTail) {
                newPreTail.setNext(l);
                newPreTail = l;
                l = l.getNext();
                // 注意稳定排序规则
            } else if (l.getVal() <= r.getVal()) {
                newPreTail.setNext(l);
                newPreTail = l;
                l = l.getNext();
            } else {
                newPreTail.setNext(r);
                newPreTail = r;
                r = r.getNext();
            }
        }


        ListNode[] newNodes = new ListNode[2];
        newNodes[0] = newVirtual.getNext();
        newNodes[1] = newPreTail;
        return newNodes;
    }

    /**
     * 快慢指针找到中心节点
     * @param virtual 头结点前一个节点
     * @param tail 尾结点后一个节点
     * @return 中心节点
     */
    private ListNode getMidNode(ListNode virtual, ListNode tail) {
        ListNode slow = virtual;
        ListNode fast = virtual;

        while (fast != tail && fast.getNext() != tail) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        return slow;
    }

    /**
     * 链表版二路归并排序（升序，迭代版自底向上），稳定排序
     * 时间复杂度为 O（n*logn）；空间复杂度：O（1）
     */
    private ListNode solutionOptimization(ListNode head) {
        // 判空
        if (head == null || head.getNext() == null) {
            return head;
        }

        // 迭代版二路归并核心算法
        return iterationMergeSort(head);
    }

    /**
     *
     * @return 返回排好序的新的头节点
     */
    private ListNode iterationMergeSort(ListNode head) {
        return null;
    }
}
