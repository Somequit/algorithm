package leetcode.contest.contest_367;


import java.util.Arrays;

/**
 * @author gusixue
 * @date 2023/10/08
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
//            int purchaseAmount = AlgorithmUtils.systemInNumberInt();

//            int res = contest.solution(purchaseAmount);
//            System.out.println(res);
        }

    }

    /**
     * @return
     */
    private int[][] solution(int[][] grid) {
        int mod = 12345;
        int n = grid.length;
        int m = grid[0].length;
        int[] rowProduct = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] %= mod;
            }
        }

        for (int i = 0; i < n; i++) {
            rowProduct[i] = 1;

            for (int j = 0; j < m; j++) {
                rowProduct[i] = rowProduct[i] * grid[i][j] % mod;
            }
        }
//        System.out.println(Arrays.toString(rowProduct));

        int[] rowProductPrefix = new int[n];
        rowProductPrefix[0] = 1;
        for (int i = 1; i < n; i++) {
            rowProductPrefix[i] = rowProductPrefix[i - 1] * rowProduct[i - 1] % mod;
        }
//        System.out.println(Arrays.toString(rowProductPrefix));

        int[] rowProductSuffix = new int[n];
        rowProductSuffix[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            rowProductSuffix[i] = rowProductSuffix[i + 1] * rowProduct[i + 1] % mod;
        }
//        System.out.println(Arrays.toString(rowProductSuffix));

        int[][] p = new int[n][m];
        int[] gridProductPrefix = new int[m];
        int[] gridProductSuffix = new int[m];
        for (int i = 0; i < n; i++) {

            gridProductPrefix[0] = 1;
            for (int j = 1; j < m; j++) {
                gridProductPrefix[j] = gridProductPrefix[j - 1] * grid[i][j - 1] % mod;
            }

            gridProductSuffix[m - 1] = 1;
            for (int j = m - 2; j >= 0; j--) {
                gridProductSuffix[j] = gridProductSuffix[j + 1] * grid[i][j + 1] % mod;
            }

            for (int j = 0; j < m; j++) {
                p[i][j] = rowProductPrefix[i] * rowProductSuffix[i] % mod * gridProductPrefix[j] % mod * gridProductSuffix[j] % mod;
            }
        }

        return p;
    }


}
