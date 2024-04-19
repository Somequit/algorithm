package leetcode.brushQuestions.hard;

import java.util.Arrays;

/**
 * @author gusixue
 * @description 1883. 准时抵达会议现场的最小跳过休息次数
 * @date 2024/4/19
 */
public class MinSkips {

    /**
     * dp[i][j] 代表到第 i 条路（使 dist 右移一位方便计算），休息 j 次的最小时间
     * 递推方程：dp[i][j] = min(dp[i-1][j]+di/s, dp[i-1][j-1]+(surplusDist[di-1],di)/s)，i > j，代表第 i 条路前是否休息
     * 注意需要存储一个 surplusDist[i][j] 代表此处最小时间情况下还剩最多可以走的距离
     */
    public int minSkips(int[] dist, int speed, int hoursBefore) {
        int inf = Integer.MAX_VALUE / 2;
        // 一定不能到达
        int total = 0;
        for (int distVal : dist) {
            total += distVal;
        }
        if ((total + speed - 1) / speed > hoursBefore) {
            return -1;
        }

        int n = dist.length;
        int[][] dp = new int[n + 1][n];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(dp[i], inf);
        }
        // 此处最小时间情况下还剩最多可以走的距离
        int[][] surplusDist = new int[n + 1][n];

        // 初始化不休息的最小时间
        dp[0][0] = 0;
        for (int i = 1; i < n + 1; i++) {
            int hourTemp = (dist[i - 1] + speed - 1) / speed;
            dp[i][0] = dp[i - 1][0] + hourTemp;
            surplusDist[i][0] = hourTemp * speed - dist[i - 1];
        }

        // 不休息即可
        if (dp[n][0] <= hoursBefore) {
            return 0;
        }

        for (int j = 1; j < n; j++) {
            for (int i = j + 1; i < n + 1; i++) {
                int curHour = (dist[i - 1] + speed - 1) / speed;
                int curDist = curHour * speed - dist[i - 1];

                int preHour = 0;
                int preDist = 0;
                if (surplusDist[i - 1][j - 1] >= dist[i - 1]) {
                    preHour = 0;
                    preDist = surplusDist[i - 1][j - 1] - dist[i - 1];

                } else {
                    preHour = (dist[i - 1] - surplusDist[i - 1][j - 1] + speed - 1) / speed;
                    preDist = preHour * speed - (dist[i - 1] - surplusDist[i - 1][j - 1]);
                }

                if (dp[i - 1][j] + curHour < dp[i - 1][j - 1] + preHour ||
                        (dp[i - 1][j] + curHour == dp[i - 1][j - 1] + preHour && curDist >= preDist)) {
                    dp[i][j] = dp[i - 1][j] + curHour;
                    surplusDist[i][j] = curDist;

                } else{
                    dp[i][j] = dp[i - 1][j - 1] + preHour;
                    surplusDist[i][j] = preDist;
                }
            }

            if (dp[n][j] <= hoursBefore) {
                return j;
            }
        }

        return n - 1;
    }
}
