package leetcode.brushQuestions.hard;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author gusixue
 * @description 407. 接雨水 II
 * @date 2025/10/3 1:55 上午
 */
public class TrapRainWater {

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /**
     * 类似接雨水I的双指针做法，只是换成了三维
     * 先确定最外面一圈无法接雨水且作为墙挡住，然后找到最小值 如(0,1)=10，
     * 然后其相邻点(其实要找四个相邻点，不过其余三个均不合法) (1,1) 接水/不接水后一定不小于最小值 (0,1) 替代其作为墙，
     *     如果 (1,1) 大于等于 10，则它就是新的墙，删除 (0,1) 用 全部相邻点(1,1)替代+外圈挡住 接着找最小值继续操作，
     *     如果 (1,1) 小于 10，则接水到 10，删除 (0,1) 用 全部相邻点(1,1)替代+外圈挡住 接着找最小值继续操作，
     * 直到所有点均清空
     * 可以使用优先队列存储每一个点，弹出最小值操作
     * 时间复杂度：O（mn*log(mn)），空间复杂度：O（mn）
     */
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;
        if (m <= 2 || n <= 2) {
            return 0;
        }

        boolean[][] visit = new boolean[m][n];
        // int[] 第一位存墙的高度
        PriorityQueue<int[]> minWallPriQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    minWallPriQueue.add(new int[]{heightMap[i][j], i, j});
                    visit[i][j] = true;

                }
            }
        }

        int res = 0;
        int[] curMinWall = minWallPriQueue.poll();
        while (curMinWall != null) {
            for (int i = 0; i < DIRS.length; i++) {
                int x = curMinWall[1] + DIRS[i][0];
                int y = curMinWall[2] + DIRS[i][1];
                if (checkIndex(x, y, m, n, visit)) {
                    visit[x][y] = true;

                    if (heightMap[x][y] >= curMinWall[0]) {
                        minWallPriQueue.add(new int[]{heightMap[x][y], x, y});

                    } else {
                        res += curMinWall[0] - heightMap[x][y];
                        minWallPriQueue.add(new int[]{curMinWall[0], x, y});

                    }
                }
            }

            curMinWall = minWallPriQueue.poll();
        }

        return res;
    }

    private boolean checkIndex(int x, int y, int m, int n, boolean[][] visit) {
        if (x >= 0 && x < m && y >= 0 && y < n && !visit[x][y]) {
            return true;
        }

        return false;
    }
}
