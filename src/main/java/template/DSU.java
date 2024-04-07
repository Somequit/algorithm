package template;

/**
 * @author gusixue
 * @description 带权并查集
 * @date 2024/4/7
 */
public class DSU {
    // 代价为集合中所有边按位与
    private final int[] cost;
    private final int[] parent;
    private final int size;

    public DSU(int n) {
        this.size = n;

        parent = new int[n];
        cost = new int[n];
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
            // (2^31)-1，31 位 1
            cost[i] = Integer.MAX_VALUE;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            // 路径压缩
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int u, int v, int w) {
        int xAncestor = find(u);
        int yAncestor = find(v);
        parent[xAncestor] = yAncestor;
        cost[yAncestor] &= cost[xAncestor];
        cost[yAncestor] &= w;
    }

    public int getCostByIndex(int index) {
        return cost[index];
    }
}
