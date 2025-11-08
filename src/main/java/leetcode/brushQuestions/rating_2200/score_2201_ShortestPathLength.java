package leetcode.brushQuestions.rating_2200;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author gusixue
 * @description 847. 访问所有节点的最短路径
 * @date 2025/11/3 3:28 下午
 */
public class score_2201_ShortestPathLength {

    /**
     * 枚举起始点，BFS 暴力求结果，每个点压缩到 int 中作去重
     */
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        if (n <= 2) {
            return n - 1;
        }

        // 去重：访问过的点 + 当前点
        Set<Integer> setDed = new HashSet<>();

        // curPoint，curMask, dist
        Queue<int[]> queueBFS = new LinkedList<>();

        // 所有点均可做起点
        for (int start = 0; start < n; start++) {
            int curMask = 1 << start;

            int deduplication = curMask + (1 << (n + start));
            setDed.add(deduplication);

            queueBFS.add(new int[]{start, curMask, 0});
        }

        int finishMask = (1 << n) - 1;

        while (!queueBFS.isEmpty()) {
            int[] curQueue = queueBFS.poll();
            int curPoint = curQueue[0];
            int curMask = curQueue[1];
            int dist = curQueue[2];

            for (int i = 0; i < graph[curPoint].length; i++) {
                int nextPoint = graph[curPoint][i];
                int nextMask = curMask | (1 << nextPoint);

                if (nextMask == finishMask) {
                    return dist + 1;
                }

                // nextMask 不存在则代表此序列未出现过，否则序列步数更小
                int deduplication = nextMask + (1 << (n + nextPoint));
                if (!setDed.contains(deduplication)) {
                    setDed.add(deduplication);
                    queueBFS.add(new int[]{nextPoint, nextMask, dist + 1});
                }
            }
        }

        return Integer.MAX_VALUE;
    }

}
