package codefroces.contest.Codeforces_Global_Round_30;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author gusixue
 * @description TODO:GSX:E Journey
 * @date 2025/10/21 10:37 上午
 */
public class Contest5 {
//    public static void main(String[] args) throws IOException {
//        int t = scanInt();
//
//        while (t > 0) {
//            int n = scanInt();
//            int m = scanInt();
//            int[] u = new int[m];
//            int[] v = new int[m];
//            int[] w = new int[m];
//            for (int i = 0; i < m; i++) {
//                u[i] = scanInt();
//                v[i] = scanInt();
//                w[i] = scanInt();
//            }
//
//            long res = solve(n, m, u, v, w);
//
//            print(res);
//
//            t--;
//        }
//    }
//
//    private static long solve(int n, int m, int[] u, int[] v, int[] w) {
//        long res = 0;
//        int[] degree = new int[n + 1];
//        for (int i = 0; i < m; i++) {
//            res += w[i];
//            degree[u[i] - 1]++;
//            degree[v[i] - 1]++;
//        }
//
//        int[] oddDegree = new int[n];
//        boolean flagEvenAll = true;
//        for (int i = 0; i < n; i++) {
//            if (degree[i] % 2 == 1) {
//                oddDegree[i] = 1;
//                flagEvenAll = false;
//            }
//        }
//        if (flagEvenAll) {
//            return res;
//        }
////        System.out.println(Arrays.toString(oddDegree));
//
//        int[] minW = new int[m];
//        DSU dsuSearch = new DSU(n);
//        int maxWNum = w[0];
//        for (int i = 0; i < m; i++) {
//            minW[i] = w[i];
//            dsuSearch.union(u[i] - 1, v[i] - 1);
//
//            if (w[i] < maxWNum) {
//                for (int j = 0; j < i; j++) {
//                    if (dsuSearch.find(u[j] - 1) == dsuSearch.find(u[i] - 1)) {
//                        minW[j] = Math.min(minW[j], w[i]);
//                    }
//                }
//
//            } else {
//                maxWNum = w[i];
//            }
//        }
////        System.out.println(Arrays.toString(minW));
//
//        DSU dsu = new DSU(n, oddDegree);
//
//        for (int i = 0; i < m; i++) {
//            long addW = dsu.union(u[i] - 1, v[i] - 1) / 2;
//            res += addW * minW[i];
////            System.out.println(i + " : " + addW);
//        }
//
//        return res;
//    }


    static class DSU {
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
            }
        }

        public DSU(int n, int[] costOut) {
            this.size = n;

            parent = new int[n];
            cost = new int[n];
            for (int i = 0; i < n; ++i) {
                parent[i] = i;
                cost[i] = costOut[i];
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                // 路径压缩
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public int union(int u, int v) {
            int xAncestor = find(u);
            int yAncestor = find(v);
            parent[xAncestor] = yAncestor;

            if (xAncestor != yAncestor) {
                cost[yAncestor] += cost[xAncestor];
            }

            int res = cost[yAncestor];
            cost[yAncestor] %= 2;
            return res;
        }

        public int getCostByIndex(int index) {
            return cost[index];
        }
    }


    static int MOD = 1_000_000_007;
    static int INF = (int) 1e9;
    static long fact[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int scanInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    static long scanLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    static String scanString() throws IOException {
        return nextToken();
    }

    static String nextToken() throws IOException {
        if (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    static int[] scanIntArray(int size) throws IOException {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanInt();
        }
        return array;
    }

    static long[] scanLongArray(int size) throws IOException {
        long array[] = new long[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanLong();
        }
        return array;
    }

    static void printArray(int arr[]) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int e : arr) {
            sb.append(e + " ");
        }
        bw.write(sb.toString().trim());
        bw.newLine();
        bw.flush();
    }

    static void print(Object o) throws IOException {
        bw.write(o.toString());
        bw.newLine();
        bw.flush();
    }
}
