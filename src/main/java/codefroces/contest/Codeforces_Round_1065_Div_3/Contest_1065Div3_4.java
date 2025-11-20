package codefroces.contest.Codeforces_Round_1065_Div_3;

import java.io.*;
import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/11/18 10:55 下午
 */
public class Contest_1065Div3_4 {
    public static void main(String[] args) throws IOException {
        int t = scanInt();

        while (t > 0) {
            int n = scanInt();
            int[] a = scanIntArray(n);
            int[] b = scanIntArray(n);

            int res = solve(n, a, b);

            print(res == 0 ? "Tie" : (res == 1 ? "Ajisai" : "Mai"));

            t--;
        }
    }

    private static int solve(int n, int[] a, int[] b) {
        int[] bitA = new int[n];
        int[] bitB = new int[n];
        for (int i = 19; i >= 0; i--) {
            int totalA = 0;
            int totalB = 0;

            for (int j = 0; j < n; j++) {
                bitA[j] = (a[j] & (1 << i)) >> i;
                totalA += bitA[j];
                bitB[j] = (b[j] & (1 << i)) >> i;
                totalB += bitB[j];
            }

            int res = doXorGame(n, bitA, bitB, totalA, totalB);
            if (res != 0) {
                return res;
            }
        }

        return 0;
    }

    private static int doXorGame(int n, int[] a, int[] b, int totalA, int totalB) {
//        System.out.println(Arrays.toString(a));
//        System.out.println(Arrays.toString(b));
//        System.out.println(totalA + " : " + totalB);
        if ((totalA + totalB) % 2 == 0) {
            return 0;
        }

        for (int i = n - 1; i >= 0; i--) {
            if (a[i] != b[i]) {
                return i % 2 == 0 ? 1 : -1;
            }
        }

        return totalA % 2 == 1 ? 1 : -1;
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