package leetcode.contest_vp.double_106_vp;


import java.util.*;

/**
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

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
    private List<Integer> solution(int[][] grid) {
        int m = grid.length;
        int[] colNums = new int[m];
        for (int i = 0; i < m; i++) {

            int colNumTemp = 0;
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    colNumTemp |= (1 << j);
                }
            }

            colNums[i] = colNumTemp;
        }
//        System.out.println(Arrays.toString(colNums));

        List<Integer> res = new ArrayList<>();
        if (m == 1) {
            if (colNums[0] == 0) {
                res.add(0);
            }

        } else {
            int[] numRow = new int[32];
            for (int i = 0; i < m; i++) {
                numRow[colNums[i]] = i + 1;
            }
            if(numRow[0] > 0) {
                res.add(numRow[0] - 1);

            } else {
                for (int i = 1; i < 32 && res.size() == 0; i++) {
                    for (int j = i + 1; j < 32; j++) {

                        if (numRow[i] > 0 && numRow[j] > 0 && (i & j) == 0) {
                            res.add(numRow[i] - 1);
                            res.add(numRow[j] - 1);
                            break;
                        }
                    }
                }
            }

        }
        Collections.sort(res);

        return res;
    }


}
