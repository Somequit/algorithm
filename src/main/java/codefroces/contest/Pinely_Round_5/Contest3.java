package codefroces.contest.Pinely_Round_5;

import java.io.*;
import java.util.*;

/**
 * @author gusixue
 * @description C. Loyalty
 * @date 2025/10/31 1:59 上午
 */
public class Contest3 {
    public static void main(String[] args) throws IOException {
        int t = scanInt();

        while (t > 0) {
            int n = scanInt();
            int x = scanInt();
            int[] price = scanIntArray(n);
            int[] res = solve(n, x, price);
            printArray(res);

            t--;
        }


    }

    private static int[] solve(int n, int x, int[] price) throws IOException {
        long resPoints = 0;
        int[] res = new int[n];

        Arrays.sort(price);

        long curCostSur = 0;
        for (int r = n - 1, l = -1, i = 0; r > l; r--) {
            while (curCostSur + price[r] < x && r > l + 1) {
                l++;
                curCostSur += price[l];
                res[i] = price[l];
                i++;
            }

            curCostSur += price[r];
            if (curCostSur >= x) {
                curCostSur %= x;
                resPoints += price[r];
            }

            res[i] = price[r];
            i++;
        }

        print(resPoints);
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