package leetcode.brushQuestions.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gusixue
 * @description 2477. 到达首都的最少油耗
 * @date 2023/12/5
 */
public class MinimumFuelCost {

    /**
     * 建图 + DFS 一次遍历：
     *
     * 第 1 步：
     * 思考不走回头路肯定是最优的，因此每个叶子节点均会发车
     *
     * 第 2 步：
     * 如果车上座位比较多，则发车到父节点后，将所有人塞入最少的车
     *
     * 第 3 步：
     * 实现仅在回溯时处理，
     *     * 叶子节点发一辆车，载一个人
     *     * 非叶子节点、将所有孩子节点的人加自己塞入最少的车
     *     * 根节点不能再发车了
     *
     * 第 4 步：
     * 具体实现可以仅回溯人数 human，使用（human+seats-1）/seats 确定最少发了几辆车
     * 注意：发车到根节点即可，根节点不发车
     * 时间复杂度：O（n），空间复杂度：O（n+height）建树+递归栈深度
     *
     */
    private long res;
    public long minimumFuelCost(int[][] roads, int seats) {
        this.res = 0L;

        int n = roads.length + 1;
        // 建树
        List<Integer>[] edgeList = buildTree(n, roads);

        // 递归遍历树
        dfsTraversalTree(0, -1, edgeList, seats);

        return this.res;
    }

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

    private int dfsTraversalTree(int son, int father, List<Integer>[] edgeList, int seats) {
        int human = 1;

        // 叶子节点
        if (edgeList[son].size() == 1 && edgeList[son].get(0).equals(father)) {
            // 到父节点发一辆车消耗一升油
            this.res++;
            return human;
        }

        for (int next : edgeList[son]) {
            if (next != father) {
                human += dfsTraversalTree(next, son, edgeList, seats);
            }
        }

        // 发车到根节点即可，根节点不发车
        if (son != 0) {
            // 到父节点多少辆车就是会消耗多少升油
            this.res += (human + seats - 1) / seats;
        }

        return human;
    }
}
