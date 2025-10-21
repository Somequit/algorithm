package codefroces.brushQuestions.started;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author gusixue
 * @description
 * @date 2025/10/21 11:05 上午
 */
public class CF12A_Super_Agent {

    public static void main(String[] args) throws IOException {
        StringBuilder[] agents  = new StringBuilder[3];
        for (int i = 0; i < 3; i++) {
            agents[i] = new StringBuilder();
            agents[i].append(scanString());
        }

        String res = solve(agents);

        print(res);
    }

    public static String solve(StringBuilder[] agents) throws IOException {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3 - i; j++) {
                if (agents[i].charAt(j) != agents[2 - i].charAt(2 - j)) {
                    return "NO";
                }
            }
        }

        return "YES";
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

