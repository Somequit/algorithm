package leetcode;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 链表
 * @author gusixue
 * @date 2023/2/22
 */
@Getter
@Setter
@ToString
public class ListNode {

    int val;
    ListNode next;

    public ListNode() {}

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
