package leetcode.contest.contest_358;

import leetcode.ListNode;
import utils.AlgorithmUtils;


/**
 *
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
            ListNode head = null;

            ListNode res = contest.solution(head);
            System.out.println(res);
        }

    }

    public ListNode solution(ListNode head) {
        int carry = recursion(head);
        if (carry > 0) {
            ListNode virtualHead = new ListNode(carry, head);
            head = virtualHead;
        }

        return head;
    }

    private int recursion(ListNode curNode) {
        if (curNode == null) {
            return 0;
        }

        int carry = recursion(curNode.next);

        int curVal = curNode.val * 2 + carry;
        curNode.val = curVal % 10;

        return curVal / 10;
    }


}
