package leetcode.brushQuestions.hard;

import javafx.util.Pair;

import java.util.*;

/**
 * @author gusixue
 * @description 1349. 参加考试的最大学生数
 * @date 2023/12/26
 */
public class MaxStudents {

    /**
     * Java + 按行状压暴力 + DP：
     *
     * 第 1 步：
     * 首先思考每个好座位选或不选的 DFS 暴力求解，会超时
     * 其次分析题意可知，仅有相邻两行之间有限制，
     * 因此可以想到将行拆开，仅每一行去暴力所有可能，使用 DP 判断相邻两行的限制即可
     *
     * 第 2 步：
     * 每行暴力：
     * 每行遍历从 0 到 (2 ^ n) - 1 的数字 seat，seat 转化为二进制、1 代表有人，
     * isRowUsableSeat 行内满足要求：遍历每个 1 相邻左侧没有 1 且 每个 1 均是好座位，
     * 此 seat 代表该行内部满足条件，
     *
     * 第 3 步：
     * DP 判断两行的限制：
     * 定义状态：dp[i][seat] 代表前 i 行中，第 i 行座位为 seat 时的最大学生数
     * 初始化：dp[0][isRowUsableSeat(seat)] = countOne(seat)（seat 中 1 个个数），代表第一行没有限制
     * 状态转移方程：dp[i][isRowUsableSeat(seat)] = countOne(seat)
     * + max(isCrossUsableSeat(0, seat)?dp[i-1][0]):0 , ... , isCrossUsableSeat((2 ^ n) - 1, seat)?dp[i-1][(2 ^ n) - 1]:0)
     * 其中 isCrossUsableSeat(seat1, seat2) 代表两行（seat1-上一行、seat2-下一行）是否满足要求，即 seat2 每个 1 的下标 col、在 seat1 中 col-1 与 col+1 都不存在 1
     *
     * 第 4 步：
     * 预处理所有 isCrossUsableSeat，
     * 由于 i 仅与 i-1 相关，因此使用滚动数组即可
     * 时间复杂度：O（(m + n) * 2 ^ 2n），空间复杂度：O（n * 2 ^ 2n）
     *
     */
    public int maxStudents(char[][] seats) {
        int m = seats.length;
        int n = seats[0].length;

        int seatTotal = 1 << n;
        // 预处理所有 isCrossUsableSeat
        boolean[][] crossUsableSeat = preCrossUsableSeat(seatTotal);

        int[][] dp = new int[2][seatTotal];
        // 初始化
        for (int j = 0; j < seatTotal; j++) {
            // 第 0 行满足 isRowUsableSeat
            if (isRowUsableSeat(seats[0], j)) {
                dp[0][j] = countOne(j);
            }
        }

        // 状态转移方程：dp[i][isRowUsableSeat(seat)] = countOne(seat)
        // + max(isCrossUsableSeat(0, seat)?dp[i-1][0]):0 , ... , isCrossUsableSeat((2 ^ n) - 1, seat)?dp[i-1][(2 ^ n) - 1]:0)
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < seatTotal; j++) {
                // 第 i 行内满足条件
                if (isRowUsableSeat(seats[i], j)) {
                    int countOneJ = countOne(j);

                    for (int k = 0; k < seatTotal; k++) {
                        // 第 i 行与 i-1 行满足条件
                        if (crossUsableSeat[j][k]) {
                            dp[i & 1][j] = Math.max(dp[i & 1][j], dp[(i - 1) & 1][k] + countOneJ);
                        }
                    }
                }

            }
        }

        int res = 0;
        for (int j = 0; j < seatTotal; j++) {
            res = Math.max(res, dp[(m - 1) & 1][j]);
        }

        return res;
    }

    /**
     * 遍历每个 1：相邻左侧没有 1 且 每个 1 均是好座位
     */
    private boolean isRowUsableSeat(char[] seats, int seat) {
        for (int i = 0; (1 << i) <= seat; i++) {
            if (((1 << i) & seat) > 0) {
                if (seats[i] == '#' || ((1 << i + 1) & seat) > 0) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 预处理所有 isCrossUsableSeat，
     */
    private boolean[][] preCrossUsableSeat(int seatTotal) {
        boolean[][] crossUsableSeat = new boolean[seatTotal][seatTotal];

        // seat2 每个 1 的下标 col、在 seat1 中 col-1 与 col+1 都不存在 1
        for (int seat1 = 0; seat1 < seatTotal; seat1++) {
            for (int seat2 = 0; seat2 < seatTotal; seat2++) {
                if (isCrossUsableSeat(seat1, seat2)) {
                    crossUsableSeat[seat1][seat2] = true;
                }
            }
        }

        return crossUsableSeat;
    }

    private boolean isCrossUsableSeat(int seat1, int seat2) {
        for (int bitNum = (seat2 & -seat2); bitNum > 0; bitNum = (seat2 & -seat2)) {
            if ((bitNum != 1 && (seat1 & (bitNum >> 1)) > 0) || ((seat1 & (bitNum << 1)) > 0)) {
                return false;
            }

            seat2 -= bitNum;
        }

        return true;
    }

    /**
     * 二进制 1 的个数
     */
    private int countOne(int seat) {
        int res = 0;
        while (seat > 0) {
            seat &= seat - 1;
            res++;
        }
        return res;
    }
}
