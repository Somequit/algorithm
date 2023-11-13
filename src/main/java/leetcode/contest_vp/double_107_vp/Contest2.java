package leetcode.contest_vp.double_107_vp;


/**
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

//            int res = contest.solution(n, nums, list);
//            System.out.println(res);
        }

    }

    /**
     * @return
     */
    private int solution(int x, int y, int z) {
        // 0-x 1-y 2-z，0-1、1-0/2、2-0/2
        boolean[][][][] dp = new boolean[x + 1][y + 1][z + 1][3];
        int res = 3;
        dp[0][0][1][2] = true;
        dp[0][1][0][1] = true;
        dp[1][0][0][0] = true;

        dp[0][1][1][2] = true;
        dp[1][0][1][0] = true;
        dp[1][1][0][0] = true;
        dp[1][1][0][1] = true;

        for (int i = 1; i < x + 1; i++) {
            for (int j = 1; j < y + 1; j++) {
                for (int k = 1; k < z + 1; k++) {
                    if (!dp[i][j][k][0]) {
                        dp[i][j][k][0] = dp[i - 1][j][k][1] | dp[i - 1][j][k][2];
                    }
                    if (!dp[i][j][k][1]) {
                        dp[i][j][k][1] = dp[i][j - 1][k][0];
                    }
                    if (!dp[i][j][k][2]) {
                        dp[i][j][k][2] = dp[i][j][k - 1][1] | dp[i][j][k - 1][2];
                    }

                    if (dp[i][j][k][0] || dp[i][j][k][1] || dp[i][j][k][2]) {
                        res = Math.max(res, i + j + k);
                    }
                }
            }
        }

        return res * 2;
    }

    /**
     * @return
     */
    private int solutionOptimization(int x, int y, int z) {
        return (Math.min(x + 1, y) + Math.min(y + 1, x) + z) << 1;

    }

}
