package leetcode.brushQuestions.medium;

/**
 * @author gusixue
 * @description 2257. 统计网格图中没有被保卫的格子数
 * @date 2025/11/2 9:57 上午
 */
public class CountUnguarded {

    /**
     * 从左往右从上到下遍历一遍，从上到下从左往右再遍历一遍，vis 数组标记所有被守卫的格子
     */
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        // 0-空，1-守卫，2-看守到，3墙
        int[][] grids = new int[m][n];
        int res = m * n;

        for (int[] guard : guards) {
            grids[guard[0]][guard[1]] = 1;
            res--;
        }
        for (int[] wall : walls) {
            grids[wall[0]][wall[1]] = 3;
            res--;
        }

        for (int i = 0; i < m; i++) {
            boolean guardExist = false;
            int l = 0;
            for (int j = 0; j <= n; j++) {
                if (j == n || grids[i][j] == 3) {
                    if (guardExist) {
                        while (l < j) {
                            if (grids[i][l] == 0) {
                                grids[i][l] = 2;
                                res--;
                            }
                            l++;
                        }
                    }

                    l = j + 1;
                    guardExist = false;

                } else if (grids[i][j] == 1) {
                    while (l < j) {
                        if (grids[i][l] == 0) {
                            grids[i][l] = 2;
                            res--;
                        }
                        l++;
                    }

                    l = j + 1;
                    guardExist = true;
                }
            }
        }


        for (int i = 0; i < n; i++) {
            boolean guardExist = false;
            int l = 0;
            for (int j = 0; j <= m; j++) {
                if (j == m || grids[j][i] == 3) {
                    if (guardExist) {
                        while (l < j) {
                            if (grids[l][i] == 0) {
                                grids[l][i] = 2;
                                res--;
                            }
                            l++;
                        }
                    }

                    l = j + 1;
                    guardExist = false;

                } else if (grids[j][i] == 1) {
                    while (l < j) {
                        if (grids[l][i] == 0) {
                            grids[l][i] = 2;
                            res--;
                        }
                        l++;
                    }

                    l = j + 1;
                    guardExist = true;
                }
            }
        }

        return res;
    }
}
