package leetcode.contest.double_121;

import java.util.*;
/**
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

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
    public int minimumOperationsToMakeEqual(int x, int y) {
        if (x <= y) {
            return y - x;
        }

        int max = x + 10;
        int[] vis = new int[max + 1];
        int res = 0;
        Queue<int[]> queue = new LinkedList<>();

        vis[x] = 1;
        queue.offer(new int[]{x, 0});

        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            if (temp[0] == y) {
                res = temp[1];
                break;
            }

            int next;

            if (temp[0] > y) {
                next = temp[0] - 1;
                offerNext(queue, next, temp[1] + 1, vis);

                if (temp[0] % 5 == 0) {
                    next = temp[0] / 5;
                    offerNext(queue, next, temp[1] + 1, vis);
                }

                if (temp[0] % 11 == 0) {
                    next = temp[0] / 11;
                    offerNext(queue, next, temp[1] + 1, vis);
                }
            }

            if (temp[0] < max) {
                next = temp[0] + 1;
                offerNext(queue, next, temp[1] + 1, vis);
            }

        }

        return res;
    }

    private void offerNext(Queue<int[]> queue, int next, int count, int[] vis) {
        if (vis[next] == 0) {
            vis[next] = 1;
            queue.offer(new int[]{next, count});
        }
    }


}
