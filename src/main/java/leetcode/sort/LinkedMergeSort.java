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
            ListNode newHead2 = linkedMergeSort.solutionOptimization(head2);
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
     * 先获取链表长度，然后分割成多组，每组连续俩节点排序，可看做两个有序链表（因为只有一个节点）合并成一个链表，注意最后一组可能不满，
     * 接着连续四个节点排序，同样是两个有序链表合并成一个链表，然后连续八个、十六个...，直到排好序个数大于等于链表长度就完成
     * 注意：链表每次排序后需要嵌回原链表
     * @return 返回排好序的新的头节点
     */
    private ListNode iterationMergeSort(ListNode head) {
        // 获取链表长度
        int len = getLinkedLen(head);
        System.out.println("len:" + len + "\n");

        // 头结点前添加虚拟节点，方便操作
        ListNode virtual = new ListNode(0, head);

        // 循环操作连续两个节点、四个节点、八个节点...
        for (int group = 2; group / 2 < len; group *= 2) {
            // 第一个链表头结点前一个节点
            ListNode left = virtual;
            System.out.println("left:" + left);

            // 每 group 个元素一组，前 group/2 个与后 group/2 个有序链表合并成一个有序链表
            int halfGroup = group / 2;
            for (int now = 0; now < len; now += group) {
                System.out.println(String.format("now:%s group:%s left:%s", now, group, left));
                // 从第 now 位开始，最多到 len 长度，连续 group 个链表进行合并，然后 left 后移到下一组（每组头结点的前一个节点）
                left = mergeContinueLinked(group, halfGroup, now, len, left, left.getNext());
            }
            System.out.println();
        }

        return virtual.getNext();
    }

    /**
     * 获取链表长度
     */
    private int getLinkedLen(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.getNext();
        }
        return len;
    }

    /**
     * 从第 now 位开始，最多到 len 长度，连续 group 个链表进行合并，然后 left 后移
     * @param group 每组个数
     * @param halfGroup 半组长度，其为排好序的长度
     * @param now 当前下标（模拟数组）
     * @param len 链表总长度
     * @param preHead 当前链表起点位置的前一个节点
     * @param lHead 当前链表起点位置
     * @return lHead 接下来移到的位置的前一个节点
     */
    private ListNode mergeContinueLinked(int group, int halfGroup, int now, int len, ListNode preHead, ListNode lHead) {
        // 后续不足 halfGroup 个元素，代表均已排
        if (len <= now + halfGroup) {
            return null;
        }

        // 第一个链表尾结点后一个节点，同时也是第二个链表头结点
        ListNode rHead = moveBack(lHead, halfGroup);

        // 第二个链表尾结点后一个节点
        ListNode rTail = moveBack(rHead, halfGroup);

        System.out.println(String.format("lHead:%s rHead:%s rTail:%s", lHead, rHead, rTail));
        // 合并俩有序链表
        ListNode[] newNodes = mergeTwoSortedLinked2(lHead, rHead, rTail);
        ListNode newHead = newNodes[0];
        ListNode newPreTail = newNodes[1];

        // 合并后的有序链表嵌回原链表
        preHead.setNext(newHead);
        newPreTail.setNext(rTail);
        System.out.println(String.format("preHead:%s newPreTail:%s", preHead, newPreTail) + "\n");

        return newPreTail;
    }

    /**
     * head 开始后移 halfGroup 个元素，其中移动到 null 则不用再移动了
     */
    private ListNode moveBack(ListNode head, int halfGroup) {
        while (head != null && halfGroup > 0) {
            halfGroup--;
            head = head.getNext();
        }
        return head;
    }

    /**
     * 左右俩有序链表合并成一个有序链表，返回新的头尾节点
     * @param lHead 第一个链表头结点
     * @param rHead 第一个链表尾结点后一个节点，同时也是第二个链表的头结点
     * @param rTail 第二个链表尾结点后一个节点
     * @return 新的头尾节点：0-新头结点，1-新尾结点
     */
    private ListNode[] mergeTwoSortedLinked2(ListNode lHead, ListNode rHead, ListNode rTail) {
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
                // 保证排序稳定性
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
}
