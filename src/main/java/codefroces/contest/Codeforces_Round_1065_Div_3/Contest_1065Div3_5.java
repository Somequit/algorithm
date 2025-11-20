package codefroces.contest.Codeforces_Round_1065_Div_3;

import java.io.*;
import java.util.*;

/**
 * @author gusixue
 * @description TODO:GSX:
 * @date 2025/11/18 10:55 下午
 */
public class Contest_1065Div3_5 {
    public static void main(String[] args) throws IOException {
        int t = scanInt();

        while (t > 0) {
            int n = scanInt();
            int[] p = scanIntArray(n);

            boolean res = solve(n, p);

            print(res ? "Yes" : "No");

            t--;
        }
    }

    private static boolean solve(int n, int[] p) {
        Deque<Integer> stackMin = new LinkedList<>();
        stackMin.push(p[0]);
        for (int i = 1; i < n; i++) {
            int curMin = p[i];
            while (!stackMin.isEmpty() && stackMin.peekFirst() < p[i]) {
                curMin = Math.min(stackMin.pollFirst(), curMin);
            }

            stackMin.push(curMin);
        }

        return stackMin.size() <= 1;
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