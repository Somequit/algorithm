package leetcode.contest.double_166;

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

    class DSU {
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
            parent[xAncestor] = yAncestor;
        }
    }

    /**
     * 使用并查集求出哪些下标可以完全相互替换，可以替换的下标集合排序将大值作为偶数下标
     * @return
     */
    public long maxAlternatingSum(int[] nums, int[][] swaps) {
        int len = nums.length;

        // 使用并查集求出哪些下标可以完全相互替换
        DSU dsu = new DSU(len);

        for (int[] s : swaps) {
            dsu.union(s[0], s[1]);
        }

        // 相互替换集合
        Map<Integer, List<Integer>> indexToSwapMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
//            indexToSwapMap.merge(dsu.find(i),
//                    new ArrayList<>(Arrays.asList(i)),
//                    (existing, newList) -> {
//                        existing.addAll(newList);
//                        return existing;
//                    });
            indexToSwapMap.computeIfAbsent(dsu.find(i), x -> new ArrayList<>()).add(i);
        }

        long res = 0;
        // 可以替换的下标集合排序将大值作为偶数下标
        for (List<Integer> swapLists : indexToSwapMap.values()) {

            List<Integer> numsList = new ArrayList<>();
            int evenNum = 0;
            for (int swapList : swapLists) {
                numsList.add(nums[swapList]);

                if (swapList % 2 == 0) {
                    evenNum++;
                }
            }

            numsList.sort((o1, o2) -> {
                return o2 - o1;
            });
//            System.out.println(numsList);

            for (int i = 0; i < numsList.size(); i++) {
                if (i < evenNum) {
                    res += numsList.get(i);

                } else {
                    res -= numsList.get(i);
                }
            }

        }

        return res;

    }




}
