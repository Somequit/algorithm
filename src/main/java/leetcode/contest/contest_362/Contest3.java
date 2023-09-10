package leetcode.contest.contest_362;


import javafx.util.Pair;
import utils.AlgorithmUtils;

import java.util.*;

/**
 *
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
            int[][] grid = AlgorithmUtils.systemInTwoArray();

            int res = contest.solution(grid);
            System.out.println(res);
        }

    }

    int ans = Integer.MAX_VALUE;
    public int solution(int[][] grid) {
        ans = Integer.MAX_VALUE;
        List<Pair<Integer, Integer>> zeroGrid = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 0) {
                    zeroGrid.add(new Pair<>(i, j));

                }
            }
        }

        if (zeroGrid.size() == 0) {
            return 0;
        }

        List<Integer>[] moveNumList = new ArrayList[9];
        for (int i = 0; i < 9; i++) {
            moveNumList[i] = new ArrayList<>();
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] > 1) {
                    for (int k = 0; k < zeroGrid.size(); k++) {
                        int moveCount = Math.abs(zeroGrid.get(k).getKey() - i) + Math.abs(zeroGrid.get(k).getValue() - j);
                        moveNumList[i * 3 + j].add(moveCount);
                    }
                }
            }
        }

//        System.out.println(Arrays.toString(moveNumList));

        int[] moveCount = new int[9];
        dfs(grid, moveNumList, 0, zeroGrid.size(), moveCount, 0);

        return ans;
    }

    private void dfs(int[][] grid, List<Integer>[] moveNumList, int index, int zeroNum, int[] moveCount, int res) {
        if (index == zeroNum) {
            ans = Math.min(res, ans);
            return;
        }

        for (int i = 0; i < 9; i++) {
//            System.out.println(i + " : " + index + " : " + moveCount[i] + " : " + grid[i / 3][i % 3]);
            if (moveNumList[i].size() == 0 || moveCount[i] + 1 >= grid[i / 3][i % 3]) {
                continue;
            }

            moveCount[i]++;
            dfs(grid, moveNumList, index + 1, zeroNum, moveCount, res + moveNumList[i].get(index));
            moveCount[i]--;
        }

    }


}
