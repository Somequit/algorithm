package leetcode.contest.double_108;

import utils.AlgorithmUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * @author gusixue
 * @date 2023/5/14
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
            int m = AlgorithmUtils.systemInNumberInt();
            int n = AlgorithmUtils.systemInNumberInt();
            int[][] coordinates = AlgorithmUtils.systemInTwoArray();

            long[] res = contest.solution(m, n, coordinates);
            System.out.println(Arrays.toString(res));
        }

    }

    private long[] solution(int m, int n, int[][] coordinates) {
        long maxLen = 100000;

        long[] res = new long[5];

        long total = (long)(m - 1) * (n - 1);
//        System.out.println(total);

        if (coordinates.length > 0) {
            Set<Long> blockSet = new HashSet<>(coordinates.length << 2);
            for (int i = 0; i < coordinates.length; i++) {
                blockSet.add(toDimensional(coordinates[i][0], coordinates[i][1], maxLen));
            }
//            System.out.println(blockSet);

            for (int i = 0; i < coordinates.length; i++) {
                int x = coordinates[i][0];
                int y = coordinates[i][1];
                // 左上
                if (!(x == m - 1 || y == n - 1)) {
                    int black = 1;
                    black += blockSet.contains(toDimensional(x, y + 1, maxLen)) ? 1 : 0;
                    black += blockSet.contains(toDimensional(x + 1, y, maxLen)) ? 1 : 0;
                    black += blockSet.contains(toDimensional(x + 1, y + 1, maxLen)) ? 1 : 0;
                    res[black]++;
                }

                // 左下
                if (!(x == 0 || y == n - 1)) {
                    int black = 1;
                    black += blockSet.contains(toDimensional(x - 1, y, maxLen)) ? 1 : 0;
                    black += blockSet.contains(toDimensional(x - 1, y + 1, maxLen)) ? 1 : 0;
                    black += blockSet.contains(toDimensional(x, y + 1, maxLen)) ? 1 : 0;
                    res[black]++;
                }

                // 右上
                if (!(x == m - 1 || y == 0)) {
                    int black = 1;
                    black += blockSet.contains(toDimensional(x, y - 1, maxLen)) ? 1 : 0;
                    black += blockSet.contains(toDimensional(x + 1, y - 1, maxLen)) ? 1 : 0;
                    black += blockSet.contains(toDimensional(x + 1, y, maxLen)) ? 1 : 0;
                    res[black]++;
                }

                // 右下
                if (!(x == 0 || y == 0)) {
                    int black = 1;
                    black += blockSet.contains(toDimensional(x - 1, y - 1, maxLen)) ? 1 : 0;
                    black += blockSet.contains(toDimensional(x - 1, y, maxLen)) ? 1 : 0;
                    black += blockSet.contains(toDimensional(x, y - 1, maxLen)) ? 1 : 0;
                    res[black]++;
                }
            }
        }

//        System.out.println(Arrays.toString(res));

        // 两个黑格子就会重复计算两次，三个重复计算三次，四个重复计算四次
        res[2] /= 2;
        res[3] /= 3;
        res[4] /= 4;

        res[0] = total - res[1] - res[2] - res[3] - res[4];
        return res;
    }

    private long toDimensional(int x, int y, long maxLen) {
        return x * maxLen + y;
    }

}
