package leetcode.brushQuestions.easy;


import leetcode.ListNode;

/**
 * 83. 删除排序链表中的重复元素
 */
public class DeleteDuplicates {

    public static void main(String[] args) {
        DeleteDuplicates contest = new DeleteDuplicates();

        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

//            int res = contest.solution(n, nums, list);
//            System.out.println(res);
        }

    }

    /**
     * @return
     */
    private ListNode solution(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode curNode = head;
        while (curNode.next != null) {
            if (curNode.val == curNode.next.val) {
                curNode.next = curNode.next.next;

            } else {
                curNode = curNode.next;
            }
        }

        return head;
    }


}
