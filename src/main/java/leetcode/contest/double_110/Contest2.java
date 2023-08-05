package leetcode.contest.double_110;

import leetcode.ListNode;
import utils.AlgorithmUtils;


/**
 * @author gusixue
 * @date 2023/7/22
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
            // todo
            ListNode head = null;

            ListNode res = contest.solution(head);
            System.out.println(res);
        }

    }

    public ListNode solution(ListNode head) {
        ListNode curNode = head;
        ListNode nextNode = curNode.next;

        while (nextNode != null) {
            int gcdVal = gcd(curNode.val, nextNode.val);
            ListNode gcdNode = new ListNode(gcdVal, nextNode);
            curNode.next = gcdNode;

            curNode = nextNode;
            nextNode = nextNode.next;
        }

        return head;
    }

    private int gcd(int a,int b){
        return (a == 0 ? b : gcd(b % a, a));
    }

}
