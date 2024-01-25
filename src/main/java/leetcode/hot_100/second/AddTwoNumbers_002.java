package leetcode.hot_100.second;

import leetcode.ListNode;

/**
 * @author gusixue
 * @description
 * @date 2024/1/25
 */
public class AddTwoNumbers_002 {

    /**
     * Java + 链表
     *
     * 第 1 步：
     * 首先判空，
     * 然后从头开始（低位到高位）将 l2 加到 l1 上，
     * 最后注意 l1 与 l2 长度不同与进位
     *
     * 第 2 步：
     * 如果 l2 更长则将更长的直接加入 l1 后，
     * 如果最后还有进位，则需要新创建 ListNode 放在后面，
     * 注意由于直接判断 l1 == null，此时会丢失 l1 前一个位置的信息，因此需要维护一个 prevL1.next = l1（通用技巧）
     *
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 判空
        if (l1 == null) {
            return l2;

        } else if (l2 == null) {
            return l1;
        }

        // 从头开始将 l2 加到 l1 上，如果 l2 更长则将更长的直接加入 l1 后，注意进位
        ListNode res = l1;
        int carry = 0;

        ListNode prevL1 = new ListNode(0, l1);

        // 如果最后需要进位，则 carry 非 0
        while (l1 != null || l2 != null || carry != 0) {

            if (l1 != null && l2 != null) {
                l1.val += l2.val + carry;
                carry = l1.val / 10;
                l1.val %= 10;

                prevL1 = l1;
                l1 = l1.next;
                l2 = l2.next;

            } else if (l1 != null) {
                l1.val += carry;
                carry = l1.val / 10;
                l1.val %= 10;

                prevL1 = l1;
                l1 = l1.next;

            } else if (l2 != null) {
                l1 = l2;
                prevL1.next = l2;
                l2 = null;

            } else {
                prevL1.next = new ListNode(carry % 10);
                carry /= 10;
            }
        }

        return res;
    }
}
