package codefroces.contest.ICPC_2025_Chengdu_Huawei_Cup;

import java.io.*;
import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/11/23 7:43 上午
 */
public class A_A_Lot_of_Paintings {
    public static void main(String[] args) throws IOException {
        int t = scanInt();

        while (t > 0) {
            int n = scanInt();
            int[] d = scanIntArray(n);

            int[] res = solve(n, d);

            if (res == null) {
                print("NO");
            } else {
                print("YES");
                printArray(res);
            }

            t--;
        }
    }

    private static int[] solve(int n, int[] d) {
        int[] res = new int[n];
        int total = 0;
        int noNegCnt = 0;
        for (int i = 0; i < n; i++) {
            total += d[i];
            noNegCnt += d[i] > 0 ? 1 : 0;
        }

        if (total == 100) {
            for (int i = 0; i < n; i++) {
                res[i] = d[i];
            }
            return res;

            // 大于 100 将 d 中大于0 元素统一减 (total-100)/noNegCnt，需要判断是否减大于 0.5
            // 为了避免精度可以统一 *noNegCnt，结果为 noNegCnt * d - (total - 100)
        } else if (total > 100) {
            // *10 避免精度误差
            if ((total * 10 - 1000 + noNegCnt - 1) / noNegCnt > 5) {
                return null;
            }

            for (int i = 0; i < n; i++) {
                if (d[i] > 0) {
                    res[i] = noNegCnt * d[i] - (total - 100);
                }
            }
            return res;

            // 小于 100 所有元素统一加 (100-total)/n，需要判断是否加大于等于 0.5
            // 为了避免精度可以统一 *noNegCnt，结果为 n * d + (100 - total)
        } else {
            // *10 避免精度误差
            if ((1000 - total * 10) / n >= 5) {
                return null;
            }

            for (int i = 0; i < n; i++) {
                res[i] = d[i] * n + (100 - total);
            }

            return res;
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
