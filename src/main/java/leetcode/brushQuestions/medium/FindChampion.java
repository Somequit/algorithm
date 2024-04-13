package leetcode.brushQuestions.medium;

/**
 * @author gusixue
 * @description 2924. 找到冠军 II
 * @date 2024/4/13
 */
public class FindChampion {

    /**
     * 找入度为 0 的点就是没人能够打败的人
     */
    public int findChampion(int n, int[][] edges) {
        int[] indegree = new int[n];
        for (int[] edge : edges) {
            indegree[edge[1]]++;
        }

        int res = -2;
        for (int i = 0; i < n; i++) {
            if(indegree[i] == 0) {
                if (res == -2) {
                    res = i;

                } else {
                    res = -1;

                }
            }
        }

        return res;
    }
}
