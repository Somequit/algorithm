package leetcode.contest_vp.contest_348_vp;


/**
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

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
    private long solution(int n, int[][] queries) {
        long res = 0L;
        boolean[] row = new boolean[n];
        int rowCount = n;
        boolean[] col = new boolean[n];
        int colCount = n;

        for (int i = queries.length - 1; i >= 0; i--) {
            // 行
            if (queries[i][0] == 0) {
                if (!row[queries[i][1]]) {
                    row[queries[i][1]] = true;
                    res += (long) queries[i][2] * colCount;
                    rowCount--;
                }

            // 列
            } else {
                if (!col[queries[i][1]]) {
                    col[queries[i][1]] = true;
                    res += (long) queries[i][2] * rowCount;
                    colCount--;
                }

            }
        }

        return res;
    }


}
