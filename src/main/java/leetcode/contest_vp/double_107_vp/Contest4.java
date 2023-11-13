package leetcode.contest_vp.double_107_vp;


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
    private int[] solution(int n, int[][] logs, int x, int[] queries) {
        int qLen = queries.length;
        int logLen = logs.length;

        Arrays.sort(logs, (o1, o2) -> {return o1[1] - o2[1];});

        Integer[] queriesIndex = new Integer[qLen];
        for (int i = 0; i < qLen; i++) {
            queriesIndex[i] = i;
        }
        Arrays.sort(queriesIndex, (o1, o2) -> {return queries[o1] - queries[o2];});

        int[] serverTime = new int[n + 1];
        Arrays.fill(serverTime, -1);
        int ans = 0;

        int[] res = new int[qLen];
        for (int i = 0, left = 0, right = 0; i < qLen; i++) {
            while (right < logLen && logs[right][1] <= queries[queriesIndex[i]]) {
                int serverId = logs[right][0];
                if (serverTime[serverId] == -1) {
                    ans++;
                }
                serverTime[serverId] = right;

                right++;
            }
            while (left < logLen && logs[left][1] < queries[queriesIndex[i]] - x) {
                int serverId = logs[left][0];
                if (serverTime[serverId] == left) {
                    serverTime[serverId] = -1;
                    ans--;
                }

                left++;
            }

            res[queriesIndex[i]] = n - ans;
        }
        return res;
    }

}
