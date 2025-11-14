package codefroces.contest.Codeforces_Round_184_Div2;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author gusixue
 * @description
 * @date 2025/11/14 10:34 下午
 */
public class Contest3 {
    public static void main(String[] args) throws IOException {
        int t = scanInt();

        while (t > 0) {
            int n = scanInt();
            int[] a = scanIntArray(n);

            long res = solve(n, a);

            print(res);

            t--;
        }
    }

    private static long solve(int n, int[] a) {
        long total = 0;
        for (int num : a) {
            total += num;
        }

        long[] dp = new long[n];
        dp[0] = total - a[0] + 2;
        long res = Math.max(total, dp[0]);
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(total, dp[i - 1]) - a[i] + (2L * (i + 1));
            res = Math.max(res, dp[i]);
        }

        return res;
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

