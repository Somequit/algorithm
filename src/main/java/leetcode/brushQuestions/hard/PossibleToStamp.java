package leetcode.brushQuestions.hard;

/**
 * @author gusixue
 * @description 2132. 用邮票贴满网格图
 * @date 2023/12/14
 */
public class PossibleToStamp {

    /**
     * Java + 两次一维前缀和 + 二维差分
     *
     * 第 1 步：
     * 先求出以每个点为左上角，是否可以贴邮票，
     * 接着使用二维差分，将每个可以贴邮票的点 +1，
     * 最后统一更新一遍，查询 贴邮票的点数 + 被占据的点数 是否等于总格子数
     *
     * 第 2 步：
     * 求出以每个点为左上角，是否可以贴邮票：
     *     * 先每行倒序、求出每个点往右最多多少空位 rowCount：空位 rowCount[i][j]=rowCount[i][j+1]+1，否则 rowCount[i][j]=0
     *     * 再每列倒序、求出每个点往下最多有多少个 rowCount >= stampWidth，设置为 rowColCount：大于等于 rowColCount[i][j]=rowColCount[i+1][j]+1，否则 rowColCount[i][j]=0
     *     * 最后求每个点的 rowColCount >= stampHeight，代表可以贴邮票 isStamp[i][j]=true
     *
     * 第 3 步：
     * 顺序遍历 isStamp，如果 true 则将当前点 stampCount[i][j]++、代表右下角均可以贴邮票，
     * 右边 stampCount[i][j+stampWidth]--、下边 stampCount[i+stampHeight][j]--、右下角 stampCount[i+stampHeight][j+stampWidth]++，
     * 以差分的形式处理结果，
     *
     * 第 4 步：
     * 顺序遍历 stampCount，使用差分 DP 的思想，更新 stampCount：
     * stampCount[i][j] += stampCount[i-1][j]+stampCount[i][j-1]-stampCount[i-1][j-1]：代表上边区间 加 左边区间 减 重叠区间，
     * 如果 stampCount[i][j] > 0 代表此点被贴邮票
     * 时间复杂度：O（m*n），空间复杂度：O（m*n）
     */
    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        int m = grid.length;
        int n = grid[0].length;

        // 列 +1 方便处理最右边的点
        int[][] rowCount = new int[m][n + 1];

        // 被占领的数量
        int fillCount = 0;

        // 先每行倒序、求出每个点往右最多多少空位 rowCount：空位 rowCount[i][j]=rowCount[i][j+1]+1，否则 rowCount[i][j]=0
        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 0) {
                    rowCount[i][j] = rowCount[i][j + 1] + 1;


                } else {
                    rowCount[i][j] = 0;
                    fillCount++;
                }
            }
        }

        // 行 +1 方便处理最下边的点
        int[][] rowColCount = new int[m + 1][n];

        // 最后求每个点的 rowColCount >= stampHeight，代表可以贴邮票 isStamp[i][j]=true
        boolean[][] isStamp = new boolean[m][n];

        // 再每列倒序、求出每个点往下最多有多少个 rowCount >= stampWidth，设置为 rowColCount：大于等于 rowColCount[i][j]=rowColCount[i+1][j]+1，否则 rowColCount[i][j]=0
        for (int j = 0; j < n; j++) {
            for (int i = m - 1; i >= 0; i--) {
                if (rowCount[i][j] >= stampWidth) {
                    rowColCount[i][j] = rowColCount[i + 1][j] + 1;

                    if (rowColCount[i][j] >= stampHeight) {
                        isStamp[i][j] = true;
                    }

                } else {
                    rowColCount[i][j] = 0;
                }
            }
        }

        // 行列向右下移动一位，方便处理
        int[][] stampCount = new int[m + 1][n + 1];
        // 顺序遍历 isStamp，如果 true 则以二维差分方式处理
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isStamp[i][j]) {
                    stampCount[i + 1][j + 1]++;

                    if (i + 1 + stampHeight <= m) {
                        stampCount[i + 1 + stampHeight][j + 1]--;
                    }

                    if (j + 1 + stampWidth <= n) {
                        stampCount[i + 1][j + 1 + stampWidth]--;
                    }

                    if (i + 1 + stampHeight <= m && j + 1 + stampWidth <= n) {
                        stampCount[i + 1 + stampHeight][j + 1 + stampWidth]++;
                    }
                }
            }
        }

        int stampCountRes = 0;
        // 顺序遍历 stampCount，使用差分 DP 的思想，更新 stampCount
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                stampCount[i][j] += stampCount[i][j - 1] + stampCount[i - 1][j] - stampCount[i - 1][j - 1];
                if (stampCount[i][j] > 0) {
                    stampCountRes++;
                }
            }
        }

        return fillCount + stampCountRes == m * n;
    }
}
