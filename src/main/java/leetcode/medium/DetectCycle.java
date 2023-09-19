package leetcode.medium;

import leetcode.ListNode;
import utils.AlgorithmUtils;

import java.util.List;

/**
 * @author gusixue
 * @description
 * 142. 环形链表 II
 * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 链表中节点的数目范围在范围 [0, 10 ^ 4] 内
 * -10 ^ 5 <= Node.val <= 10 ^ 5
 * pos 的值为 -1 或者链表中的一个有效索引
 * @date 2023/9/19
 */
public class DetectCycle {

    public static void main(String[] args) {
        DetectCycle detectCycle = new DetectCycle();
        while (true) {
            ListNode head = AlgorithmUtils.arrToListNode(AlgorithmUtils.systemInArray());
            int pos = AlgorithmUtils.systemInNumberInt();
            if (pos != -1) {
                ListNode last = head;
                ListNode cycleStart = null;
                for (int i = 0; last.next != null; last = last.next, i++) {
                    if (i == pos) {
                        cycleStart = last;
                    }
                }
                System.out.println(cycleStart + " : " + last);
                last.next = cycleStart;
            }

            ListNode res = detectCycle.solution(head);
            System.out.println(res.val);
        }
    }

    /**
     * 先判空与单个无环节点，然后使用快满指针判断是否有环，如果快指针遇到 null 则无环，否则一定有环，
     * 假设环外有 a 个节点、环内有 b 个节点，考虑快指针走了慢指针两倍距离，同时由于他俩必须在环内才能相遇，同时快指针一定比慢指针多走 nb 个节点（快指针会套慢指针 n 圈），
     * 因此 f=2s、f-s=nb 可得：2s-s=nb、s=nb，即慢指针也走了 n 圈环，可是我们需要找到环入口，因此考虑 nb+a 就会走到入口、同时 0+a 也走到入口（此时俩指针第一次相遇），
     * 此时从头结点再建一个新指针 h，它与慢指针 s 一起走 a 步就会第一次相遇，此时就是环入口
     * @param head
     * @return
     */
    private ListNode solution(ListNode head) {
        // 判空与单个无环节点
        if (head == null || head.next == null) {
            return null;
        }

        // 头结点前加虚拟节点方便处理
        ListNode virtualNode = new ListNode(0, head);

        // 快慢指针，先判断是否有环
        ListNode resNode = virtualNode;
        ListNode slow = virtualNode.next;
        ListNode fast = virtualNode.next.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                resNode = null;
                break;
            }

            slow = slow.next;
            fast = fast.next.next;
        }
        if (resNode == null) {
            return resNode;
        }

        // 双指针找环入口
        while (resNode != slow) {
            resNode = resNode.next;
            slow = slow.next;
        }

        return resNode;
    }
}
