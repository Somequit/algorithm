package leetcode.brushQuestions.medium;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author gusixue
 * @description 417. 太平洋大西洋水流问题
 * @date 2025/10/5 11:57 上午
 */
public class PacificAtlantic {

    private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     * 从四个边缘进行 BFS 反向移动，即从 （i1,j1） 移动到 （i2,j2） 时 heights[i1][j1] <= heights[i2][j2]，
     * 移动的每个点代表其可以流到对应的海里面，最后遍历一遍找到哪些点既可流向太平洋也可流向大西洋，
     * 实现的时候第二次 BFS 时可以边移动边判断时候是结果
     * 时间复杂度：O（m*n），空间复杂度：O（m*n）
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;

        boolean[][] visitUpLeft = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        // 上方与左方入队，代表可流到"太平洋"
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    queue.add(new int[]{i, j});
                    visitUpLeft[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] point = queue.poll();

            for (int[] dirs : DIRS) {
                int x = dirs[0] + point[0];
                int y = dirs[1] + point[1];

                if (x >= 0 && x < m && y >= 0 && y < n && heights[x][y] >= heights[point[0]][point[1]] && !visitUpLeft[x][y]) {
                    visitUpLeft[x][y] = true;
                    queue.add(new int[]{x, y});
                }
            }
        }

        boolean[][] visitDownRight = new boolean[m][n];
        // 下方与右方入队，代表可流到"大西洋"
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == m - 1 || j == n - 1) {
                    queue.add(new int[]{i, j});
                    visitDownRight[i][j] = true;
                }
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            if (visitUpLeft[point[0]][point[1]]) {
                res.add(Arrays.stream(point).boxed().collect(Collectors.toList()));
            }

            for (int[] dirs : DIRS) {
                int x = dirs[0] + point[0];
                int y = dirs[1] + point[1];

                if (x >= 0 && x < m && y >= 0 && y < n && heights[x][y] >= heights[point[0]][point[1]] && !visitDownRight[x][y]) {
                    visitDownRight[x][y] = true;
                    queue.add(new int[]{x, y});
                }
            }
        }

        return res;
    }
}
