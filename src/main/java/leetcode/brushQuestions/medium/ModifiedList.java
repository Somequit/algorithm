package leetcode.brushQuestions.medium;

import leetcode.ListNode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author gusixue
 * @description 3217. 从链表中移除在数组中存在的节点
 * @date 2025/11/1 3:55 下午
 */
public class ModifiedList {

    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> numSet = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        ListNode curNode = head;
        ListNode prevNode = new ListNode();
        // head 有可能换掉
        ListNode firstNode = prevNode;
        while (curNode != null) {
            if (!numSet.contains(curNode.val)) {
                prevNode.next = curNode;
                prevNode = prevNode.next;
            }

            curNode = curNode.next;
        }
        prevNode.next = null;

        return firstNode.next;
    }
}
