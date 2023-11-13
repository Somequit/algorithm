package leetcode.contest_vp.double_114_vp;


import java.util.ArrayList;
import java.util.List;

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
    private int solution(int n, int[][] edges, int[] values, int k) {
        this.answer = 0;

        List<Integer>[] edgeList = buildTree(n, edges);

        dfsTraversalTree(0, -1, edgeList, values, k);

        return this.answer;
    }

    private int answer;

    private List<Integer>[] buildTree(int n, int[][] edges) {
        List<Integer>[] edgeList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edgeList[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            edgeList[u].add(v);
            edgeList[v].add(u);
        }
        return edgeList;
    }

    private int dfsTraversalTree(int son, int father, List<Integer>[] edgeList, int[] values, int k) {
        // 叶子节点
        if (edgeList[son].size() == 1 && edgeList[son].get(0).equals(father)) {
            if (values[son] % k == 0) {
                this.answer++;
            }
            return values[son] % k;
        }

        int totalValue = values[son] % k;
        for (int next : edgeList[son]) {
            if (next != father) {
                totalValue += dfsTraversalTree(next, son, edgeList, values, k);
                totalValue %= k;
            }
        }
        if (totalValue == 0) {
            this.answer++;
        }

        return totalValue % k;
    }


}
