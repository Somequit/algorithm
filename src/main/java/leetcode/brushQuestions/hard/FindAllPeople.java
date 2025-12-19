package leetcode.brushQuestions.hard;

import java.util.*;

/**
 * @author gusixue
 * @description 2092. 找出知晓秘密的所有专家
 * @date 2025/12/19 4:59 下午
 */
public class FindAllPeople {

    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        DSU dsu = new DSU(n);
        dsu.union(0, firstPerson);

        List<int[]>[] listMeet = new ArrayList[100_000];
        for (int[] meeting : meetings) {
            int t = meeting[2] - 1;

            if (listMeet[t] == null) {
                listMeet[t] = new ArrayList<>();
            }
            listMeet[t].add(new int[]{meeting[0], meeting[1]});
        }

        Set<Integer> setRes = new HashSet<>();
        setRes.add(0);
        setRes.add(firstPerson);
        for (int i = 0; i < listMeet.length; i++) {
            if (listMeet[i] != null) {
                for (int[] meet : listMeet[i]) {
                    dsu.union(meet[0], meet[1]);
                }

                for (int[] meet : listMeet[i]) {
//                    System.out.println(meet[0] + " : " + dsu.find(meet[0]));
                    if (dsu.find(meet[0]) != 0) {
                        dsu.separation(meet[0]);
                        dsu.separation(meet[1]);

                    } else {
                        setRes.add(meet[0]);
                        setRes.add(meet[1]);
                    }
                }
            }
        }

        return new ArrayList<>(setRes);
    }

    class DSU {
        // 代价为集合中所有边按位与
        private final int[] parent;
        private final int size;

        public DSU(int n) {
            this.size = n;

            parent = new int[n];
            for (int i = 0; i < n; ++i) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                // 路径压缩
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int u, int v) {
            int xAncestor = find(u);
            int yAncestor = find(v);
            if (xAncestor > yAncestor) {
                parent[xAncestor] = yAncestor;

            } else {
                parent[yAncestor] = xAncestor;
            }
        }

        public void separation(int u) {
            parent[u] = u;
        }
    }

}
