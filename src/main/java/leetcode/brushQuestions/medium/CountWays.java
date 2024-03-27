package leetcode.brushQuestions.medium;

import java.util.Arrays;

/**
 * @author gusixue
 * @description 2580. 统计将重叠区间合并成组的方案数
 * @date 2024/3/27
 */
public class CountWays {

    /**
     * 划分成多个不相交的区间 rangeCount，每个 rangeCount 可以任意放入组 1 或 组 2，结果为 2^rangeCount
     */
    public int countWays(int[][] ranges) {
        Arrays.sort(ranges, (intArr1, intArr2) -> intArr1[0] == intArr2[0] ? intArr1[1] - intArr2[1] : intArr1[0] - intArr2[0]);
        int curEnd = ranges[0][1];

        int rangeCount = 1;
        for (int i = 1; i < ranges.length; i++) {
            if (curEnd >= ranges[i][0]) {
                curEnd = Math.max(curEnd, ranges[i][1]);

            } else {
                rangeCount++;
                curEnd = ranges[i][1];
            }
        }

        return qPow(2, rangeCount, (int)(1e9+7));
    }

    private int qPow(long value, long pow, int mod) {
        long res = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) {
                res *= value;
                res %= mod;
            }

            value *= value;
            value %= mod;
            pow >>= 1;
        }
        return (int)res;
    }
}
