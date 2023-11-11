package leetcode.hot_100.easy;

import lombok.ToString;
import utils.AlgorithmUtils;

/**
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * @author gusixue
 * @date 2023/1/21
 */
public class MergeTwoLists {

    @ToString
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {
        int[] arr1 = AlgorithmUtils.systemInArray();
        int[] arr2 = AlgorithmUtils.systemInArray();

        MergeTwoLists mergeTwoLists = new MergeTwoLists();
        ListNode list1 = mergeTwoLists.arrayToList(arr1);
        ListNode list2 = mergeTwoLists.arrayToList(arr2);

        ListNode res = mergeTwoLists.solution(list1, list2);
        System.out.println(res);
    }

    private ListNode arrayToList(int[] arr) {
        // 为了方便编写，这儿是头结点的前一个虚拟节点
        ListNode head = new ListNode();
        ListNode prev = head;
        for(int a : arr) {
            prev.next = new ListNode(a);
            prev = prev.next;
        }
        return head.next;
    }

    private ListNode solution(ListNode list1, ListNode list2) {
        System.out.println(list1);
        System.out.println(list2);

        // 为了方便编写，这儿是头结点的前一个虚拟节点
        ListNode resFront = new ListNode();
        ListNode resPrev = resFront;
        ListNode tail1 = list1;
        ListNode tail2 = list2;

        while (tail1 != null && tail2 != null) {
            if (tail1.val > tail2.val) {
                resPrev.next = tail2;
                tail2 = tail2.next;
            } else {
                resPrev.next = tail1;
                tail1 = tail1.next;
            }
            resPrev = resPrev.next;
        }

        resPrev.next = tail1 == null ? tail2 : tail1;

        return resFront.next;
    }
}
