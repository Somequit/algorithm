package leetcode.brushQuestions.medium;

/**
 * @author gusixue
 * @description 3531. 统计被覆盖的建筑
 * @date 2025/12/11 9:29 下午
 */
public class CountCoveredBuildings {

    /**
     * 每行最小值最大值、每列最小值最大值均不被包含，其他点一定被包含
     */
    public int countCoveredBuildings(int n, int[][] buildings) {
        // 最小值，最大值
        int[][] rowNum = new int[n][2];
        int[][] colNum = new int[n][2];
        for (int i = 0; i < n; i++) {
            rowNum[i] = new int[]{n, -1};
            colNum[i] = new int[]{n, -1};
        }

        for (int[] build : buildings) {
            int x = build[0] - 1;
            int y = build[1] - 1;
            rowNum[x][0] = Math.min(rowNum[x][0], y);
            rowNum[x][1] = Math.max(rowNum[x][1], y);

            colNum[y][0] = Math.min(colNum[y][0], x);
            colNum[y][1] = Math.max(colNum[y][1], x);
        }

        int res = 0;
        for (int[] build : buildings) {
            int x = build[0] - 1;
            int y = build[1] - 1;

            if (y > rowNum[x][0] && y < rowNum[x][1] && x > colNum[y][0] && x < colNum[y][1]) {
                res++;
            }
        }

        return res;
    }
}
