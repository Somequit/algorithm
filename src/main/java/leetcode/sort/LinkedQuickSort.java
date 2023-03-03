package leetcode.sort;

import leetcode.ListNode;
import utils.AlgorithmUtils;

import java.util.Random;

/**
 * 链表版快排
 * @author gusixue
 * @date 2023/2/28
 */
public class LinkedQuickSort {

    public static void main(String[] args) {
        LinkedQuickSort linkedQuickSort = new LinkedQuickSort();
        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();

            ListNode head = AlgorithmUtils.arrToListNode(nums);

            ListNode newHead = linkedQuickSort.solution(head);
            System.out.println(newHead);
        }
    }

    /**
     * 链表版快排（升序）
     * 时间复杂度为 O（n*logn）；空间复杂度：O（logn）
     */
    private ListNode solution(ListNode head) {
        // 判空
        if (head == null || head.getNext() == null) {
            return head;
        }

        int len = getLen(head);
        System.out.println(len);

        // 头结点前的虚拟节点，方便处理头接地以及获取最新的头结点
        ListNode virtual = new ListNode(0, head);
        // 快排核心算法
        quickSort(virtual, head, null, len);
        return virtual.getNext();
    }

    private int getLen(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.getNext();
        }
        return len;
    }

    /**
     * 快排核心算法：
     * 先获得并提取随机中心，
     * 然后以中心节点为头，从头到尾循环当前链表，
     * 小于中心的插头，大于中心的插尾，等于中心的插入 leftPivot 和 rightPivot 中间（减少相同的元素的递归）
     * 接着将新的头尾节点连接上原有节点，
     * 最后递归左右俩区间
     * 注意：添加虚拟节点，提取出来的随机节点要最后才指向空，如果新尾结点从没有移动过则需要随着 rightPivot 移动
     *
     * @param preHead 当前链表头结点之前的节点
     * @param head 当前链表头结点（含）
     * @param tail 当前链表尾结点（不含）
     * @param len 节点元素个数
     */
    private void quickSort(ListNode preHead, ListNode head, ListNode tail, int len) {
        if (len <= 1) {
            return;
        }

        // 获得并提取随机中心
        ListNode[] pivot = getPivot(head, len);
        head = pivot[0];
        ListNode rightPivot = pivot[1];
        System.out.println(head + ":" + rightPivot);

        // 从头到尾循环链表，小于中心的插头，大于中心的插尾，等于中心的插入 rightPivot 后面，rightPivot 后移（减少相同的元素的递归）
        ListNode leftPivot = rightPivot;
        ListNode virtual = new ListNode(0, rightPivot);
        ListNode newTail = rightPivot;
        int leftLen = 0;
        int rightLen = 0;

        for (ListNode now = head; now != tail;) {
            ListNode next = now.getNext();

            if (now.getVal() < leftPivot.getVal()) {
                // 头插
                now.setNext(virtual.getNext());
                virtual.setNext(now);
                leftLen++;
            } else if (now.getVal() > leftPivot.getVal()) {
                // 尾插
                now.setNext(newTail.getNext());
                newTail.setNext(now);
                newTail = now;
                rightLen++;
            } else {
                now.setNext(rightPivot.getNext());
                rightPivot.setNext(now);
                rightPivot = now;
                // 如果新尾结点从没有移动过则需要随着 rightPivot 移动
                if (newTail.getNext() == rightPivot) {
                    newTail = rightPivot;
                }
            }

            now = next;
        }

        // 将新的头尾节点连接上原有节点
        newTail.setNext(tail);
        preHead.setNext(virtual.getNext());
        System.out.println("head:" + head + " tail:" + tail + " len:" + len);
        System.out.println("leftPivot:" + leftPivot + " rightPivot:" + rightPivot);
        System.out.println("virtual:" + virtual + " newTail:" + newTail);
        System.out.println("leftLen:" + leftLen + " rightLen:" + rightLen);

        // 递归左右俩区间
        quickSort(preHead, preHead.getNext(), leftPivot, leftLen);
        quickSort(rightPivot, rightPivot.getNext(), newTail.getNext(), rightLen);
    }

    /**
     * 获得并提取随机中心
     * 注意：添加虚拟节点，提取出来的节点要最后才指向空
     * @return 返回提取后的节点数组（0-提取后的新头结点，1-提取出来的节点）
     */
    private ListNode[] getPivot(ListNode head, int len) {
        ListNode[] pivot = new ListNode[2];

        // 添加虚拟节点，方便操作头结点
        ListNode virtual = new ListNode(0, head);
        ListNode pivotNode = virtual;

        int pivotNum = (new Random()).nextInt(len);
        for (int i = 0; i < pivotNum; i++) {
            pivotNode = pivotNode.getNext();
        }

        // 提取随机中心节点
        ListNode temp = pivotNode.getNext();
        pivotNode.setNext(pivotNode.getNext().getNext());
        // 提取出来的节点要最后才指向空
        temp.setNext(null);

        pivot[0] = virtual.getNext();
        pivot[1] = temp;
        return pivot;
    }
}
