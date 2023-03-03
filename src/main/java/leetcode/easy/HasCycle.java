package leetcode.easy;

import lombok.ToString;
import utils.AlgorithmUtils;

/**
 * 给你一个链表的头节点 head ，判断链表中是否有环。如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * @author gusixue
 * @date 2023/2/17
 */
public class HasCycle {

    @ToString
    public class ListNode {
        int val;
        HasCycle.ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, HasCycle.ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {
        while (true) {
            int[] listArr = AlgorithmUtils.systemInArray();
            // 表示链表尾连接到链表中的位置（索引从 0 开始）。-1 代表没有环
            int pos = AlgorithmUtils.systemInNumberInt();
            HasCycle hasCycle = new HasCycle();

            ListNode head = hasCycle.createLinked(listArr, pos);
            ListNode temp = head;
            for (int i = 0; i < 10 && temp != null; i++) {
                System.out.println(temp.val);
                temp = temp.next;
            }

            boolean res = hasCycle.solution(head);
            System.out.println(res);
        }
    }

    private ListNode createLinked(int[] listArr, int pos) {
        if (listArr == null || listArr.length == 0) {
            return null;
        }

        // 添加一个虚拟头结点，指向头结点
        ListNode virtual = new ListNode(-1, null);
        ListNode tail = virtual;
        ListNode cycle = null;

        for (int i = 0; i < listArr.length; i++) {
            tail.next = new ListNode(listArr[i]);

            if (pos >= 0 && pos < listArr.length) {
                if (i == pos) {
                    cycle = tail.next;
                }
                if (i == listArr.length - 1) {
                    tail.next.next = cycle;
                }
            }

            tail = tail.next;
        }


        return virtual.next;
    }

    /**
     * 使用快慢指针解决，慢指针每次走一步，快指针每次走两步，任一指针走到 null 则结束代表无环，否则快慢指针相遇则结束代表右环
     * @param head
     * @return
     */
    private boolean solution(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode fastNode = head.next;
        ListNode slowNode = head;

        while (fastNode != slowNode) {
            if (fastNode == null || fastNode.next == null) {
                return false;
            }

            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }

        return true;
    }
}
