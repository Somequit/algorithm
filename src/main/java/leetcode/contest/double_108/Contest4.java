package leetcode.contest.double_108;

import utils.AlgorithmUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * @author gusixue
 * 6928. 黑格子的数目
 * 给你两个整数 m 和 n ，表示一个下标从 0 开始的 m x n 的网格图。
 * 给你一个下标从 0 开始的二维整数矩阵 coordinates ，其中 coordinates[i] = [x, y] 表示坐标为 [x, y] 的格子是 黑色的 ，所有没出现在 coordinates 中的格子都是 白色的。
 * 一个块定义为网格图中 2 x 2 的一个子矩阵。更正式的，对于左上角格子为 [x, y] 的块，其中 0 <= x < m - 1 且 0 <= y < n - 1 ，
 * 包含坐标为 [x, y] ，[x + 1, y] ，[x, y + 1] 和 [x + 1, y + 1] 的格子。
 * 请你返回一个下标从 0 开始长度为 5 的整数数组 arr ，arr[i] 表示恰好包含 i 个 黑色 格子的块的数目。
 * 2 <= m <= 105
 * 2 <= n <= 105
 * 0 <= coordinates.length <= 104
 * coordinates[i].length == 2
 * 0 <= coordinates[i][0] < m
 * 0 <= coordinates[i][1] < n
 * coordinates 中的坐标对两两互不相同。
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

    /**
     * m*n 比较大，因此不能遍历矩阵，但是黑色块比较少，因此可以由黑色块入手，计算 1、2、3、4 块黑格的个数，然后总个数减去所有就是 0 快黑格的个数，
     * 遍历所有黑格，将其作为符合要求的黑格的 左上角、左下角、右上角、右下角，同时判断该 2*2 矩形其余位置黑格数，然后计算出 1、2、3、4 块黑格的矩阵数（含重复），
     * 最后要注意，两个黑格子就会重复计算两次、三个重复计算三次、四个重复计算四次，因此除掉即可
     * 总个数可以取每个矩阵的右下角，除第一行与第一列外均可做右下角，因此总个数为 (m-1)*(n-1)
     * 为了方便可以使用 HashSet<Integer> 存储格子下标，使用二维转一维存储与计算：m*maxN+n
     * 时间复杂度：O（coordinates），空间复杂度：O（coordinates）
     */
    private long[] solution(int m, int n, int[][] coordinates) {
        // 二维转一维使用 m*maxN+n，所以 maxN 必须为 n（这儿使用 n的最大值 更方便排查）
        long maxN = 100000;
        long[] res = new long[5];
        
        // 存在黑格子
        if (coordinates.length > 0) {
            // 存放黑格
            Set<Long> blockSet = saveBlock(coordinates, maxN);

            // 遍历所有黑格，将其作为符合要求的黑格的 左上角、左下角、右上角、右下角，同时判断该 2*2 矩形其余位置黑格数，然后计算出 1、2、3、4 块黑格的矩阵数（含重复）
            doAllBlackBlocks(coordinates, blockSet, maxN, res, m, n);

            // 两个黑格子就会重复计算两次，三个重复计算三次，四个重复计算四次
            res[2] /= 2;
            res[3] /= 3;
            res[4] /= 4;
        }

        // 所有格子数
        long total = (long)(m - 1) * (n - 1);
        res[0] = total - res[1] - res[2] - res[3] - res[4];
        
        return res;
    }

    /**
     *  遍历所有黑格，将其作为符合要求的黑格的 左上角、左下角、右上角、右下角，同时判断该 2*2 矩形其余位置黑格数，然后计算出 1、2、3、4 块黑格的矩阵数（含重复）
     */
    private void doAllBlackBlocks(int[][] coordinates, Set<Long> blockSet, long maxN, long[] res, int m, int n) {
        for (int i = 0; i < coordinates.length; i++) {
            int x = coordinates[i][0];
            int y = coordinates[i][1];
            // 左上
            int[][] leftUpper = new int[][]{{0,0},{0,1},{1,0},{1,1}};
            int black = getBlackNum(blockSet, leftUpper, x, y, maxN, m, n);
            if (black > 0) {
                res[black]++;
            }

            // 左下
            int[][] leftLower = new int[][]{{-1,0},{-1,1},{0,0},{0,1}};
            black = getBlackNum(blockSet, leftLower, x, y, maxN, m, n);
            if (black > 0) {
                res[black]++;
            }

            // 右上
            int[][] rightUpper = new int[][]{{0,-1},{0,0},{1,-1},{1,0}};
            black = getBlackNum(blockSet, rightUpper, x, y, maxN, m, n);
            if (black > 0) {
                res[black]++;
            }

            // 右下
            int[][] rightLower = new int[][]{{-1,-1},{-1,0},{0,-1},{0,0}};
            black = getBlackNum(blockSet, rightLower, x, y, maxN, m, n);
            if (black > 0) {
                res[black]++;
            }
        }
    }

    private int getBlackNum(Set<Long> blockSet, int[][] direction, int x, int y, long maxN, int m, int n) {
        int black = 0;
        int xTemp = x;
        int yTemp = y;
        for (int i = 0; i < direction.length; i++) {
            xTemp = x + direction[i][0];
            yTemp = y + direction[i][1];
            if (xTemp >= 0 && xTemp < m && yTemp >= 0 && yTemp < n) {
                if (blockSet.contains(toDimensional(xTemp, yTemp, maxN))) {
                    black++;
                }

                // 2*2 矩形某个位置在大矩阵外，则代表黑格不能作为该位置
            } else {
                black = -1;
                break;
            }
        }
//        System.out.println(x + " : " + y + " : " + black);
        return black;
    }

    /**
     * 存放黑格
     */
    private Set<Long> saveBlock(int[][] coordinates, long maxN) {
        Set<Long> blockSet = new HashSet<>(coordinates.length << 2);
        for (int i = 0; i < coordinates.length; i++) {
            // 二维转一维
            blockSet.add(toDimensional(coordinates[i][0], coordinates[i][1], maxN));
        }

        return blockSet;
    }

    /**
     * 二维转一维
     */
    private long toDimensional(int x, int y, long maxN) {
        return x * maxN + y;
    }

}
