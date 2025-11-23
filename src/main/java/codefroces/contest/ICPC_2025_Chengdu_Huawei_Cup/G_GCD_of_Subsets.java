package codefroces.contest.ICPC_2025_Chengdu_Huawei_Cup;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author gusixue
 * @description
 * @date 2025/11/23 5:50 上午
 */
public class G_GCD_of_Subsets {

    public static void main(String[] args) throws IOException {
        int t = scanInt();

        while (t > 0) {
            long n = scanLong();
            long k = scanLong();
            long m = scanLong();

            long res = solve(n, k, m);

            print(res);

            t--;
        }
    }

    private static long solve(long n, long k, long m) {
        if (m == n) {
            return n;
        }

        long matchCnt = (n / k - 1) / 2 * 2;
        long surplusCnt = n - matchCnt - 1;
        if (m <= surplusCnt) {
            return m + matchCnt / 2 + 1;
        }

        long res = m + 1;
        m -= surplusCnt;
        matchCnt -= m;
        return res + matchCnt / 2;
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
