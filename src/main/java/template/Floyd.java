package template;

import java.util.Arrays;

/**
 * @author gusixue
 * @description n^3 最短路算法 Floyd
 * @date 2024/1/21
 */
public class Floyd {

    public void floyd(int n) {
        // 极大值，代表路不通
        int inf = n + 1;

        int[][] path = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(path[i], inf);
        }

        // (i,i+1) 长度为 1，（i，i）长度为 0
        for (int i = 0; i < n - 1; i++) {
            path[i][i + 1] = path[i + 1][i] = 1;
            path[i][i] = 0;
        }
        path[n - 1][n - 1] = 0;

        // Floyd 核心算法
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n ; j++) {
                    if (path[i][j] > path[i][k] + path[k][j]) {
                        path[i][j] = path[i][k] + path[k][j];
                    }
                }
            }
        }
    }

}
