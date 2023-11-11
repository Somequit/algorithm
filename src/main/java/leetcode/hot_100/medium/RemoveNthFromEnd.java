package leetcode.hot_100.medium;

import utils.AlgorithmUtils;

/**
 * 19. 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 你能尝试使用一趟扫描实现吗？
 *
 * 特别地，在某些语言中，由于需要自行对内存进行管理。因此在实际的面试中，对于「是否需要释放被删除节点对应的空间」这一问题，我们需要和面试官进行积极的沟通以达成一致。
 *
 */
public class RemoveNthFromEnd {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        RemoveNthFromEnd removeNthFromEnd = new RemoveNthFromEnd();
        while (true) {
            int[] vals = AlgorithmUtils.systemInArray();
            // 创建链表，返回头结点
            ListNode headNode = createListNode(vals);
            int n = AlgorithmUtils.systemInNumberInt();

            // 相隔 n 个节点的两个指针处理
            ListNode resultNode = removeNthFromEnd.solution(headNode, n);

            // 输出整个链表
            systemOutListNode(resultNode);
        }
    }

    public ListNode solution(ListNode headNode, int n) {
        // 参数校验
        if (headNode == null || n < 1) {
            return null;
        }

        // 创建一个"虚拟"的头指针指向头结点方便处理
        ListNode virtualNode = new ListNode(0, headNode);

        // 首指针先遍历，走 n 步
        ListNode firstNode = virtualNode;
        for (int i = 0; i < n ;i++) {
            // 如果未到 n 步到达最后一个节点，则不能获得结果、返回 null
            if (firstNode.next != null) {
                firstNode = firstNode.next;
            } else {
                System.out.println(String.format("删除链表倒数第n个 %s:太大了", n));
                return null;
            }
        }

        ListNode tailNode = virtualNode;
        // 如果删除的不是头结点，则与尾指针同时遍历
        while (firstNode.next != null) {
            firstNode = firstNode.next;
            tailNode = tailNode.next;
        }
        System.out.println(String.format("删除链表倒数第n个 first:%s tail:%s tailNext:%s", firstNode.val, tailNode.val, tailNode.next.val));

        // 当首指针达到最后一个节点，尾结点同步完成，此时删除尾结点后一个节点
        deleteNextNode(tailNode);

        return virtualNode.next;
    }

    /**
     * 清理下一个节点
     */
    private static void deleteNextNode(ListNode listNode) {
        if (listNode == null || listNode.next == null) {
            System.out.println("删除链表倒数第n个 清理下一个节点失败 listNode:" + listNode.val);
            return;
        }
        ListNode listNext = listNode.next;
        listNode.next = listNext.next;
        listNext.next = null;
    }

    /**
     * 创建链表，返回头结点
     * @return 头结点
     */
    private static ListNode createListNode(int[] vals) {
        ListNode headNode = null;
        ListNode lastNode = null;
        for (int val : vals) {
            ListNode node = new ListNode(val, null);

            if (lastNode != null) {
                lastNode.next = node;
            } else {
                headNode = node;
            }
            lastNode = node;
        }

        return headNode;
    }

    /**
     * 以 node 开始输出整个链表
     */
    private static void systemOutListNode(ListNode node) {
        if (node == null) {
            System.out.println("删除链表倒数第n个 空链表");
            return;
        }

        System.out.print("删除链表倒数第n个 非空链表");
        while (node != null) {
            System.out.print(" " + node.val);
            node = node.next;
        }
        System.out.println();
    }

}
