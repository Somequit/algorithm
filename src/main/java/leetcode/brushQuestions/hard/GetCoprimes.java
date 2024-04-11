package leetcode.brushQuestions.hard;

import java.util.*;

/**
 * @author gusixue
 * @description 1766. 互质树
 * @date 2024/4/11
 */
public class GetCoprimes {

    /**
     * 从根节点模拟二叉树的前序遍历树，记录 根 到 当前节点父节点 的所有祖先节点序列，
     * 序列中节点的值作为下标，放入 List[] 中，节点编号依次放入 List 中（进入节点添加、回溯节点删除），保证 List 的最后一个为相同值中最后加入的节点，
     * 序列中节点编号为 key，加入时间（从根节点递增）为 value 放入 HashMap，在找到编号后求最大的时间、即最近的祖先，
     * 每个节点遍历 List[] 数组 [1,50] 的值，如果互质则取出最后一个节点编号，通过 HashMap 找到最大的时间
     * 也可以预处理 gcd 哪些互质，最多 50*50
     */
    public int[] getCoprimes(int[] nums, int[][] edges) {
        Map<Integer, Integer> mapValTime = new HashMap<>();

        List<Integer>[] listNumVal = new ArrayList[51];
        Arrays.setAll(listNumVal, i -> new ArrayList<>());

        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        List<Integer>[] edgeList = buildTree(n, edges);

        recursionGetCoprimes(edgeList, nums, 0, -1, listNumVal, mapValTime, ans);

        return ans;
    }

    private void recursionGetCoprimes(List<Integer>[] edgeList, int[] nums, int son, int father, List<Integer>[] listNumVal,
                                      Map<Integer,Integer> mapValTime, int[] ans) {
        int res = -1;
        int curTime = -1;
        for (int i = 1; i < 51; i++) {
            if (gcd(i, nums[son]) == 1 && listNumVal[i].size() > 0) {
                int val = listNumVal[i].get(listNumVal[i].size() - 1);

                if (mapValTime.get(val) > curTime) {
                    curTime = mapValTime.get(val);
                    res = val;
                }
            }
        }
        ans[son] = res;

        for (int next : edgeList[son]) {
            if (next != father) {
                listNumVal[nums[son]].add(son);
                mapValTime.put(son, mapValTime.size());

                recursionGetCoprimes(edgeList, nums, next, son, listNumVal, mapValTime, ans);

                listNumVal[nums[son]].remove(listNumVal[nums[son]].size() - 1);
                mapValTime.remove(son);
            }
        }
    }

    private int gcd(int a,int b){
        return (a == 0 ? b : gcd(b % a, a));
    }

    private List<Integer>[] buildTree(int n, int[][] edges) {
        List<Integer>[] edgeList = new ArrayList[n];
        Arrays.setAll(edgeList, i -> new ArrayList<>());

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            edgeList[u].add(v);
            edgeList[v].add(u);
        }
        return edgeList;
    }
}
