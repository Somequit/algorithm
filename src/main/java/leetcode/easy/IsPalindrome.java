package leetcode.easy;

import leetcode.ListNode;
import utils.AlgorithmUtils;

/**
 * 234. 回文链表
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false
 * 链表中节点数目在范围[1, 10^5] 内
 * 0 <= Node.val <= 9
 * 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * @author gusixue
 * @date 2023/3/14
 */
public class IsPalindrome {

    public static void main(String[] args) {
        IsPalindrome isPalindrome = new IsPalindrome();

        while (true) {

            int[] nums = AlgorithmUtils.systemInArray();
            ListNode head = AlgorithmUtils.arrToListNode(nums);

            boolean res = isPalindrome.solution(head);
            System.out.println(res);
        }
    }

    /**
     * 快慢指针获取中心节点（偶数则偏前一个），
     * 中心节点后一个节点开始反转链表、拆成两个链表，
     * 两个链表均从头开始比较相同的长度（如果链表为奇数，那第一个链表会长一点但是不比较是否相同），
     * 完成后将链表反转回来
     * 时间复杂度：O(n) ，空间复杂度：O(1)
     */
    private boolean solution(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        // 快慢指针获取中心节点（偶数则偏前一个）
        ListNode midHead = getMidNode(head);
//        System.out.println("midHead:" + midHead);

        // 反转链表
        ListNode rightHead = reverseLinked(midHead.next);
        midHead.next = null;
//        System.out.println("rightHead:" + rightHead);


        // 两个链表从头开始比较相同的长度
        boolean res = compareLinked(head, rightHead);

        // 链表反转回来
        midHead.next = reverseLinked(rightHead);
//        System.out.println("head:" + head);

        return res;
    }

    private boolean compareLinked(ListNode left, ListNode right) {
        boolean res = true;

        while (left != null && right != null) {
            if (left.val != right.val) {
                res = false;
                break;
            }
            left = left.next;
            right = right.next;
        }

        return res;
    }

    /**
     * 反转链表
     * @return 返回新头结点
     */
    private ListNode reverseLinked(ListNode head) {
        ListNode curr = head.next;
        head.next = null;

        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = head;
            head = curr;
            curr = nextNode;
        }

        return head;
    }

    private ListNode getMidNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = slow;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
