package codefroces.contest.Codeforces_Round_1065_Div_3;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author gusixue
 * @description
 * @date 2025/11/18 10:55 下午
 */
public class Contest_1065Div3_9 {
    public static void main(String[] args) throws IOException {
        int t = scanInt();

        while (t > 0) {
            int n = scanInt();
            int m = scanInt();

            long res = solve(n, m);

            print(res);

            t--;
        }
    }

    private static long solve(int n, int m) {
        long res = 0;
        for (int i = n; i >= 2; i--) {
            int curK = (int) Math.floor(Math.log(m) / Math.log(i));
            System.out.println(m + " : " + curK);
            int curA = qPow(i, curK);
            curA = m / curA * curA;
            res += curK;
            m = curA - 1;
        }

        return res;
    }

    // 注意不能对 pow 取模
    private static int qPow(int value, int pow) {
        int res = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) {
                res *= value;
            }

            value *= value;
            pow >>= 1;
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