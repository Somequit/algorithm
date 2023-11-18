package leetcode.brushQuestions;

import utils.AlgorithmUtils;

import java.util.*;

/**
 * @author gusixue
 * @description
 * 1591. 奇怪的打印机 II
 * 给你一个奇怪的打印机，它有如下两个特殊的打印规则：
 * 每一次操作时，打印机会用同一种颜色打印一个矩形的形状，每次打印会覆盖矩形对应格子里原本的颜色。
 * 一旦矩形根据上面的规则使用了一种颜色，那么 相同的颜色不能再被使用 。
 * 给你一个初始没有颜色的 m x n 的矩形 targetGrid ，其中 targetGrid[row][col] 是位置 (row, col) 的颜色。
 * 如果你能按照上述规则打印出矩形 targetGrid ，请你返回 true ，否则返回 false 。
 * m == targetGrid.length
 * n == targetGrid[i].length
 * 1 <= m, n <= 60
 * 1 <= targetGrid[row][col] <= 60
 * @date 2023/11/18
 */
public class IsPrintable {

    public static void main(String[] args) {
        IsPrintable isPrintable = new IsPrintable();
        while (true) {
            int[][] targetGrid = AlgorithmUtils.systemInTwoArray();

            boolean res = isPrintable.solution(targetGrid);
            System.out.println(res);
        }

    }

    /**
     * 反向思考 + 状压：Java 7ms
     * 每种颜色找到最小与最大的 x 与 y 轴下标，此时该颜色最小就必须涂成这种矩形，接着枚举每种颜色矩阵内部包含其他哪些颜色（60 中颜色可以状压到 long），
     * 此时可以想到从外到内一层一层剥离最后涂得颜色，看最后是否可以还原成为涂色矩阵，将颜色种类为 1 的颜色类型 c （没有被覆盖、一定是最后涂上的）删除，
     * 然后将其他未删除的颜色均删除 c（如包含的），最后看是否颜色矩阵可以还原
     */
    private boolean solution(int[][] targetGrid) {
        int colorType = 60;

        // 每一种颜色的四个角的坐标，该颜色最小就必须涂成这种矩形，
        int[] minX = new int[colorType];
        Arrays.fill(minX, Integer.MAX_VALUE);
        int[] minY = new int[colorType];
        Arrays.fill(minY, Integer.MAX_VALUE);
        int[] maxX = new int[colorType];
        Arrays.fill(maxX, -1);
        int[] maxY = new int[colorType];
        Arrays.fill(maxY, -1);

        int m = targetGrid.length;
        int n = targetGrid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int color = targetGrid[i][j] - 1;
                minX[color] = Math.min(minX[color], i);
                maxX[color] = Math.max(maxX[color], i);

                minY[color] = Math.min(minY[color], j);
                maxY[color] = Math.max(maxY[color], j);
            }
        }

        // 每种颜色矩阵内部包含其他哪些颜色（60 中颜色可以状压到 long）
        long[] colorMatrixType = new long[colorType];
        int colorMatrixTypeTotal = 0;
        for (int c = 0; c < colorType; c++) {
            if (minX[c] != Integer.MAX_VALUE) {
                colorMatrixTypeTotal++;

                for (int i = minX[c]; i <= maxX[c]; i++) {
                    for (int j = minY[c]; j <= maxY[c]; j++) {
                        colorMatrixType[c] |= 1L << (targetGrid[i][j] - 1);
                    }
                }

            }
        }
//        System.out.println(colorMatrixColorMap);

        // 循环将颜色种类为 1 的删除
        while (colorMatrixTypeTotal > 0) {
//            System.out.println(Arrays.toString(colorMatrixType));

            long removeColorType = 0;
            // 当前哪些颜色种类为 1 删除
            for (int c = 0; c < colorType; c++) {
                if (Long.bitCount(colorMatrixType[c]) == 1) {
                    colorMatrixType[c] = 0;

                    colorMatrixTypeTotal--;
                    removeColorType |= 1L << c;
                }
            }
            if (removeColorType == 0) {
                break;
            }

//            System.out.println(Long.toBinaryString(removeColorType));
            removeColorType = ~removeColorType;
//            System.out.println(Long.toBinaryString(removeColorType));

            // 删除其他颜色中包含颜色种类为 1 的元素
            for (int c = 0; c < colorType; c++) {
                colorMatrixType[c] &= removeColorType;
            }
//            System.out.println(Arrays.toString(colorMatrixType));
        }

        return colorMatrixTypeTotal == 0;
    }
}
