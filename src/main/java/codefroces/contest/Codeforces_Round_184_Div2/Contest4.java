package codefroces.contest.Codeforces_Round_184_Div2;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author gusixue
 * @description
 * @date 2025/11/14 10:34 下午
 */
public class Contest4 {
    public static void main(String[] args) throws IOException {
        int t = scanInt();

        while (t > 0) {
            long x = scanLong();
            long y = scanLong();
            long k = scanLong();

            long res = solve(x,y,k);

            print(res);

            t--;
        }
    }

    private static long CNT_LOOP = 0;
    private static long solve(long x, long y, long k) {
        long left = k;
        long right = 1_000_000_000_000L;
        long res = -1;
        while (left <= right) {
            long mid = (right - left) / 2 + left;
            if (checkMid(mid, y, x, k)) {
                res = mid;
                right = mid - 1;

            } else {
                left = mid + 1;
            }
        }

        System.out.println(CNT_LOOP);

        return res;
    }

    private static boolean checkMid(long stop, long y, long x, long k) {
        for (int i = 0; i < x; i++) {
            CNT_LOOP++;
            stop -= stop / y;
        }

        return stop >= k;
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

