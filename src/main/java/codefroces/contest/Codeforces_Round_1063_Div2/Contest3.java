package codefroces.contest.Codeforces_Round_1063_Div2;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author gusixue
 * @description
 * @date 2025/10/31 12:34 上午
 */
public class Contest3 {
    public static void main(String[] args) throws IOException {
        int t = scanInt();

        while (t > 0) {
            int n = scanInt();
            int[] a1 = scanIntArray(n);
            int[] a2 = scanIntArray(n);

            long res = solve(n, a1, a2);

            print(res);

            t--;
        }
    }

    private static long solve(int n, int[] a1, int[] a2) {
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = a2[n - 1];
        int[] suffixMax = new int[n];
        suffixMax[n - 1] = a2[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMax[i] = Math.max(suffixMax[i + 1], a2[i]);
            suffixMin[i] = Math.min(suffixMin[i + 1], a2[i]);
        }

        int prefixMin = a1[0];
        int prefixMax = a1[0];
        long curMin = Math.min(prefixMin, suffixMin[0]);
        long curMax = Math.max(prefixMax, suffixMax[0]);
        long prevMin = curMin;
        long prevMax = curMax;
        long res = curMin * (2 * n - curMax + 1);
        for (int i = 1; i < n; i++) {
            prefixMin = Math.min(prefixMin, a1[i]);
            prefixMax = Math.max(prefixMax, a1[i]);

            curMin = Math.min(prefixMin, suffixMin[i]);
            curMax = Math.max(prefixMax, suffixMax[i]);
//            System.out.println(prevMin + " : " + prevMax + " : " + curMin + " : " + curMax);

            res += curMin * (2 * n - curMax + 1);
            res -= Math.min(curMin, prevMin) * (2 * n - Math.max(curMax, prevMax) + 1);

            curMin = Math.max(curMin, prevMin);
            curMax = Math.min(curMax, prevMax);

            prevMin = curMin;
            prevMax = curMax;
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