package leetcode.hot_100.first.easy;

import lombok.ToString;
import utils.AlgorithmUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 160. 相交链表
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。
 * 如果两个链表不存在相交节点，返回 null 。
 * 题目数据 保证 整个链式结构中不存在环。
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 * @author gusixue
 * @date 2023/2/19
 */
public class GetIntersectionNode {

    @ToString
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {
        while (true) {
            // 保证俩数组一定有数据
            int[] listArrA = AlgorithmUtils.systemInArray();
            int[] listArrB = AlgorithmUtils.systemInArray();
            int intersectVal = AlgorithmUtils.systemInNumberInt();
            int skipA = AlgorithmUtils.systemInNumberInt();
            int skipB = AlgorithmUtils.systemInNumberInt();
            GetIntersectionNode getIntersectionNode = new GetIntersectionNode();

            ListNode[] head = getIntersectionNode.createLinked(listArrA, listArrB, intersectVal, skipA, skipB);
            ListNode headA = head[0];
            ListNode headB = head[1];
            System.out.println(headA);
            System.out.println(headB);

            ListNode res = getIntersectionNode.solution(headA, headB);
            System.out.println(res);
            ListNode res2 = getIntersectionNode.optimization(headA, headB);
            System.out.println(res2);
        }
    }

    private ListNode[] createLinked(int[] listArrA, int[] listArrB, int intersectVal, int skipA, int skipB) {
        ListNode[] res = new ListNode[2];

        ListNode intersectNode = null;
        ListNode virtualA = new ListNode();
        ListNode tail = virtualA;
        for (int i = 0; i < listArrA.length; i++) {
            tail.next = new ListNode(listArrA[i]);
            tail = tail.next;
            if (skipA == i) {
                intersectNode = tail;
            }
        }
        res[0] = virtualA.next;

        ListNode virtualB = new ListNode();
        tail = virtualB;
        for (int i = 0; i < listArrB.length; i++) {
            if (skipB == i) {
                tail.next = intersectNode;
                break;
            }
            tail.next = new ListNode(listArrB[i]);
            tail = tail.next;
        }
        res[1] = virtualB.next;

        return res;
    }

    /**
     * 当不存在环的时候，循环俩链表时，当某个节点第一次出现重复即是相交节点
     * 将 A 链表的所有节点存储在 Set 集合中，然后循环 B 链表，发现某个节点存在集合中，即为相交节点
     * @param headA
     * @param headB
     * @return
     */
    private ListNode solution(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode res = null;
        Set<ListNode> nodeSet = new HashSet<>();
        while (headA != null) {
            nodeSet.add(headA);
            headA = headA.next;
        }

        while (headB != null) {
            if (nodeSet.contains(headB)) {
                res = headB;
                break;
            }
            headB = headB.next;
        }

        return res;
    }

    /**
     * 优化：如果有相交则从尾到相交点、俩链表均相同，同时如果俩链表节点个数一样，则同时循环一定会走到第一个相交点
     * 首先求出俩链表节点个数，然后节点多的链表向后移动到短链表节点相同的位置，同时循环直到找到第一个相同的节点
     * 空间复杂度可以优化为 O(1)
     */
    private ListNode optimization(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        // 获取链表节点个数
        int lenA = getNodeLen(headA);
        int lenB = getNodeLen(headB);

        // 长链表移动到与短链表相同位置
        if (lenA > lenB) {
            headA = moveListNode(headA, lenA - lenB);
        } else if (lenB > lenA) {
            headB = moveListNode(headB, lenB - lenA);
        }

        // 俩链表同时移动，查询相同节点
        ListNode intersection = null;
        while (headA != null && headB != null) {
            if (headA == headB) {
                intersection = headA;
                break;
            }
            headA = headA.next;
            headB = headB.next;
        }

        return intersection;
    }

    private ListNode moveListNode(ListNode head, int move) {
        for (int i = 0; head != null && i < move; i++) {
            head = head.next;
        }
        return head;
    }

    private int getNodeLen(ListNode head) {
        int res = 0;
        while (head != null) {
            res++;
            head = head.next;
        }
        return res;
    }


}
