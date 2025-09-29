package leetcode.brushQuestions.medium;

import leetcode.Contest1;
import utils.AlgorithmUtils;

import java.util.Arrays;

/**
 * @author gusixue
 * @description 1039. 多边形三角剖分的最低得分
 * @date 2025/9/29 1:22 下午
 */
public class MinScoreTriangulation {

    public static void main(String[] args) {
        MinScoreTriangulation minScoreTriangulation = new MinScoreTriangulation();

        while (true) {
            int[] values = AlgorithmUtils.systemInArray();

            int res = minScoreTriangulation.minScoreTriangulation(values);
            System.out.println(res);
        }

    }

    /**
     * 首先题目可以转化为多边形 t[0,n-1]闭区间，按规则的选择 n-2 个不同的三角形求最低分
     * 其次每个三角形可看成选择一条边（可以多边形已有的、也可以是辅助线）然后加一个点形成，且多边形已有的每条边都会用且仅用一次构成三角形
     * 接着使用区间动态规划求全局最优，dp[i][j] 代表 t[i,j]闭区间 构成三角形的最低分
     * 接着为了方便计算，先选择 边(0,n-1) 以及 [0,n-1] 中另外一个点构成三角形，这样也能不重不漏
     * 即通项公式就是：dp[i][j] = dp[i][k] + dp[k][j] + v[i]*v[j]*v[k]
     *     i、j 取值范围为 [0,n-1]闭区间，k 取值范围为 [i+1,j-1]闭区间
     *     k-i<=1 结果为 0，k-i==2 结果为 v[i]*v[i+1]v*[k]，当然 j-k 也一致
     * 结果就是 dp[0][n-1] 的值
     * 时间复杂度：O（n^3）,空间复杂度：O（n^2）
     */
    public int minScoreTriangulation(int[] values) {
        int len = values.length;
        int inf = (int)(1e9 + 7);

        int[][] dp = new int[len][len];
        Arrays.stream(dp).forEach(arr -> Arrays.fill(arr, inf));
        // 连续的三个点直接构成三角形，不能在此初始化，例如：(0,1,n-1) 与 (0,n-1,n-2) 都可以构成三角形
//        for (int i = 0; i < len - 2; i++) {
//            dp[i][i + 2] = values[i] * values[i + 1] * values[i + 2];
//        }
        // 连续的两个点直接为 0
        for (int i = 0; i < len -1; i++) {
            dp[i][i + 1] = 0;
        }
        dp[len - 1][0] = 0;

        // 枚举 j-i+1 的值，即多边形区间点的数量
        for (int l = 3; l <= len; l++) {
            for (int i = 0; i < len - 2 && i + l - 1 < len; i++) {
                int j = i + l - 1;

                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][k] + dp[k][j] + values[i] * values[j] * values[k], dp[i][j]);
//                    System.out.println(i + " : " + j + " : " + k + " : " + dp[i][k] + " : " + dp[k][j] + " : " + dp[i][j]);
                }
            }
        }

        return dp[0][len - 1];
    }
}
