package leetcode.contest.contest_365;


import utils.AlgorithmUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author gusixue
 * @date 2023/10/01
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
            List<Integer> edges = AlgorithmUtils.systemInList();

            int[] res = contest.solution(edges);
            System.out.println(Arrays.toString(res));
        }

    }

    private int[] solution(List<Integer> edges) {
        int n = edges.size();
        int[] answer = new int[n];

        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (vis[i]) {
                continue;
            }

            List<Integer> ringList = new ArrayList<>();
            int ringPoint = dfs(i, edges, vis, ringList);
//            System.out.println(ringPoint + " : " + ringList);

            int ringIndex = -1;
            for (int j = ringList.size() - 1; j >= 0; j--) {
                if (ringList.get(j).equals(ringPoint)) {
                    ringIndex = j;
                    break;
                }
            }
//            System.out.println(ringIndex);

            for (int j = ringList.size() - 1; j >= 0; j--) {
                if (j < ringIndex || ringIndex == -1) {
                    answer[ringList.get(j)] = answer[edges.get(ringList.get(j))] + 1;

                } else {
                    answer[ringList.get(j)] = ringList.size() - ringIndex;
                }
            }
        }

        return answer;
    }

    private int dfs(int cur, List<Integer> edges, boolean[] vis, List<Integer> ringList) {
        if (vis[cur]) {
            return cur;
        }

        vis[cur] = true;
        ringList.add(cur);
        return dfs(edges.get(cur), edges, vis, ringList);
    }
}
