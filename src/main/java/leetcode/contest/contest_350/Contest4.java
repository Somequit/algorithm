package leetcode.contest.contest_350;

import utils.AlgorithmUtils;

import java.util.Arrays;


/**
 * @author gusixue
 * @date 2023/5/14
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
            int[] cost = AlgorithmUtils.systemInArray();
            int[] time = AlgorithmUtils.systemInArray();

            int res = contest.solution(cost, time);
            System.out.println(res);
        }

    }

    public int solution(int[] cost, int[] time) {
        int res = Integer.MAX_VALUE;
        int inf = 1_000_000_000;

        int len = time.length;
        if (len == 1) {
            return cost[0];
        }

        int maxPaint = (len + 1) >> 1;
        int maxTime = maxHalfTime(time);
//        System.out.println(maxTime);
        int[][] dp = new int[maxPaint + 1][maxTime + 1];
        for (int i = 0; i < maxPaint + 1; i++) {
            Arrays.fill(dp[i], inf);
        }
        dp[0][0] = 0;

        for (int i = 0; i < len; i++) {
            for (int j = maxPaint; j >= 1; j--) {
                for (int k = maxTime; k >= time[i]; k--) {
                    dp[j][k] = Math.min(dp[j - 1][k - time[i]] + cost[i], dp[j][k]);
                }
            }
        }

//        for (int j = 0; j < maxPaint + 1; j++) {
//            for (int k = 0; k < maxTime + 1; k++) {
//                System.out.print(j + ":" + k + ":" + dp[j][k] + "\t");
//            }
//            System.out.println();
//        }


        for (int j = 1; j < maxPaint + 1; j++) {
            for (int k = len - j; k < maxTime + 1; k++) {
                res = Math.min(res, dp[j][k]);
            }
        }


        return res;
    }

    private int maxHalfTime(int[] time) {
        int[] timeTmp = Arrays.copyOf(time, time.length);
        Arrays.sort(timeTmp);

        int len = timeTmp.length;
        int halfFloor = len >> 1;

        int res = 0;
        for (int i = len - 1; i >= halfFloor; i--) {
            res += timeTmp[i];
            if (res >= len) {
                break;
            }
        }
        return res;
    }


}
