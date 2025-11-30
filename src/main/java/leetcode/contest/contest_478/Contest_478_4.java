package leetcode.contest.contest_478;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/11/30 10:24 上午
 */
public class Contest_478_4 {
    public static void main(String[] args) {
        Contest_478_4 contest = new Contest_478_4();

        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

//            int res = contest.solution(n, nums, list);
//            System.out.println(res);
        }

    }

    /**
     * 并查集求是否实现+莫队求离线区间+TreeMap求区间中位数（偏左）与大于中位数总和+差分求区间总和
     */
    public long[] minOperations(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        int m = queries.length;
        long[] res = new long[m];

        // 并查集求是否实现
        List<int[]> listQueries = doDsuQueries(nums, k, queries, n, m, res);
//        listQueries.stream().forEach(arr -> System.out.print(arr[0] + " : " + arr[1] + " : " + arr[2] + "\t"));
//        System.out.println();

        // 莫队求离线区间+TreeMap求区间中位数（偏左）与大于中位数总和
        Mos mos = new Mos(nums);
        long[][] medianAndLargeTotal = mos.processQueries(listQueries, m);
//        Arrays.stream(medianAndLargeTotal).forEach(arr -> System.out.print(arr[0] + " : " + arr[1] + "\t"));
//        System.out.println();

        // 差分求区间总和
        doDifferenceRes(n, nums, res, k, listQueries, medianAndLargeTotal);

        return res;
    }

    private void doDifferenceRes(int n, int[] nums, long[] res, int k, List<int[]> listQueries, long[][] medianLargeTotal) {
        long[] prefix = new long[n + 1];
        for (int i = 1; i < n + 1; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }
        for (int[] listQuerie : listQueries) {
            int l = listQuerie[0];
            int r = listQuerie[1];
            int index = listQuerie[2];

            long prefixTotal = prefix[r + 1] - prefix[l];
//            System.out.println(l + " : " + r + " : " + index + " : " + prefixTotal);
            if ((r - l + 1) % 2 == 1) {
                res[index] = (2 * medianLargeTotal[index][1] - prefixTotal + medianLargeTotal[index][0]) / k;
            } else {
                res[index] = (2 * medianLargeTotal[index][1] - prefixTotal) / k;
            }
        }
    }

    class Mos {

        class Query {
            int l;
            int r;
            int idx;
            int block;

            Query(int l, int r, int idx, int blockSize) {
                this.l = l;
                this.r = r;
                this.idx = idx;
                // 分块：确定查询所属的块，用于排序
                this.block = l / blockSize;
            }

            @Override
            public String toString() {
                return "Query{" +
                        "l=" + l +
                        ", r=" + r +
                        ", idx=" + idx +
                        ", block=" + block +
                        '}';
            }
        }

        private int[] arr;
        // 当前区间内中位数
        private int median;
        // 当前区间内小于中位数个数
        private int medianLessCnt = 0;
        // 当前区间内大于中位数总和
        private long medianLargeTotal;

        public Mos(int[] arr) {
            this.arr = arr;
            this.median = -1;
            this.medianLessCnt = 0;
            this.medianLargeTotal = 0;
        }

        public long[][] processQueries(List<int[]> listQueries, int m) {
            Query[] queries = listToQuery(listQueries);
//            System.out.println(Arrays.toString(queries));

            // 对查询进行排序：先按块号，块内按右端点（奇偶优化可选）
            Arrays.sort(queries, (a, b) -> {
                if (a.block != b.block) {
                    return Integer.compare(a.block, b.block);
                }
                // 右端点升序
                return Integer.compare(a.r, b.r);
//                // 奇偶化排序优化：奇数块r升序，偶数块r降序
//                if ((a.block & 1) == 1) {
//                    return Integer.compare(a.r, b.r);
//                } else {
//                    return Integer.compare(b.r, a.r);
//                }
            });
//            System.out.println(Arrays.toString(queries));

            // 区间中位数（偏左）与大于中位数总和
            long[][] medianAndLargeTotal = new long[m][2];
            // 默认开始 [0,0]
            int curL = 0, curR = 0;
            median = arr[0];
            medianLargeTotal = 0;
            medianLessCnt = 0;
            TreeMap<Integer, Integer> treeMapCurArr = new TreeMap<>();
            treeMapCurArr.put(arr[0], 1);
            for (Query q : queries) {
                // 移动左右指针以适应当前查询区间
                while (curL > q.l) {
                    curL--;
                    add(curL, curR - curL + 1, treeMapCurArr);
                }
                while (curR < q.r) {
                    curR++;
                    add(curR, curR - curL + 1, treeMapCurArr);
                }
                while (curL < q.l) {
                    remove(curL, curR - curL, treeMapCurArr);
                    curL++;
                }
                while (curR > q.r) {
                    remove(curR, curR - curL, treeMapCurArr);
                    curR--;
                }
                medianAndLargeTotal[q.idx][0] = median;
                medianAndLargeTotal[q.idx][1] = medianLargeTotal;
            }

            return medianAndLargeTotal;
        }

        private Query[] listToQuery(List<int[]> listQueries) {
            int n = listQueries.size();
            Query[] res = new Query[n];
            int blockSize = (int) Math.sqrt(n);
            for (int i = 0; i < n; i++) {
                res[i] = new Query(listQueries.get(i)[0], listQueries.get(i)[1], listQueries.get(i)[2], blockSize);
            }
            return res;
        }

        private void add(int pos, int len, TreeMap<Integer, Integer> treeMapCurArr) {
            int value = arr[pos];
            treeMapCurArr.merge(value, 1, Integer::sum);

            if (value > median) {
                if (len % 2 == 0) {
                    medianLargeTotal += value;

                } else {
                    // median移右
                    if (medianLessCnt + treeMapCurArr.get(median) <= len / 2) {
                        medianLessCnt += treeMapCurArr.get(median);
                        median = treeMapCurArr.higherKey(median);
                    }

                    medianLargeTotal += -median + value;

                }

            } else if (value < median) {
                if (len % 2 == 0) {
                    medianLargeTotal += median;
                    // median移左
                    medianLessCnt++;
                    if (medianLessCnt > len / 2 - 1) {
                        median = treeMapCurArr.lowerKey(median);
                        medianLessCnt -= treeMapCurArr.get(median);

                    }

                } else {
                    medianLessCnt++;
                }

            } else{
                if (len % 2 == 0) {
                    medianLargeTotal += median;

                }
            }
        }

        private void remove(int pos, int len, TreeMap<Integer, Integer> treeMapCurArr) {
            int value = arr[pos];
            treeMapCurArr.merge(value, -1, Integer::sum);
            if (treeMapCurArr.get(value) == 0) {
                treeMapCurArr.remove(value);
            }

            if (value > median) {
                if (len % 2 == 0) {
                    medianLargeTotal += -value + median;
                    // median移左
                    if (medianLessCnt > len / 2 - 1) {
                        median = treeMapCurArr.lowerKey(median);
                        medianLessCnt -= treeMapCurArr.get(median);
                    }

                } else {
                    medianLargeTotal += -value;
                }

            } else if (value < median) {
                if (len % 2 == 0) {
                    medianLessCnt--;

                } else {
                    // median移右
                    medianLessCnt--;
                    if (medianLessCnt + treeMapCurArr.get(median) <= len/2) {
                        medianLessCnt += treeMapCurArr.get(median);
                        median = treeMapCurArr.higherKey(median);
                    }
                    medianLargeTotal -= median;
                }

            } else{
                if (len % 2 == 0) {
                    // median移左
                    if (medianLessCnt > len / 2 - 1) {
                        median = treeMapCurArr.lowerKey(median);
                        medianLessCnt -= treeMapCurArr.get(median);
                    }

                } else {
                    // median移右
                    if (medianLessCnt + treeMapCurArr.getOrDefault(median, 0) <= len / 2) {
                        medianLessCnt += treeMapCurArr.getOrDefault(median, 0);
                        median = treeMapCurArr.higherKey(median);
                    }
                    medianLargeTotal -= median;
                }
            }

        }

    }


    private List<int[]> doDsuQueries(int[] nums, int k, int[][] queries, int n, int m, long[] res) {
        List<int[]> listQueries = new ArrayList<>();

        if (k > 1) {
            DSU dsu = new DSU(n);
            for (int i = 1; i < n; i++) {
                if (nums[i] % k == nums[i - 1] % k) {
                    dsu.union(i - 1, i);
                }
            }

//            for (int i = 0; i < n; i++) {
//                System.out.println(dsu.find(i));
//            }

            for (int i = 0; i < m; i++) {
                int l = queries[i][0];
                int r = queries[i][1];

                if (dsu.find(l) != dsu.find(r)) {
                    res[i] = -1;

                } else {
                    listQueries.add(new int[]{l, r, i});
                }

            }
        } else {
            for (int i = 0; i < m; i++) {
                listQueries.add(new int[]{queries[i][0], queries[i][1], i});
            }
        }

        return listQueries;
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
            parent[xAncestor] = yAncestor;
        }
    }

}
