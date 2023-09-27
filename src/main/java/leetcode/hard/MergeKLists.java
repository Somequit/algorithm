package leetcode.hard;

import leetcode.ListNode;
import leetcode.brushQuestions.CombinationSum2;
import utils.AlgorithmUtils;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author gusixue
 * @description
 * 23. 合并 K 个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * k == lists.length
 * 0 <= k <= 10 ^ 4
 * 0 <= lists[i].length <= 500
 * -10 ^ 4 <= lists[i][j] <= 10 ^ 4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10 ^ 4
 * @date 2023/9/27
 */
public class MergeKLists {

    public static void main(String[] args) {
        MergeKLists mergeKLists = new MergeKLists();
        while (true) {
            int n = AlgorithmUtils.systemInNumberInt();
            ListNode[] lists = new ListNode[n];
            for (int i = 0; i < n; i++) {
                lists[i] = AlgorithmUtils.arrToListNode(AlgorithmUtils.systemInArray());
            }

            ListNode res = mergeKLists.solution(lists);
            System.out.println(res);
        }
    }

    /**
     * 多指针+小顶堆，将每个链表头指针放入小顶堆，每次弹出最小值放入结果链表中，然后将对应链表的 next（如有）放入小顶堆，直到所有链表都为 null
     * 注意：结果链表可以先添加一个虚拟结点指向头结点，此时头结点和其他节点就可以相同处理
     * n 为所有链表的节点总数，m 为链表数，时间复杂度：O（n*logm），空间复杂度：O（n）
     * @param lists
     * @return
     */
    public ListNode solution(ListNode[] lists) {
        // 判空
        if (lists == null || lists.length <= 0) {
            return null;
        }

        // 添加虚拟结点指向结果头结点
        ListNode virtualNode = new ListNode();

        // 将所有链表的头结点（如有）放入小顶堆
        Queue<ListNode> leastNodeHeap = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for (ListNode list : lists) {
            if (list != null) {
                leastNodeHeap.offer(list);
            }
        }

        ListNode curNode = virtualNode;
        // 弹出最小值放入结果链表中
        while (!leastNodeHeap.isEmpty()) {
            ListNode nodeTemp = leastNodeHeap.poll();
            curNode.next = nodeTemp;
            curNode = curNode.next;

            // 将对应链表的 next（如有）放入小顶堆，直到所有链表都为 null
            if (nodeTemp.next != null) {
                leastNodeHeap.offer(nodeTemp.next);
            }
        }

        return virtualNode.next;
    }
}
