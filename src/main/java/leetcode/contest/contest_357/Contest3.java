package leetcode.contest.contest_357;

import javafx.util.Pair;
import utils.AlgorithmUtils;

import java.util.*;
import java.util.stream.Collectors;


/**
 *
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
            List<List<Integer>> grid = Arrays.stream(AlgorithmUtils.systemInTwoArrayInteger())
                    .map(Arrays::asList).collect(Collectors.toList());

            long res = contest.solution(grid);
            System.out.println(res);
        }
    }

    public long solution(List<List<Integer>> grid) {
        int n = grid.size();
        if (grid.get(n - 1).get(n - 1) == 1 || grid.get(0).get(0) == 1) {
            return 0;
        }

        // 多源 BFS 确定每个点的安全系数
        int[][] safenessGrid = getSafenessGrid(grid);

        // 单源 BFS 从起点 (0,0) 出发队列返回最大值，更新该点到起点的最大安全系数
        int res = doMaximumSafenessFactor(safenessGrid);

        return res;
    }

    private int[][] getSafenessGrid(List<List<Integer>> grid) {
        int n = grid.size();

        int[][] safenessGrid = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(safenessGrid[i], Integer.MAX_VALUE);
        }

        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    queue.add(new Pair<>(i, j));
                    safenessGrid[i][j] = 0;
                }
            }
        }
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> pair = queue.poll();

            int x = pair.getKey();
            int y = pair.getValue();
            int distance = safenessGrid[x][y];

            List<Pair<Integer, Integer>> nextPair = getNextPosition(x, y, n);
            for (int i = 0; i < nextPair.size(); i++) {
                int xx = nextPair.get(i).getKey();
                int yy = nextPair.get(i).getValue();

                if (safenessGrid[xx][yy] > distance + 1) {
                    safenessGrid[xx][yy] = distance + 1;
                    queue.add(new Pair<>(xx, yy));
//                    System.out.println(distance + 1 + " : " + xx + " : " + yy);
                }
            }
        }
//        AlgorithmUtils.systemOutArray(safenessGrid);
        return safenessGrid;
    }

    private List<Pair<Integer,Integer>> getNextPosition(int x, int y, int n) {
        int[][] next = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

        List<Pair<Integer,Integer>> res = new ArrayList<>();

        for (int i = 0; i < next.length; i++) {
            if (x + next[i][0] >= 0 && x + next[i][0] < n && y + next[i][1] >= 0 && y + next[i][1] < n) {
                res.add(new Pair<>(x + next[i][0], y + next[i][1]));
            }
        }
        return res;
    }

    private int doMaximumSafenessFactor(int[][] safenessGrid) {
        int n = safenessGrid.length;
        int[][] distanceGrid = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(distanceGrid[i], -1);
        }

        Queue<Pair<Integer, Integer>> queue = new PriorityQueue<>(new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                return distanceGrid[o2.getKey()][o2.getValue()] - safenessGrid[o1.getKey()][o1.getValue()];
            }
        });
        distanceGrid[0][0] = safenessGrid[0][0];
        queue.add(new Pair<>(0, 0));

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> pair = queue.poll();

            int x = pair.getKey();
            int y = pair.getValue();
            int distance = distanceGrid[x][y];

            List<Pair<Integer, Integer>> nextPair = getNextPosition(x, y, n);
//            System.out.println(nextPair);

            for (int i = 0; i < nextPair.size(); i++) {
                int xx = nextPair.get(i).getKey();
                int yy = nextPair.get(i).getValue();

                if (distanceGrid[xx][yy] == -1) {
                    distanceGrid[xx][yy] = Math.min(distance, safenessGrid[xx][yy]);
                    queue.add(new Pair<>(xx, yy));
//                    System.out.println(distanceGrid[xx][yy] + " : " + xx + " : " + yy);
                }
            }
        }
        return distanceGrid[n-1][n-1];
    }
}
