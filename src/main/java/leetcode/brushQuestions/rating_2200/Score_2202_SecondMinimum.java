package leetcode.brushQuestions.rating_2200;

import java.util.*;

/**
 * @author gusixue
 * @description 2045. 到达目的地的第二短时间
 * @date 2025/11/8 9:05 上午
 */
public class Score_2202_SecondMinimum {

    /**
     * 首先每条边均走 time 时间，因此可先将时间看做 1 作为走了多少次、且不计算 change，最后再根据走的次数与 change 计算时间
     * 因为最短走到 n 后再走一对来回变成多两次，因此主要是看是否可以比最短多 1 次，因此 BFS 每个点只能在最短的 t1次 以及可能出现的 t1+1次 到达，否则均不能到达
     * 最后根据次数求时间，遍历到每个点的时间然后：curTime / change = 奇数 -> curTime += change - curTime % change
     */
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0] - 1;
            int v = edge[1] - 1;
            graph[u].add(v);
            graph[v].add(u);
        }

        int secondMiniCnt = doX(n, graph);
//        System.out.println(secondMiniCnt);

        int res = 0;
        for (int i = 0; i < secondMiniCnt - 1; i++) {
            res += time;
            if ((res / change) % 2 == 1) {
                res += change - res % change;
            }
        }

        return res + time;
    }

    private int doX(int n, List<Integer>[] graph) {
        int[] visTimeCount = new int[n];

        Queue<int[]> queuePointCnt = new LinkedList<>();
        queuePointCnt.offer(new int[]{0, 0});
        visTimeCount[0] = 0;

        int res = 0;
        while (!queuePointCnt.isEmpty()) {
            int[] curQueue = queuePointCnt.poll();
            int curPoint = curQueue[0];
            int curCnt = curQueue[1];


            for (int nextPoint : graph[curPoint]) {
                int nextCnt = curCnt + 1;
                if (visTimeCount[nextPoint] == 0) {
                    if (nextPoint == n - 1) {
                        res = nextCnt;
                    }

                    queuePointCnt.offer(new int[]{nextPoint, nextCnt});
                    visTimeCount[nextPoint] = nextCnt;

                } else if (nextCnt == visTimeCount[nextPoint] + 1) {
                    if (nextPoint == n - 1) {
                        return nextCnt;
                    }

                    queuePointCnt.offer(new int[]{nextPoint, nextCnt});
                }
            }
        }

        return res + 2;
    }
}
