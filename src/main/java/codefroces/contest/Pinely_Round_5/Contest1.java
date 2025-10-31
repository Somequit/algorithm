package codefroces.contest.Pinely_Round_5;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author gusixue
 * @description A Round Trip
 * @date 2025/10/31 12:34 上午
 */
public class Contest1 {
    public static void main(String[] args) throws IOException {
        int t = scanInt();

        while (t > 0) {
            int r = scanInt();
            int x = scanInt();
            int d = scanInt();
            int n = scanInt();
            String contest = scanString();
            int res = solve(r, x, d, n, contest);
            print(res);

            t--;
        }

    }

    private static int solve(int r, int x, int d, int n, String contest) {
        if (r < x) {
            return n;
        }

        int res = n;
        int contestCount = (r - x + 1 + d - 1) / d;
        for (char c : contest.toCharArray()) {
            if (c == '2') {
                res--;

            } else {
                contestCount--;
                if (contestCount == 0) {
                    break;
                }
            }
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